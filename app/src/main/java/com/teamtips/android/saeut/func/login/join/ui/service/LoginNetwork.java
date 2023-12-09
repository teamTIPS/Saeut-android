package com.teamtips.android.saeut.func.login.join.ui.service;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.*;

public interface LoginNetwork {
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