package com.example.tripbus;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class PlaceActivity extends AppCompatActivity {
    SessionManager session;
    ImageButton goBack, scrap;
    Button moreReview;
    ImageView image, icon;
    TextView name, Ename, address;
    ListView reviewList;
    ArrayList<reviewItem> reviewArr = new ArrayList<reviewItem>();
    reviewAdapter rAdapter = null;
    Retrofit retrofit;
    APIservice apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place);

        //일반 뷰들 생성
        goBack = (ImageButton) findViewById(R.id.back);
        scrap = (ImageButton) findViewById(R.id.back);
        moreReview = (Button) findViewById(R.id.reviewMore);
        image = (ImageView) findViewById(R.id.placeImage);
        icon = (ImageView) findViewById(R.id.placeIcon);
        name = (TextView) findViewById(R.id.placeName);
        Ename = (TextView) findViewById(R.id.placeEName);
        address = (TextView) findViewById(R.id.placeAddress);
        reviewList = (ListView) findViewById(R.id.reviewList);

        /*
        //리뷰쓰는 밑의 뷰들 생성
        View footer = getLayoutInflater().inflate(R.layout.reviewlist_footer, null, false);
        reviewList.addFooterView(footer);
        EditText reviewText = (EditText) footer.findViewById(R.id.reviewText);
        RatingBar star = (RatingBar) footer.findViewById(R.id.rwratingbar);
        Button writeReview = (Button) footer.findViewById(R.id.reviewWrite);
        */

        session = new SessionManager(getApplicationContext());

        /*
        //이전 페이지에서 버스인덱스번호 가져오기
        Intent intent = getIntent();
        int tour_idx = intent.getIntExtra("tour_idx", 0);
        Log.d("tour_idx", tour_idx + " ");
        */
        //임시 투어번호
        int tour_idx = 1;

        getPlace(tour_idx);
        getReview(tour_idx);

        rAdapter = new reviewAdapter(PlaceActivity.this, reviewArr);
        reviewList.setAdapter(rAdapter); //리스트뷰의 어댑터 설정하기

        goBack.setOnClickListener(onClickListener);
        scrap.setOnClickListener(onClickListener);
        moreReview.setOnClickListener(onClickListener);
    }


    public void getPlace(int idx) { //서버로부터 장소 정보를 가져와 뿌려줄것

        Call<place> call = apiService.place(idx);
        call.enqueue(new Callback<place>() {
            @Override
            public void onResponse(Call<place> call, Response<place> response) {
                //Log.v("Test", response.body().string());
                //LoginActivity.user u = response.body();

            }

            @Override
            public void onFailure(Call<place> call, Throwable t) {
                Log.d("userRetrofit", "서버 통신에 오류가 있음");
            }


        });

    }

    public void getReview(int idx) { //서버로부터 리뷰 정보를 가져와 뿌려줄것
        /*
        Call<place> comment2 = apiService.getPostPlace();
        comment2.enqueue(new Callback<place>() {
            @Override
            public void onResponse(Call<place> call, Response<place> response) {
                //Log.v("Test", response.body().string());
                //LoginActivity.user u = response.body();
                //Glide.with(this).load("http://www.selphone.co.kr/homepage/img/team/3.jpg").into(imageView); glide사용할것

            }

            @Override
            public void onFailure(Call<place> call, Throwable t) {
                Log.d("userRetrofit", "서버 통신에 오류가 있음");
            }


        });
        */
    }


    Button.OnClickListener onClickListener;

    {
        onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.back:
                        finish();
                        break;

                    case R.id.scrap:

                        break;

                    case R.id.reviewMore:
                        break;
                }
            }
        };
    }




}
