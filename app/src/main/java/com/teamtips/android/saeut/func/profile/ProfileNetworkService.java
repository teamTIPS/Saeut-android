package com.teamtips.android.saeut.func.profile;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ProfileNetworkService {

    @GET("valid/nickname/{nickname}")
    Call<String> validNickname(@Path("nickname") String nickname);

    @POST("user/additional/edit")
    Call<String> putuserAdditional(@Body UserAdditional userAdditional);
}
