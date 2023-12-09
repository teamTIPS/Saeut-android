package com.teamtips.android.saeut.func.map;


import android.util.Base64;
import android.util.JsonReader;
import android.util.Log;

import androidx.annotation.Nullable;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.internal.$Gson$Preconditions;
import com.google.gson.reflect.TypeToken;

import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/*
현재좌표 >> 주소 변환을 요청하여 저장하는 클래스
*/
final class RequestLocationInfo {
    private static final String Tag = "RequestLocationInfo";
    String APPKEY = "KakaoAK a980cc15274214f283c3a720c92e85ad";

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://dapi.kakao.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    RetrofitInterface retrofitInterface = retrofit.create(RetrofitInterface.class);
}