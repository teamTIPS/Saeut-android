package com.teamtips.android.saeut.func.map;
import com.google.gson.JsonObject;

import org.json.JSONObject;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.*;

public interface RetrofitInterface {
//    String APPKEY = "a980cc15274214f283c3a720c92e85ad";

//    @Headers("Authorization: KakaoAK "+APPKEY)
    @Headers("Authorization: KakaoAK a980cc15274214f283c3a720c92e85ad")
    @GET("v2/local/geo/coord2address.{format}")
    Call<JsonObject> getLocationInfo(
//            @Header("Authorization") String kakaoAK,
            @Path("format") String path,
            @Query("x") Double x,
            @Query("y") Double y);
}
