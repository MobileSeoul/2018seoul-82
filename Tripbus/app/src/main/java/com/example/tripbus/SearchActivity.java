package com.example.tripbus;
import android.app.Activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import android.os.AsyncTask;
import android.os.Bundle;

import android.text.InputFilter;

import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import info.androidhive.loginandregistration.R;
import com.android.crystalmarket.app.AppConfig;
import com.android.crystalmarket.app.AppController;

import android.widget.AdapterView.OnItemSelectedListener;

public class SearchActivity extends Activity{
    ImageView img1, img2, img3;
    EditText et;

    // LogCat tag
    private static final String TAG = SearchActivity.class.getSimpleName();
    private ProgressDialog pDialog;//다이얼로그

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_path);

        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        img1 = (ImageView) findViewById(R.id.imageView1);
        img2 = (ImageView) findViewById(R.id.imageView3);
        img3 = (ImageView) findViewById(R.id.imageView4);
        et = (EditText) findViewById(R.id.editText1);



        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        }); //뒤로가기 버튼

        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!et.getText().toString().equals("")) {
                    String searchword = et.getText().toString();

                    Intent intent = new Intent(getApplicationContext(),SearchpdActivity.class);
                    intent.putExtra("searchword", searchword);
                    startActivityForResult(intent, 3000);
                }
            }
        });

        img3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et.getText().clear();
            }
        });

        et.setFilters(new InputFilter[]{
                new InputFilter.LengthFilter(13)
        }); //et 글자수 제한


    }   //onCreate()

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch(requestCode) {
            case 3000:
                Intent i = getIntent();
                String searchword = i.getStringExtra("searchword");
                et.setText(searchword);

                break;
        }
    }

}