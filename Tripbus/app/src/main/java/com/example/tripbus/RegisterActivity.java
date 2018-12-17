package com.example.tripbus;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RegisterActivity extends AppCompatActivity {
    EditText ipId, ipPassword, ipPasswordcheck;
    Button register;
    Retrofit retrofit;
    APIservice apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        if (Build.VERSION.SDK_INT >= 21) {
            // 21 버전 이상일 때
            getWindow().setStatusBarColor(Color.rgb(209, 239, 255));
        }

        Gson gson = new GsonBuilder().setLenient().create();
        retrofit = new Retrofit.Builder().baseUrl(apiService.API_URL).addConverterFactory(GsonConverterFactory.create(gson)).build();
        apiService = retrofit.create(APIservice.class);

        Toolbar mToolbar = (Toolbar) findViewById(R.id.register_toolbar);
        setSupportActionBar(mToolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setElevation(0);


        ipId = (EditText) findViewById(R.id.inputId);
        ipPassword = (EditText) findViewById(R.id.inputPw);
        ipPasswordcheck = (EditText) findViewById(R.id.inputPwc);

        register = (Button) findViewById(R.id.button_rg);

        // Register Button Click event
        register.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {

                String id = ipId.getText().toString().trim();
                String password = ipPassword.getText().toString().trim();
                String passwordCheck = ipPasswordcheck.getText().toString().trim();

                if(!password.equals(passwordCheck))
                    Toast.makeText(getApplicationContext(),
                            "비밀번호와 비밀번호 확인값이 일치하지 않습니다.", Toast.LENGTH_LONG).show();
                else {
                    if (!id.isEmpty() && !password.isEmpty() && !passwordCheck.isEmpty()) {
                        registerUser(id, password);

                    } else {
                        Toast.makeText(getApplicationContext(),
                                "모든 회원정보를 입력해주세요.", Toast.LENGTH_LONG)
                                .show();
                    }
                }
            }
        });

    };


    private void registerUser(final String id, final String password) {
        // Tag used to cancel the request
        String tag_string_req = "req_register";
        Call<userConnect> comment2 = apiService.register(id, password);
        comment2.enqueue(new Callback<userConnect>() {
            @Override
            public void onResponse(Call<userConnect> call, Response<userConnect> response) {
                //Log.v("Test", "Response Body is " + response.body().toString());
                userConnect u = response.body();

                if(u.getSuccess()) {
                    finish(); //다시 메인화면으로 돌아가기
                    Toast.makeText(getApplicationContext(), "회원가입이 완료되었습니다! 로그인하세요 :)", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<userConnect> call, Throwable t) {
                Log.d("userRetrofit", t.toString());
            }


        });



    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:{ //toolbar의 back키 눌렀을 때 동작

                finish();
                return true;

            }
        }
        return super.onOptionsItemSelected(item);
    }

}
