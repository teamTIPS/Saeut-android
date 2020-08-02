package com.teamtips.android.saeut.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import com.teamtips.android.saeut.R;

import java.util.ArrayList;

public class DashboardFragment extends Fragment {

    private DashboardViewModel dashboardViewModel;
    private ViewPager viewPager ;
    private ScreenSlidePagerActivity pagerAdapter ;

    private ArrayList<Integer> imageList;
    private static final int DP = 24;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        container = (ViewGroup) inflater.inflate(
                R.layout.fragment_dashboard, container, false);

        this.initializeData();

        ViewPager viewPager = container.findViewById(R.id.viewPager);
        viewPager.setClipToPadding(false);

        float density = getResources().getDisplayMetrics().density;
        int margin = (int) (DP * density);
        viewPager.setPadding(margin, 0, margin, 0);
        viewPager.setPageMargin(margin/2);

        viewPager.setAdapter(new ViewPagerAdapter(container.getContext(), imageList));

        return container;
    }

    public void initializeData()
    {
        imageList = new ArrayList();

        imageList.add(R.drawable.ic_launcher_background);
        imageList.add(R.drawable.ic_profile);
    }
}
