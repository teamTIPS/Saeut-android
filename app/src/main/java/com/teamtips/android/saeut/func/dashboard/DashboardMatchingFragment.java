package com.teamtips.android.saeut.func.dashboard;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.teamtips.android.saeut.R;
import com.teamtips.android.saeut.data.Post;
import com.teamtips.android.saeut.func.dashboard.service.PostNetworkTask;
import com.teamtips.android.saeut.func.login.data.model.LoggedInUser;
import java.util.ArrayList;

public class DashboardMatchingFragment extends Fragment {

    private static final String ARGUMENT_POSITION = "argument_position";
    public static final String TAG = "DashboardMatchingFragment";

    private DashboardListAdapter dashboardListAdapter;
    private ListView listView;
    private static String url;
    private int position;        // taglayout 구별하기 위한 일종의 flag라고 이해함

    // 세션에 저장된 유저 객체 저장하는 변수 -> 구현 덜 됨.
    private static LoggedInUser sessionUser;

    public DashboardMatchingFragment(int position) {
        this.position = position;
    }

    public static DashboardMatchingFragment newInstance(int position) {
        Bundle args = new Bundle();
        args.putInt(ARGUMENT_POSITION, position);
        DashboardMatchingFragment fragment = new DashboardMatchingFragment(position);
        fragment.setArguments(args);

        sessionUser = LoggedInUser.getLoggedInUser();   // 세션에 저장된 유저 정보 가져오기
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_dashboard_matching, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}
