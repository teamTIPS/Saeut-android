package com.teamtips.android.saeut.func.profile;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ProfileNetworkService {

    @GET("valid/nickname/{nickname}")
    Call<String> validNickname(@Path("nickname") String nickname);

    @PUT("user/additional")
    Call<String> putuserAdditional(@Body UserAdditional userAdditional);
}
