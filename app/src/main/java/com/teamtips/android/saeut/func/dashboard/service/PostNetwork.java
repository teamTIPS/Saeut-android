package com.teamtips.android.saeut.func.dashboard.service;

import com.teamtips.android.saeut.func.dashboard.model.Post;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.*;

public interface PostNetwork {

    // 게시물 추가
    @POST("post/add")
    Call<String> addPost(@Body Post post);

    // 게시물 수정
    @PUT("post/mod")
    Call<ResponseBody> modPost();

    @DELETE("post/remove/{id}")
    Call<ResponseBody> deletePost(@Path("id") String post_id);
}