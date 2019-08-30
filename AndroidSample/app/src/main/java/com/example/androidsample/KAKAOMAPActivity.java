package com.example.androidsample;

import android.os.Bundle;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;

import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapView;

public class KAKAOMAPActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kakaomap);

        MapView map = new MapView(this);
        ViewGroup group = (ViewGroup)findViewById(R.id.mapll);

        MapPoint mapPoint = MapPoint.mapPointWithGeoCoord(37.501336, 127.039664);
        map.setMapCenterPoint(mapPoint,true);
        group.addView(map);

    }
}
