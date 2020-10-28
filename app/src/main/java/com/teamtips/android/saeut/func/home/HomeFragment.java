package com.teamtips.android.saeut.func.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.teamtips.android.saeut.MainActivity;
import com.teamtips.android.saeut.R;
import com.teamtips.android.saeut.func.dashboard.DashboardFragment;
import com.teamtips.android.saeut.func.dashboard.DashboardListFragment;

import de.hdodenhof.circleimageview.CircleImageView;

public class HomeFragment extends Fragment {

    private static final String Tag = "HomeFragment";
    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    CircleImageView group_icon;
    CircleImageView child_icon;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        child_icon = view.findViewById(R.id.child_icon);
        group_icon = view.findViewById(R.id.group_icon);

        child_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // getActivity()로 MainActivity의 replaceFragment를 불러옵니다.
                ((MainActivity)getActivity()).replaceFragment(new DashboardListFragment(4));
                ((MainActivity)getActivity()).initTitle("아이돌봄");
                // 새로 불러올 Fragment의 Instance를 Main으로 전달
            }
        });

        group_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // getActivity()로 MainActivity의 replaceFragment를 불러옵니다.
                ((MainActivity)getActivity()).replaceFragment(new DashboardListFragment(5));
                ((MainActivity)getActivity()).initTitle("함께돌봄");
                // 새로 불러올 Fragment의 Instance를 Main으로 전달
            }
        });
    }

    private static class HomeFragmentHolder {

    }
}
