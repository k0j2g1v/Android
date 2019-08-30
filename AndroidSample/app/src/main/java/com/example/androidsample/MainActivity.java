package com.example.androidsample;

import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

// 이벤트 처리객체를 만들어야 하기 때문에 일단
// class부터 정의.

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // btn1 : Event Source
        Button btn1 = (Button)findViewById(R.id.btn1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Intent i = new Intent();
                ComponentName cname = new ComponentName("com.example.androidsample",
                        "com.example.androidsample.LinearLayoutExampleActivity");
                i.setComponent(cname);
                startActivity(i);
            }
        });
        // btn2 : Event Source
        Button btn2 = (Button)findViewById(R.id.btn2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Intent i = new Intent();
                ComponentName cname = new ComponentName("com.example.androidsample",
                        "com.example.androidsample.activity_chat_example");
                i.setComponent(cname);
                startActivity(i);
            }
        });
        // btn3 : Event Source
        Button btn3 = (Button)findViewById(R.id.btn3);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Intent i = new Intent();
                ComponentName cname = new ComponentName("com.example.androidsample",
                        "com.example.androidsample.ImageActivity");
                i.setComponent(cname);
                startActivity(i);
            }
        });
        // btn4 : Event Source
        Button btn4 = (Button)findViewById(R.id.btn4);
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Intent i = new Intent();
                ComponentName cname = new ComponentName("com.example.androidsample",
                        "com.example.androidsample.TouchActivity");
                i.setComponent(cname);
                startActivity(i);
            }
        });
        // btn5 : Event Source
        Button btn5 = (Button)findViewById(R.id.btn5);
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Intent i = new Intent();
                ComponentName cname = new ComponentName("com.example.androidsample",
                        "com.example.androidsample.SwapActivity");
                i.setComponent(cname);
                startActivity(i);
            }
        });
        // btn6: Event source
        Button btn6 = (Button)findViewById(R.id.btn6);
        btn6.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                final EditText et = new EditText(MainActivity.this);
                // AlertDialog를 생성하기
                AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                dialog.setTitle("Activity에 데이터전달");
                dialog.setMessage("전달할 내용을 입력하세요!!");
                dialog.setView(et);
                dialog.setPositiveButton("Activity 호출",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //다른 activity를 호출하는 코드
                                Intent intent = new Intent();
                                ComponentName cname = new ComponentName("com.example.androidsample",
                                        "com.example.androidsample.SecondActivity");
                                intent.setComponent(cname);
                                intent.putExtra("sendMsg",et.getText().toString());
                                startActivity(intent);

                            }
                        });
                dialog.setNegativeButton("취소", new
                        DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                // 취소 버튼을 눌렀을 때
                            }
                        });
                dialog.show();

            }
        });

        // btn7 : Event Source
        Button btn7 = (Button)findViewById(R.id.btn7);
        // 익명 내부 클래스
        btn7.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent i = new Intent();
                ComponentName cname = new ComponentName("com.example.androidsample",
                        "com.example.androidsample.DataFromActivity");
                i.setComponent(cname);
                startActivityForResult(i, 3000);
            }
        });

        // btn8 : Event Source
        Button btn8 = (Button)findViewById(R.id.btn8);
        // 익명 내부 클래스
        btn8.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent i = new Intent();
                ComponentName cname = new ComponentName("com.example.androidsample",
                        "com.example.androidsample.ANRActivity");
                i.setComponent(cname);
                startActivity(i);
            }
        });

        // btn9 : Event Source
        Button btn9 = (Button)findViewById(R.id.btn9);
        btn9.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent i = new Intent();
                ComponentName cname = new ComponentName("com.example.androidsample",
                        "com.example.androidsample.NoCounterActivity");
                i.setComponent(cname);
                startActivityForResult(i, 3000);
            }
        });

        // btn10 : Event Source
        Button btn10 = (Button)findViewById(R.id.btn10);
        btn10.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent i = new Intent();
                ComponentName cname = new ComponentName("com.example.androidsample",
                        "com.example.androidsample.CounterActivity");
                i.setComponent(cname);
                startActivityForResult(i, 3000);
            }
        });

        // btn11 : Event Source
        Button btn11 = (Button)findViewById(R.id.btn11);
        btn11.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent i = new Intent();
                ComponentName cname = new ComponentName("com.example.androidsample",
                        "com.example.androidsample.BookSearchActivity");
                i.setComponent(cname);
                startActivityForResult(i, 3000);
            }
        });
        // btn12 : Event Source
        Button btn12 = (Button)findViewById(R.id.btn12);
        btn12.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent i = new Intent();
                ComponentName cname = new ComponentName("com.example.androidsample",
                        "com.example.androidsample.MovieSearchActivity");
                i.setComponent(cname);
                startActivityForResult(i, 3000);
            }
        });
        // btn13 : Event Source
        Button btn13 = (Button)findViewById(R.id.btn13);
        btn13.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent i = new Intent();
                ComponentName cname = new ComponentName("com.example.androidsample",
                        "com.example.androidsample.CustomBookSearchActivity");
                i.setComponent(cname);
                startActivity(i);
            }
        });
        // btn14 : Implicit intent Source
        Button btn14 = (Button)findViewById(R.id.btn14);
        btn14.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                // 명시적 인텐트(Explicit Intent)
                Intent i = new Intent();
                ComponentName cname = new ComponentName("com.example.androidsample",
                        "com.example.androidsample.IntentTestActivity");
                i.setComponent(cname);
                startActivity(i);
            }
        });
        // btn 15 : Service Source serviceStartBtn
        Button serviceStartBtn = (Button)findViewById(R.id.serviceStartBtn);
        serviceStartBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                // 서비스 객체를 실행!
                Intent i = new Intent();
                ComponentName cname = new ComponentName("com.example.androidsample",
                        "com.example.androidsample.LifeCycleService");
                i.setComponent(cname);
                i.putExtra("ActivityToServiceData","소리없는 아우성!!");
                // 서비스 클래스를 찾아서 객체화 시키고 실행!
                startService(i);
            }
        });
        // 15 : Service Source serviceStopBtn
        Button serviceStopBtn = (Button)findViewById(R.id.serviceStopBtn);
        serviceStopBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                // 서비스 객체를 종료!
                Intent i = new Intent();
                ComponentName cname = new ComponentName("com.example.androidsample",
                        "com.example.androidsample.LifeCycleService");
                i.setComponent(cname);
                // 서비스 클래스를 찾아서 객체화 시키고 실행!
                stopService(i);
            }
        });

        // btn 15 : kakaobook
        Button btn15 = (Button)findViewById(R.id.btn15);
        btn15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Broadcast
                Intent i = new Intent();
                i.setAction("START_BROADCAST_ACTIVITY");
                startActivity(i);
            }
        });
        // btn 16 :
        Button btn16 = (Button)findViewById(R.id.btn16);
        btn16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent();
                i.setAction("START_DATABASE_ACTIVITY");
                startActivity(i);
            }
        });
        // btn 17 :
        Button btn17 = (Button)findViewById(R.id.btn17);
        btn17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent();
                i.setAction("Contact_ACTIVITY");
                startActivity(i);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode,
                                    int resultCode,
                                    @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==3000&&resultCode==5000){
            String result = data.getExtras().getString("DATA");
            Toast.makeText(this,result,Toast.LENGTH_SHORT).show();

        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        String msg = intent.getExtras().getString("ServiceToActivityData");
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }
}




