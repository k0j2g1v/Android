package com.example.androidsample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.Toast;

public class SwapActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swap);
    }
    float start_x = 0;
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        int action = event.getAction();
        Log.i("myTest","action : "+action+" x : "+ x +" y :" + y);
        if( action == MotionEvent.ACTION_DOWN ){
            start_x = x;

        } else if ( action == MotionEvent.ACTION_UP ){
            if( start_x < x && Math.abs(start_x - x) > 50){
                Toast.makeText(this, "Left Swipe", Toast.LENGTH_SHORT).show();
            } else if( start_x > x && Math.abs(start_x - x) > 50 ){
                Toast.makeText(this,"Right swipe", Toast.LENGTH_SHORT).show();
            }
        }

        return super.onTouchEvent(event);
    }
}

