package com.example.androidsample;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

class MyCounter1 implements Runnable{

    private Handler handler;

    MyCounter1(Handler handler){
        this.handler = handler;
    }

    @Override
    public void run() {
        for(int i = 0; i<10 ; i++){
            try {
                Thread.sleep(1000);
                Bundle bundle = new Bundle();
                bundle.putString("num",i+"");
                Message msg = new Message();
                msg.setData(bundle);
                handler.sendMessage(msg);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

public class CounterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_counter);

        final TextView tv1 = (TextView) findViewById(R.id.counterTv1);
        Button counterStartBtn1 = (Button)findViewById(R.id.startBtn1);

        final Handler handler = new Handler(){
            // 메세지를 받는 순간 아래 메서드 호출;;
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                Bundle b = msg.getData();
                tv1.setText(b.getString("num"));
            }
        };



        counterStartBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyCounter1 myCounter1 = new MyCounter1(handler);
                Thread t = new Thread(myCounter1);
                t.start();
            }
        });



    }
}
