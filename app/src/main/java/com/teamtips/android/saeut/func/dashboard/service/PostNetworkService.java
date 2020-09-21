package com.teamtips.android.saeut.func.dashboard.service;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.teamtips.android.saeut.func.dashboard.model.Post;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PostNetworkService {

    private static String TAG = "PostNetworkService";
    private static String BASE_URL = "http://49.50.173.180:8080/saeut/";

    private Call<String> mCallAddPost;
    private Call<ResponseBody> mCallModPost;

    Gson gson = new GsonBuilder().setLenient().create();
    OkHttpClient.Builder builder = new OkHttpClient.Builder();
    OkHttpClient client = builder.build();
    private final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build();

    private PostNetwork postNetwork = retrofit.create(PostNetwork.class);

    // Add Post
    public void addPost(Post post) {
        Gson gson = new Gson();
        String obj2 = gson.toJson(post);
        // Bring session id that Sign on this app
        mCallAddPost = postNetwork.addPost(post);
        mCallAddPost.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()) {
                    Log.d(TAG, "성공  : " + call.request().body());
                } else {
                    Log.d(TAG, "실패  : " + response.code());
                    Log.d(TAG, "실패  : " + post.toString());
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.d(TAG, "여기여기 : " + t.getMessage());
            }
        });
    }

    public void modPost(Post post) {
        mCallModPost = postNetwork.modPost(post);
        mCallModPost.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }
}