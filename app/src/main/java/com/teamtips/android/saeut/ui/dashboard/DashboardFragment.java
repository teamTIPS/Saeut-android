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

public class DashboardFragment extends Fragment {

    private DashboardViewModel dashboardViewModel;
    private ViewPager viewPager ;
    private ScreenSlidePagerActivity pagerAdapter ;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        return (ViewGroup) inflater.inflate(
                R.layout.fragment_dashboard, container, false);
    }
}
