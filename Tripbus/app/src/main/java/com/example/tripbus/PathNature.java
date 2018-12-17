package com.example.tripbus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class PathNature extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_path_nature);
//        ImageButton button = (ImageButton)findViewById(R.id.rcmnd_nature);
//
//        button.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(getApplicationContext(), "이전 액티비티로 돌아갑니다.", Toast.LENGTH_LONG).show();
//
//                finish();
//            }
//        });

//        ImageButton button1 = (ImageButton)findViewById(R.id.path4432);
//        button1.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(getApplicationContext(), "4432노선", Toast.LENGTH_LONG).show();
//
//                // 액티비티 전환 코드
//                Intent intent = new Intent(getApplicationContext(), bus4432.class);
//                startActivity(intent);
//            }
//        });
//
//        ImageButton button2 = (ImageButton)findViewById(R.id.path3412);
//        button2.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(getApplicationContext(), "3412노선", Toast.LENGTH_LONG).show();
//
//                // 액티비티 전환 코드
//                Intent intent = new Intent(getApplicationContext(), bus3412.class);
//                startActivity(intent);
//            }
//        });
    }
}
