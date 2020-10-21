package com.teamtips.android.saeut;

import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.teamtips.android.saeut.func.community.CommunityFragment;
import com.teamtips.android.saeut.func.dashboard.DashboardFragment;
import com.teamtips.android.saeut.func.group.GroupFragment;
import com.teamtips.android.saeut.func.home.HomeFragment;
import com.teamtips.android.saeut.func.map.MapFragment;
import com.teamtips.android.saeut.func.profile.ProfileFragment;
import com.teamtips.android.saeut.func.timetable.ScheduleFragment;

public class BottomNavItemSelectedListener
        implements BottomNavigationView.OnNavigationItemSelectedListener {

    private final Toolbar toolbar;
    private final FragmentManager fragmentManager;

    public BottomNavItemSelectedListener(FragmentManager fragmentManager, Toolbar toolbar) {
        this.toolbar = toolbar;
        this.fragmentManager = fragmentManager;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        toolbar.setTitle(item.getTitle());
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        int itemId = item.getItemId();
        switch(itemId){
            case R.id.navigation_home:
                transaction.replace(R.id.container_main, new HomeFragment()).commit();
                return true;
            case R.id.navigation_community:
                transaction.replace(R.id.container_main, new CommunityFragment()).commit();
                return true;
            case R.id.navigation_dashboard:
                transaction.replace(R.id.container_main, new DashboardFragment()).commit();
                return true;
            case R.id.navigation_group:
                transaction.replace(R.id.container_main, new GroupFragment()).commit();
                return true;
            case R.id.navigation_profile:
                transaction.replace(R.id.container_main, new ProfileFragment()).commit();
                return true;
        }
        return true;
    }
}
