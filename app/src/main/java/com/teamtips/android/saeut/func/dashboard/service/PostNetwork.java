package com.teamtips.android.saeut.func.dashboard.service;

import com.teamtips.android.saeut.func.dashboard.model.Apply;
import com.teamtips.android.saeut.func.dashboard.model.Post;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.*;

public interface PostNetwork {

    // 게시물 추가
    @POST("post")
    Call<String> addPost(@Body Post post);

    // 게시물 수정
    @PUT("post")
    Call<String> modPost(@Body Post post);

    @DELETE("post/{id}")
    Call<String> deletePost(@Path("id") int post_id);

    // 돌봄 신청 추가
    @POST("apply")
    Call<String> addApply(@Body Apply apply);

    // 돌봄 신청 수 조회
    @GET("apply/count/{postId}")
    Call<Integer> getApplyCount(@Path("postId") int post_id);



}