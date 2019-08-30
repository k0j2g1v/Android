package com.example.myresolverapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView tv = (TextView)findViewById(R.id.tv);
        Button selectBtn = (Button)findViewById(R.id.selectBtn);
        selectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ContentResolver cr = getContentResolver();
                Uri uri = Uri.parse("content://com.test.data/Member");
                Cursor c = cr.query(uri,null,null,null,null);
                String result = "";
                while(c.moveToNext()){
                    result += c.getString(0);
                    result += ", ";
                    result += c.getString(1);
                    result += "\n";
                }
                tv.setText(result);
            }
        });

    }
}
