package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        setContentView(R.layout.mylinear_layout);
        Log.i("myTest","onCreate() 호출");
    }
    @Override
    protected void onStart(){
        super.onStart();
        Log.i("myTest","onStart() 호출");
    }
    @Override
    protected  void onResume(){
        super.onResume();
        Log.i("myTest","onResume() 호출");
    }
    @Override
    protected  void onPause(){
        super.onPause();
        Log.i("myTest","onPause() 호출");
    }
    @Override
    protected  void onStop(){
        super.onStop();
        Log.i("myTest","onStop() 호출");
    }
    @Override
    protected  void onDestroy(){
        super.onDestroy();
        Log.i("myTest","onDestroy() 호출");
    }
}
