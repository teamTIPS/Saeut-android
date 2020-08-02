package com.teamtips.android.saeut.ui.map;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.teamtips.android.saeut.R;

import net.daum.mf.map.api.MapView;

import java.security.MessageDigest;

public class MapsActivity extends Fragment {

    static ViewGroup mapViewContainer = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MapView mapView = new MapView(getContext());

        mapViewContainer = mapViewContainer.findViewById(R.id.map_view);
        mapViewContainer.addView(mapView);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_maps, container, false);

        return view;
    }

}