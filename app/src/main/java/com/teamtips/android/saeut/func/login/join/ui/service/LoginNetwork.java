package com.teamtips.android.saeut.func.login.join.ui.service;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.*;

public interface LoginNetwork {

    //로그인 성공했을 때 at, rt를 result에 같이 넣어서 보내줄 수 있는지? >> OK
//    //at, rt 가져오기 - 로그인에 추가
//    @FormUrlEncoded
//    @GET("/auth/token")
//    Call<Map<String, String>> getToken();

    //at 갱신
    @FormUrlEncoded
    @GET("/signon/get_access_token")
    Call<JsonObject> updateAt(@Field("RefreshToken") String RefreshToken);
    //return AccessToken (as json)

    //rt 갱신
    @FormUrlEncoded
    @GET("/signon/get_refresh_token ")
    Call<JsonObject> updateRt();
    //return RefreshToken (as json)

    @GET("valid/id/{id}")
    Call<String> validEmail(@Path("id") String email);
}
