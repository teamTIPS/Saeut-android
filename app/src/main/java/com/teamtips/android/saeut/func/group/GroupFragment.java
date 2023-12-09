package com.teamtips.android.saeut.func.group;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.teamtips.android.saeut.R;
import com.teamtips.android.saeut.data.Comment;
import com.teamtips.android.saeut.data.Group;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import com.teamtips.android.saeut.func.login.data.model.LoggedInUser;

public class GroupFragment extends Fragment {

    public static final String TAG = "GroupFragment";
    private final String url = "http://49.50.173.180:8080/saeut/";

    private GroupAdapter groupAdapter;
    private ArrayList<Comment> comments;
    private Context mContext;
    private RecyclerView comment_recyclerView;

    LoggedInUser loggedInUser = LoggedInUser.getLoggedInUser();
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    GroupNetworkService api = retrofit.create(GroupNetworkService.class);

    public static GroupFragment newInstance() {
        return new GroupFragment();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        comment_recyclerView = view.findViewById(R.id.comment_list_recyclerview);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(mContext);
        comment_recyclerView.setLayoutManager(layoutManager);
        groupAdapter = new GroupAdapter(mContext, comments, api);
        TextView tv_title = view.findViewById(R.id.group_title_tv);
        TextView tv_groupCaptain = view.findViewById(R.id.tv_groupCaptain);

        tv_title.setText("사랑초 등하교 모임");
        tv_groupCaptain.setText("메론아빠");

        InitComment();

        comment_recyclerView.setAdapter(groupAdapter);
    }

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        mContext = getActivity();
        comments = new ArrayList<>();

        api.getGroup(loggedInUser.getId()).enqueue(getuserGroup);

        return inflater.inflate(R.layout.fragment_group, container, false);
    }

    Callback<List<Group>> getuserGroup = new Callback<List<Group>>() {

        @Override
        public void onResponse(Call<List<Group>> call, Response<List<Group>> response) {
            Log.e(TAG,"getuserGroup 서버통신 성공");
            if(response.body() != null){
                Gson gson = new GsonBuilder().create();
                Log.e(TAG,response.body().toString());
                List<Group> rootobj = response.body();
                if(rootobj != null){
                    Log.e(TAG,"리스트 있음");

                    Group mygroup = rootobj.get(0);

                    if(getView() != null){
                        Log.e(TAG,"hi");
                    }
                }
                else {
                    Log.e(TAG, "리스트가 없어요");
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    FragmentTransaction transaction = fragmentManager.beginTransaction();
                    transaction.replace(R.id.none_comment_layout, new none_comment_fragment()).commit();
                }

            }
            else Log.e(TAG,response.toString());
        }

        @Override
        public void onFailure(Call<List<Group>> call, Throwable t) {

        }
    };

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.message, menu);
    }

    public void InitComment(){
        comments = new ArrayList<>();
        Comment c1 = new Comment(1, 0, "이웃1", "안녕하세요~", new Date(System.currentTimeMillis() - (long) ( 1000 * 60 * 60 * 15 )));
        Comment c2 = new Comment(3, 5, "이웃2", "반갑습니다!", new Date(System.currentTimeMillis() - (long) ( 1000 * 60 * 60 * 12 )));
        Comment c3 = new Comment(1, 0, "이웃3", "앞으로 잘 부탁드려요~", new Date(System.currentTimeMillis() - (long) ( 1000 * 60 * 60 * 10 )));

        comments.add(c1);
        comments.add(c2);
        comments.add(c3);
        groupAdapter.updateArrayList(comments);
    }
}
