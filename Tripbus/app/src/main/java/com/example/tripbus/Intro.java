package com.example.tripbus;
import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;

public class Intro extends SearchPath {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                Intent intent = new Intent(Intro.this, SearchPath.class);
                startActivity(intent);
                finish();


            }
        }, 3000);

    }
}

//public class MenuActivity extends Activity{
  //  @Override
    //public void onCreate(Bundle saved InstanceState){
      //  super.onCreate(savedInstanceState);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        //setContentView(R.layout.searchpath);
    //}

//}


