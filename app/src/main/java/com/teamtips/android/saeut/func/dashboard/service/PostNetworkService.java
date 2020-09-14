package com.teamtips.android.saeut.func.dashboard.service;

import android.util.Log;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.teamtips.android.saeut.R;
import com.teamtips.android.saeut.func.dashboard.model.AllPostData;
import com.teamtips.android.saeut.func.dashboard.model.Post;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;
import okio.Timeout;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PostNetworkService {

    private static String TAG = "PostNetworkService";
    private static String BASE_URL = "https://49.50.173.180:8080/saeut/";

    private Retrofit mRetrofit;
    private static PostNetwork postNetwork;
    private Gson mGson;
    private Call<ResponseBody> mCallPostList;
//    private Call<ResponseBody> mCallPostById;
//    private Call<ResponseBody> mCallPutPost;
//    private Call<ResponseBody> mCallModPost;
//    private Call<ResponseBody> mCallDeletePost;

    public void setRetrofitInit() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        OkHttpClient client = builder.build();

        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        // Interface 불러오기
        postNetwork = mRetrofit.create(PostNetwork.class);
    }

    // Get All List of Post
    public ArrayList<Post> callPostList() throws IOException {
        ArrayList<Post> postArrayList = new ArrayList<Post>();
        mCallPostList = postNetwork.postList();
        mCallPostList.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    try {
                        ResponseBody responseBody = call.execute().body();
                        Log.d(TAG, responseBody.toString());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    Log.d(TAG, response.body().toString());
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d(TAG, t.getMessage());
            }
        });
        return postArrayList;
    }


    // Get Post By Id
    private void getPostById() {
        // Bring session id that Sign on this app
        String account_id = "";

//        mCallPostById = postNetwork.getPostById(account_id);
//        mCallPostById.enqueue(mPostCallBack);
    }
}