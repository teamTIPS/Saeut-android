package com.teamtips.android.saeut.func.community;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.teamtips.android.saeut.data.Board;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface CommunityNetworkService {

    @GET("board")
    Call<JsonArray> getAllboardlist();

    @POST("board")
    Call<String> addNewBoard(@Body Board board);
}
