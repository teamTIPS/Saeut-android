package com.teamtips.android.saeut.func.community;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;
import com.teamtips.android.saeut.R;
import com.teamtips.android.saeut.data.Board;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CommunityFragment extends Fragment {

    public static final String TAG = "CommunityFragment";
    private final String url = "http://49.50.173.180:8080/saeut/";

    private CommunityAdapter communityAdapter;
    private ArrayList<Board> boardArrayList;
    private Context mContext;
    private RecyclerView community_recyclerview;

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    CommunityNetworkService communityNetworkService = retrofit.create(CommunityNetworkService.class);

    public static CommunityFragment newInstance() {
        return new CommunityFragment();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Log.e(TAG,"!!!!!!!!!!!!!!!!!!!");

        super.onViewCreated(view, savedInstanceState);
        communityNetworkService.getAllboardlist().enqueue(getboardlist);
        community_recyclerview = view.findViewById(R.id.community_recycler_item);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(mContext);
        community_recyclerview.setLayoutManager(layoutManager);
        communityAdapter = new CommunityAdapter(boardArrayList, mContext, communityNetworkService);
        InitCommunity();
        community_recyclerview.setAdapter(communityAdapter);
    }

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {

        Log.e(TAG,"onCreateView");

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

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        startActivity(new Intent(getActivity(), CommunityCreateActivity.class));
        return super.onOptionsItemSelected(item);
    }

    private void InitCommunity() {
        long date = System.currentTimeMillis();
        boardArrayList = new ArrayList<>();
        boardArrayList.add(new Board(
                1,
                "test1",
                "마곡동 맛집 어딘가요?",
                date,
                0,
                2,
                "낮잠자기좋은날",
                "서울시 강서구 발산동",
                3));

        boardArrayList.add(new Board(
                4,
                "test2",
                "고양이미용실 여기 파마 잘하네요~",
                date,
                2,
                5,
                "인공눈물",
                "서울시 강서구 화곡동",
                4));
        boardArrayList.add(new Board(
                6,
                "test8",
                "와이셔츠 세탁 빠르게 되는 곳 아시는 분 계신가요?",
                date,
                0,
                1,
                "시계조아",
                "서울시 강서구 마곡동",
                5));
    }

    Callback<JsonArray> getboardlist = new Callback<JsonArray>() {

        ArrayList<Board> temparray = new ArrayList<>();

        @Override
        public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {
            Log.e(TAG,"서버통신 성공");
            if(response.body() != null){
                Gson gson = new GsonBuilder().create();
                Log.e(TAG,response.body().toString());
                JsonArray rootobj = response.body().getAsJsonArray();
                TypeToken<List<Board>> typeToken = new TypeToken<List<Board>>(){};
                Type type = typeToken.getType();
                temparray = gson.fromJson(rootobj, type);
                if(temparray != null){
                    Log.e(TAG,"리스트 있음");
                }
                else Log.e(TAG, "리스트가 없어요");
                communityAdapter.updateArrayList(temparray);
            }
            else Log.e(TAG,response.toString());
        }

        @Override
        public void onFailure(Call<JsonArray> call, Throwable t) {
            Log.e(TAG,call.toString()+"\n"+t.toString());
            Log.e(TAG,"onFailure");
        }
    };
}
