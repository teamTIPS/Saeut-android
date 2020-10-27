package com.teamtips.android.saeut.func.dashboard.service;

import com.teamtips.android.saeut.data.Apply;
import com.teamtips.android.saeut.data.Post;
import com.teamtips.android.saeut.data.Tag;
import java.util.List;
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

    // 태그 추가
    @POST("tag")
    Call<String> addTag(@Body Tag tag);

    // 태그 가져오기
    @GET("tag/{post_id}")
    Call<List<Tag>> getTagList(@Path("post_id") int post_id);
}