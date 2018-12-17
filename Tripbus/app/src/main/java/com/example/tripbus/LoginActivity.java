package com.example.tripbus;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.nhn.android.naverlogin.OAuthLogin;
import com.nhn.android.naverlogin.OAuthLoginHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class LoginActivity extends AppCompatActivity {
    Button login, register, naver, facebook;
    private EditText inputid, inputpassword;
    private SessionManager session;
    Retrofit retrofit;
    APIservice apiService;

    //naver
    OAuthLogin mOAuthLoginModule;
    Context mContext;
    String OAUTH_CLIENT_ID = "WT_eWypnTZKUsRmf0AUo";
    String OAUTH_CLIENT_SECRET = "Pz0yvLT9nC";
    String OAUTH_CLIENT_NAME = "TripBus";
    //facebook
    private CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //naver
        mOAuthLoginModule = OAuthLogin.getInstance();
        mOAuthLoginModule.init(
                LoginActivity.this
                ,OAUTH_CLIENT_ID
                ,OAUTH_CLIENT_SECRET
                ,OAUTH_CLIENT_NAME

        );

        callbackManager = CallbackManager.Factory.create();

        //툴바
        Toolbar mToolbar = (Toolbar) findViewById(R.id.login_toolbar);
        setSupportActionBar(mToolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setElevation(0);

        session = new SessionManager(getApplicationContext());

        Gson gson = new GsonBuilder().setLenient().create();
        retrofit = new Retrofit.Builder().baseUrl(apiService.API_URL).addConverterFactory(GsonConverterFactory.create(gson)).build();
        apiService = retrofit.create(APIservice.class);

        inputid = (EditText) findViewById(R.id.id);
        inputpassword = (EditText) findViewById(R.id.password);

        login = (Button) findViewById(R.id.button_login);
        naver = (Button) findViewById(R.id.button_naver);
        facebook = (Button) findViewById(R.id.button_facebook);
        register = (Button) findViewById(R.id.button_register);

        login.setOnClickListener(onClickListener);
        naver.setOnClickListener(onClickListener);
        facebook.setOnClickListener(onClickListener);
        register.setOnClickListener(onClickListener);
    }

    Button.OnClickListener onClickListener;

    {
        onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.button_login:
                        String id = inputid.getText().toString().trim();
                        String password = inputpassword.getText().toString().trim();

                        // Check for empty data in the form
                        if (!id.isEmpty() && !password.isEmpty()) {
                            checkLogin(id, password); //로그인 데이터를 서버로 보내고 성공여부 값을 받는 함수
                        } else {
                            Toast.makeText(getApplicationContext(), "아이디와 비밀번호를 입력해주세요.", Toast.LENGTH_LONG).show();
                        }
                        break;

                    case R.id.button_register:
                        /* 회원가입페이지는 어떻게 되는지?
                         */

                        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                        startActivity(intent);
                        finish();
                        break;

                    case R.id.button_naver:
                        mOAuthLoginModule.startOauthLoginActivity(LoginActivity.this, mOAuthLoginHandler);
                        break;

                    case R.id.button_facebook:
                        loginFacebook();
                        break;
                }
            }
        };
    }

    //일반로그인--------------------------------------------------------------------------------------------------------------------

    public void checkLogin(final String id, final String password) {

        Call<userConnect> comment2 = apiService.login(id, password);
        comment2.enqueue(new Callback<userConnect>() {
            @Override
            public void onResponse(Call<userConnect> call, Response<userConnect> response) {
                //Log.v("Test", response.body().string());
                userConnect u = response.body();

                if(u.getSuccess()) { //로그인에 성공했을 경우
                    //세션에 아이디를 저장시키기
                    session.createLoginSession(id);
                    finish(); //다시 메인화면으로 돌아가기
                }
                else //로그인에 실패했을 경우
                    Toast.makeText(mContext, "등록되지 않은 아이디이거나 비밀번호가 틀립니다!", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<userConnect> call, Throwable t) {
                Log.d("userRetrofit", "서버 통신에 오류가 있음");
            }


        });

    }


    //네이버-------------------------------------------------------------------------------------------------------------------
    private OAuthLoginHandler mOAuthLoginHandler = new OAuthLoginHandler() {
        @Override
        public void run(boolean success) {
            if (success) {
                final String accessToken = mOAuthLoginModule.getAccessToken(mContext);
                ProfileTask task = new ProfileTask();
                // 이 클래스가 유저정보를 가져오는 업무를 담당합니다.
                task.execute(accessToken);

                String refreshToken = mOAuthLoginModule.getRefreshToken(mContext);
                long expiresAt = mOAuthLoginModule.getExpiresAt(mContext);
                String tokenType = mOAuthLoginModule.getTokenType(mContext);
            } else {
                String errorCode = mOAuthLoginModule.getLastErrorCode(mContext).getCode();
                String errorDesc = mOAuthLoginModule.getLastErrorDesc(mContext);
                Toast.makeText(mContext, "errorCode:" + errorCode
                        + ", errorDesc:" + errorDesc, Toast.LENGTH_SHORT).show();
            }
        }
    };


    class ProfileTask extends AsyncTask<String, Void, String> {
        String result;

        @Override
        protected String doInBackground(String... strings) {
            String token = strings[0];// 네이버 로그인 접근 토큰;
            String header = "Bearer " + token; // Bearer 다음에 공백 추가
            try {
                String apiURL = "https://openapi.naver.com/v1/nid/me";
                URL url = new URL(apiURL);
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("GET");
                con.setRequestProperty("Authorization", header);
                int responseCode = con.getResponseCode();
                BufferedReader br;
                if (responseCode == 200) { // 정상 호출
                    br = new BufferedReader(new InputStreamReader(con.getInputStream()));
                } else {  // 에러 발생
                    br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
                }
                String inputLine;
                StringBuffer response = new StringBuffer();

                while ((inputLine = br.readLine()) != null) {
                    response.append(inputLine);
                }
                result = response.toString();
                br.close();
                System.out.println(response.toString());
            } catch (Exception e) {
                System.out.println(e);
            }
            //result 값은 JSONObject 형태로 넘어옵니다.
            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try {
                //넘어온 result 값을 JSONObject 로 변환해주고, 값을 가져오면 되는데요.
                // result 를 Log에 찍어보면 어떻게 가져와야할 지 감이 오실거에요.
                JSONObject object = new JSONObject(result);
                if (object.getString("resultcode").equals("00")) {
                    JSONObject jsonObject = new JSONObject(object.getString("response"));

                    //editor.putString("email", jsonObject.getString("email"));
                    //editor.putString("name", jsonObject.getString("name"));
                    //editor.putString("profile", jsonObject.getString("profile_image"));
                    session.createLoginSession(jsonObject.getString(("id")));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    //페이스북----------------------------------------------------------------------------------------------------------------------
    public void loginFacebook() {

        LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("email"));
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        Log.d("getAccessToken()", String.valueOf(loginResult.getAccessToken()));
                        Log.d("getId()", String.valueOf(Profile.getCurrentProfile().getId()));
                        Log.d("getName()", String.valueOf(Profile.getCurrentProfile().getName())); // 이름
                        getUserEmail(loginResult);

                        session.createLoginSession(String.valueOf(Profile.getCurrentProfile().getId()));
                    }

                    @Override
                    public void onCancel() {
                        // App code
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        // App code
                    }
                });
    }

    public void getUserEmail(LoginResult loginResult){
        GraphRequest request = GraphRequest.newMeRequest(loginResult.getAccessToken(),
                new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        try {
                            String email = response.getJSONObject().getString("email");
                            Log.d("email", email);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                });
        Bundle parameters = new Bundle();
        parameters.putString("fields", "email");
        request.setParameters(parameters);
        request.executeAsync();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }


    //툴바-------------------------------------------------------------------------------------------------------------------------
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:{ //toolbar의 back키 눌렀을 때 동작

                Intent i = new Intent(LoginActivity.this, PlaceActivity.class);
                startActivity(i);
                finish();
                /*
                finish();
                return true;
                */
            }
        }
        return super.onOptionsItemSelected(item);
    }

}
