package com.example.androidsample;

import android.Manifest;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MyContactActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_contact);

        Button contactBtn = (Button)findViewById(R.id.contactBtn);
        contactBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 보안관련 코드가 나온다
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                    // 마쉬멜로우 버젼(6)보다 높은 경우
                    if(checkSelfPermission(Manifest.permission.READ_CONTACTS) !=
                            PackageManager.PERMISSION_GRANTED){
                        // 이전에 허용을 한적이 없는경우
                        // 사용자의 허가를 얻어야 한다
                        requestPermissions(new String[]{Manifest.permission.READ_CONTACTS},1111);
                    } else {
                        // 에전에 이미 허용버튼을 누른 경우
                        ContentResolver cr = getContentResolver();
                        Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
                        // content://com.test.data/Member
                        Cursor c = cr.query(uri,null,null,null,null);
                        String result = "";
                        while(c.moveToNext()){
                            result += c.getString(c.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                            result += ", ";
                            result += c.getString(c.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                            result += "\n";
                        }
                        final TextView contactTv = (TextView)findViewById(R.id.contactTv);
                        contactTv.setText(result);

                    }
                } else{
                    // 마쉬멜로우 버젼(6)보다 낮을 경우
                }
            }
        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == 1111){
            try {
                if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    // 사용자가 주소록 접근에 대한 권한요청에 허용을 누르면
                    // ContentResolver를 이용해서 주소록에 접근
                    ContentResolver cr = getContentResolver();
                    Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
                    // content://com.test.data/Member
                    Cursor c = cr.query(uri,null,null,null,null);
                    String result = "";
                    while(c.moveToNext()){
                        result += c.getString(c.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                        result += ", ";
                        result += c.getString(c.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                        result += "\n";
                    }
                    final TextView contactTv = (TextView)findViewById(R.id.contactTv);
                    contactTv.setText(result);

                }
            }catch (Exception e){
                Log.i("ddd",e.toString());
            }
        }
    }
}
