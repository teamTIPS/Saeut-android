package com.teamtips.android.saeut.func.login.join.ui.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginNetworkService {

    private static String TAG = "LoginNetworkService";
    private static String BASE_URL = "http://49.50.173.180:8080/saeut/";

    private Call<String> mCallValidEmail;
    Gson gson = new GsonBuilder().setLenient().create();
    OkHttpClient.Builder builder = new OkHttpClient.Builder();
    OkHttpClient client = builder.build();

    private final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build();
}
