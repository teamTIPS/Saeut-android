package com.teamtips.android.saeut.func.community;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.teamtips.android.saeut.R;
import com.teamtips.android.saeut.data.Community;

import java.util.ArrayList;
import java.util.Date;

public class CommunityFragment extends Fragment {

    public static final String TAG = "CommunityFragment";
    private CommunityAdapter communityAdapter;
    private ArrayList<Community> communityArrayList;
    private CommunityNetworkService communityNetworkService;
    private Context mContext;
    private RecyclerView community_recyclerview;

    public static CommunityFragment newInstance() {
        return new CommunityFragment();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setCommunityAdapter(view);
    }

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_community, container, false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.message, menu);
    }

    public void setCommunityAdapter(View view) {
        InitCommunity();
        communityAdapter = new CommunityAdapter(communityArrayList, mContext, communityNetworkService);
        community_recyclerview = view.findViewById(R.id.community_recycler_item);
        community_recyclerview.setAdapter(communityAdapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(mContext);
        community_recyclerview.setLayoutManager(layoutManager);

    }

    private void InitCommunity() {
        long now = System.currentTimeMillis();
        Date date = new Date(now);
        communityArrayList = new ArrayList<>();
        communityArrayList.add(new Community(
                1,
                2,
                "마곡동 맛집 어딘가요?",
                date,
                0,
                2,
                "낮잠자기좋은날",
                "서울시 강서구 발산동",
                "옆집이웃"));

        communityArrayList.add(new Community(
                4,
                5,
                "고양이미용실 여기 파마 잘하네요~",
                date,
                2,
                5,
                "인공눈물",
                "서울시 강서구 화곡동",
                "옆방이웃"));
        communityArrayList.add(new Community(
                6,
                7,
                "와이셔츠 세탁 빠르게 되는 곳 아시는 분 계신가요?",
                date,
                0,
                1,
                "시계조아",
                "서울시 강서구 마곡동",
                "도시이웃"));
    }
}
