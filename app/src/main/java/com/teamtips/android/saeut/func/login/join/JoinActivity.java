package com.teamtips.android.saeut.func.login.join;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.teamtips.android.saeut.R;
import com.teamtips.android.saeut.func.login.join.ui.main.JoinFragment_essential;

public class JoinActivity extends FragmentActivity {

    JoinFragment_essential joinFragmentEssential;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        joinFragmentEssential = new JoinFragment_essential();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.container_join, joinFragmentEssential);
        transaction.commit();
    }
}