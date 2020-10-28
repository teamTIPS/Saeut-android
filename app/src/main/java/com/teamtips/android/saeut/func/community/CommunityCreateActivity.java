package com.teamtips.android.saeut.func.community;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.teamtips.android.saeut.R;
import com.teamtips.android.saeut.data.Board;
import com.teamtips.android.saeut.func.login.data.model.LoggedInUser;

import org.json.JSONException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CommunityCreateActivity extends AppCompatActivity {

    private final static String Tag = "CommunityCreateActivity";
    private final String url = "http://49.50.173.180:8080/saeut/";

    CommunityFragment communityFragment;

    LoggedInUser loggedInUser = LoggedInUser.getLoggedInUser();
    Context context;

    Gson gson = new GsonBuilder()
            .setLenient()
            .create();

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build();

    CommunityNetworkService api = retrofit.create(CommunityNetworkService.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        setContentView(R.layout.activity_community_create);
        EditText create_community_et = findViewById(R.id.create_community_et);
        Button create_community_finish_btn = findViewById(R.id.create_community_finish_btn);
        Button create_community_complete_btn = findViewById(R.id.create_community_complete_btn);

        create_community_finish_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        create_community_complete_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String address = "서울시 강서구 마곡동";
                int rank = 5;
                create_community_et.getText().toString();
                String contents = create_community_et.getText().toString();
                if(contents.equals("")){
                    return;
                }

                api.addNewBoard(new Board(loggedInUser.getId(), contents, System.currentTimeMillis(), 0, 0, loggedInUser.getnickname(),  address, rank)).enqueue(createNewPost);

                finish();
            }
        });
    }

    Callback<String> createNewPost = new Callback<String>() {
        @Override
        public void onResponse(Call<String> call, Response<String> response) {
            Log.e(Tag, "서버통신 성공");
            if(response.errorBody() != null) Log.e(Tag,response.errorBody().toString());
            if(response.body() != null) Log.e(Tag,response.body());
            if(response.code() != 200){
                Log.e(Tag, "글 추가 실패");
                Log.e(Tag,response.toString());
            }
            else{
                CommunityFragment.mutableLiveData.setValue(2);
            }
        }

        @Override
        public void onFailure(Call<String> call, Throwable t) {
            Log.e(Tag, "onFailure");
            Log.e(Tag,t.toString());
        }
    };
}