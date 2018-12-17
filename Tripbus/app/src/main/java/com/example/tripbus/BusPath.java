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
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


public class BusPath extends AppCompatActivity {
    SessionManager session;
    ImageButton goBack, scrap;
    Button morePath;
    ImageView image, icon, Nimage;
    TextView Bname, Pname, maptxt;
    ListView pathList24, pathList135 ;
    ArrayList<bus_pathitem> pathArr = new ArrayList<bus_pathitem>();
    pathAdapter pAdapter = null;
    Retrofit retrofit;
    APIservice apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_path);

        goBack = (ImageButton) findViewById(R.id.back);
        scrap = (ImageButton) findViewById(R.id.scrap);
        morePath = (Button) findViewById(R.id.seeMore);
        image = (ImageView) findViewById(R.id.bus);
        Nimage = (ImageView) findViewById(R.id.step12345);
        icon = (ImageView) findViewById(R.id.gps);
        Bname = (TextView) findViewById(R.id.bus_name);
        Pname = (TextView) findViewById(R.id.rcmnd_place);
        maptxt = (TextView) findViewById(R.id.seeinmap);
        pathList24 = (ListView) findViewById(R.id.path24);
        pathList135 = (ListView) findViewById(R.id.path135);


        pAdapter = new pathAdapter(BusPath.this, pathArr);
        pathList24.setAdapter(pAdapter);
        pathList135.setAdapter(pAdapter);

        View.OnClickListener onClickListener = null;

        goBack.setOnClickListener(onClickListener);
        scrap.setOnClickListener(onClickListener);
        morePath.setOnClickListener(onClickListener);
    }

    public void getPath() {
        /*
        Call<place> comment2 = apiService.getPostPlace();
        comment2.enqueue(new Callback<place>() {
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

                    case R.id.seeMore:
                        break;
                }
            }
        };
    }



}
