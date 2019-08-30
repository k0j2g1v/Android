package com.example.androidsample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

class Mysum implements Runnable{
    private  TextView tv;

    Mysum(TextView tv){
        this.tv = tv;

    }
    @Override
    public void run() {
        long sum = 0;
        for(long i=0; i<10000000L; i++){
            sum +=i;
        }
        tv.setText("총합은 :"+sum);
        // Thread가 실행이 되면 수행되는 코드를 여기에 작성
    }
}

public class ANRActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anr);

        final TextView tv = (TextView)findViewById(R.id.countTv);
        Button countBtn = (Button)findViewById(R.id.countBtn);
        Button toastBtn = (Button)findViewById(R.id.toastBtn);

        countBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Mysum mysum = new Mysum(tv);

                Thread t = new Thread(mysum);

                t.start();



            }
        });
        toastBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Toast.makeText(ANRActivity.this, "Toast가 실행",
                        Toast.LENGTH_SHORT).show();
            }
        });

    }
}
