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

public class DashboardListFragment extends Fragment {

    private static final String ARGUMENT_POSITION = "argument_position";
    private static final String ARGUMENT_NAME = "argument_name";
    public static final String TAG = "DashboardListFragment";

    private DashboardListAdapter dashboardListAdapter;
    private ListView listView;
    private ArrayList<Post> postArrayList;
    private PostNetworkTask postNetworkTask;
    private static String url;
    private int position;        // taglayout 구별하기 위한 일종의 flag라고 이해함

    // 세션에 저장된 유저 객체 저장하는 변수 -> 구현 덜 됨.
    private static LoggedInUser sessionUser;

    public DashboardListFragment(int position) {
        this.position = position;
    }

    public static DashboardListFragment newInstance(int position) {
        Bundle args = new Bundle();
        args.putInt(ARGUMENT_POSITION, position);
        DashboardListFragment fragment = new DashboardListFragment(position);
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
        setAdapter(view);
        getListByPosition(position);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_dashboard_list, container, false);
        return root;
    }

    private void setAdapter(View view) {
        postArrayList = new ArrayList<Post>();
        dashboardListAdapter = new DashboardListAdapter(view.getContext(), R.layout.adapter_dashboard, postArrayList, position);
        listView = view.findViewById(R.id.lv_child);
        listView.setAdapter(dashboardListAdapter);
    }

    private void getListByPosition(int position) {
        // fragment position에 따른 url 구현
        // 추후 현재 로그인 유저 정보 받아서 수정할 것.
        String account_id = "test";
        Log.d(TAG, "sessionUser : " + account_id);
        if (position == 0) {
            // 전체 리스트를 불러오는 URL
            url = "http://49.50.173.180:8080/saeut/post";
        }
        postNetworkTask = new PostNetworkTask(url, null, dashboardListAdapter);
        postNetworkTask.execute();
    }

    @Override
    public void onResume() {
        super.onResume();
        // 만약 추가된 데이터가 있다면,
        setAdapter(getView());
        dashboardListAdapter.notifyDataSetChanged();
        getListByPosition(position);
    }
}
