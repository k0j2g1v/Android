package com.example.androidsample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;

public class DataFromActivity extends AppCompatActivity {

    private String selectedItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_from);

        final ArrayList<String> list = new ArrayList<String>();
        list.add("수박");
        list.add("바나나");
        list.add("딸기");
        list.add("멜론");

        Spinner spinner = (Spinner)findViewById(R.id.spinner);

        // adapter가 필요
        ArrayAdapter adapter = new ArrayAdapter(getApplicationContext(),
                android.R.layout.simple_spinner_dropdown_item, list);



        spinner.setAdapter(adapter);
        // spinner에서 item을 선택하는 이벤트 처리가 필요

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedItem = (String)list.get(i);
                Log.i("selectedTest","선택된 과일: "+selectedItem);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        Button sendDataBtn = (Button)findViewById(R.id.sendMsgBtn);
        sendDataBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){


                Intent resultInent = new Intent();
                resultInent.putExtra("DATA",selectedItem);
                setResult(5000,resultInent);
                DataFromActivity.this.finish();
            }
        });

    }
}