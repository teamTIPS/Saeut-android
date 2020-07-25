package com.teamtips.android.saeut;

import android.os.Bundle;
import android.view.ViewGroup;
import androidx.appcompat.app.AppCompatActivity;
import net.daum.mf.map.api.MapView;

public class MapsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        MapView mapView = new MapView(this);

        ViewGroup mapViewContainer = findViewById(R.id.map_view);
        mapViewContainer.addView(mapView);
    }
}