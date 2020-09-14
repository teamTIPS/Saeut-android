package com.teamtips.android.saeut.func.dashboard.service;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.teamtips.android.saeut.func.dashboard.model.Post;

import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.*;

public interface PostNetwork {

    // 전체 게시물 불러오기
    @GET("post")
    Call<ResponseBody > postList();

    // findPostById
    @GET("post/{id}")
    Call<ResponseBody> getPostById(@Path("id") String account_id);

    // 게시물 추가
    @PUT("post/add")
    Call<ResponseBody> putPost();

    // 게시물 수정
    @PUT("post/mod")
    Call<ResponseBody> modPost();

    @DELETE("post/remove/{id}")
    Call<ResponseBody> deletePost(@Path("id") String post_id);
}