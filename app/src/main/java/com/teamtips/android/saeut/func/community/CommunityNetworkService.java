package com.teamtips.android.saeut.func.community;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface CommunityNetworkService {

    @GET("board")
    Call<JsonArray> getAllboardlist();

    @POST("board")
    @FormUrlEncoded
    Call<JsonObject> addNewBoard(@Field("Board") JsonObject board);
}
