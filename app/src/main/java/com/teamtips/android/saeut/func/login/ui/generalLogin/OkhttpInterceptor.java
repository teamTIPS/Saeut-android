package com.teamtips.android.saeut.func.login.ui.generalLogin;


import android.content.Context;
import android.content.SharedPreferences;

import androidx.preference.PreferenceManager;

import com.teamtips.android.saeut.func.login.data.model.LoggedInUser;

import java.io.IOException;
import java.util.Date;
import java.util.Map;

import okhttp3.*;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class OkhttpInterceptor implements Interceptor{

    Context ctx;
    SharedPreferences mPrefs;
    SharedPreferences.Editor mPrefsEdit;

    public OkhttpInterceptor(Context ctx) {
        //인터셉터 호출시
        this.ctx = ctx;
        this.mPrefs= PreferenceManager.getDefaultSharedPreferences(ctx);
        mPrefsEdit=mPrefs.edit();
    }

    OkHttpClient.Builder builder = new OkHttpClient.Builder().addInterceptor(this::intercept);
    OkHttpClient client = builder.build();
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("getUrl()") //서버 url 배치
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build();

    RestApi api = retrofit.create(RestApi.class);

    @Override
    public Response intercept(Chain chain) throws IOException {
        LoggedInUser loggedinUser = LoggedInUser.getLoggedInUser();
        String token = loggedinUser.getAccessToken();
        Request newRequest;

        // 토큰이 있는 경우
        if (token != null && !token.equals("")) {
            // Authorization 헤더에 토큰 추가
            newRequest = chain.request().newBuilder().addHeader("Authorization", token).build();

            // 토큰 유효기간 만료시
            Date expireDate = loggedinUser.getAccessexpireDateTime();
            if (expireDate.getTime() <= System.currentTimeMillis()) {
                // 클라이언트의 at 초기화
                loggedinUser.setAccessToken(null);
                // refreshToken 토큰 갱신 api 호출
                Map<String, String> body = api.refreshToken(loggedinUser.getRefreshToken()).execute().body();

                // 클라이언트의 토큰 갱신 수행하여 tokenStr 변수에 삽입
                if (body != null) {


                    newRequest = chain.request().newBuilder().addHeader("Authorization", tokenStr).build();
                    return chain.proceed(newRequest);
                }
            }
        }
        //토큰이 없는 경우(첫 로그인)
        else {
            newRequest = chain.request();


        }
        return chain.proceed(newRequest);
    }
}
