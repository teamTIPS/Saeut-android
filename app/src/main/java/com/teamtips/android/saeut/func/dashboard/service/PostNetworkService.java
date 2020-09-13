package com.teamtips.android.saeut.func.dashboard.service;

import android.util.Log;
import com.google.gson.Gson;
import com.teamtips.android.saeut.R;
import com.teamtips.android.saeut.func.dashboard.model.AllPostData;
import com.teamtips.android.saeut.func.dashboard.model.Post;

import java.lang.reflect.Type;
import java.util.ArrayList;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PostNetworkService {

    private static String TAG = "PostNetworkService";

    private Retrofit mRetrofit;
    private static PostNetwork postNetwork;
    private Gson mGson;
    private Call<ArrayList<Post>> mCallPostList;
//    private Call<ResponseBody> mCallPostById;
//    private Call<ResponseBody> mCallPutPost;
//    private Call<ResponseBody> mCallModPost;
//    private Call<ResponseBody> mCallDeletePost;

    private void setRetrofitInit() {
        mRetrofit = new Retrofit.Builder()
                .baseUrl(String.valueOf(R.string.base_url))
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Interface 불러오기
        postNetwork = mRetrofit.create(PostNetwork.class);
    }

    // Get All List of Post
    private void callPostList() {
        mCallPostList = postNetwork.postList();
//        mCallPostList.enqueue(mPostListCallBack);
    }

//    private Callback<String> mPostListCallBack = new Callback<String>() {
//        @Override
//        public void onResponse(Call<String> call, Response<String> response) {
//            String result = response.body();
//            ArrayList<AllPostData> postList = mGson.fromJson(result, (Type) AllPostData.class);
//            Log.d(TAG, result);
//        }
//
//        @Override
//        public void onFailure(Call<String> call, Throwable t) {
//            t.printStackTrace();
//        }
//    };

    // Get Post By Id
    private void getPostById() {
        // Bring session id that Sign on this app
        String account_id = "";

//        mCallPostById = postNetwork.getPostById(account_id);
//        mCallPostById.enqueue(mPostCallBack);
    }
}