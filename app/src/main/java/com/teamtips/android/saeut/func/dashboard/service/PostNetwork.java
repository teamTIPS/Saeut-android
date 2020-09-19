package com.teamtips.android.saeut.func.dashboard.service;

import com.google.gson.JsonObject;
import com.teamtips.android.saeut.func.dashboard.model.Post;
import com.teamtips.android.saeut.func.dashboard.model.Result;

import org.json.JSONObject;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.*;

public interface PostNetwork {

    // 게시물 추가
    @POST("post")
    Call<String> addPost(@Body Post post);

    // 게시물 수정
    @PUT("post")
    Call<ResponseBody> modPost(Post post);

    @DELETE("post/remove/{id}")
    Call<ResponseBody> deletePost(@Path("id") String post_id);
}