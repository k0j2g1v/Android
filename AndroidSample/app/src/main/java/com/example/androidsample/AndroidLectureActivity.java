package com.example.androidsample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class AndroidLectureActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android_lecture);

        final TextView tv = (TextView)findViewById(R.id.tv);
        final TextView uId = (TextView)findViewById(R.id.userid);
        final EditText et = (EditText)findViewById(R.id.et);
        Button sendBtn = (Button)findViewById(R.id.sendBtn);
        tv.setMovementMethod(ScrollingMovementMethod.getInstance());
        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv.append(uId.getText()+">>"+et.getText()+"\n");
                
                //스크롤을 해야 하는지 판단해서
                tv.scrollTo(0,100);
            }
        });
    }
}
