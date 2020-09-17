package com.teamtips.android.saeut.func.login.ui.generalLogin;


import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.preference.PreferenceManager;

import com.google.gson.JsonObject;
import com.teamtips.android.saeut.func.login.data.model.LoggedInUser;

import java.io.IOException;
import java.util.Date;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.teamtips.android.saeut.func.login.ui.generalLogin.SaveSharedPreference.getRT;

public class OkhttpInterceptor implements Interceptor{
    private static final String Tag = "OkhttpInterceptor";

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
            .baseUrl("http://49.50.173.180:8080/saeut")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build();

    RestApi api = retrofit.create(RestApi.class);

    @Override
    public Response intercept(Chain chain) throws IOException {
        LoggedInUser loggedinUser = LoggedInUser.getLoggedInUser();
        String token = loggedinUser.getAccessToken();
        Request newRequest;

        if (token != null && !token.equals("")) {
            // Authorization 헤더에 토큰 추가
            newRequest = chain.request().newBuilder().addHeader("Authorization", token).build();

            // 토큰 유효기간 만료시
            Date expireDate = loggedinUser.getAccessexpireDateTime();
            if (expireDate.getTime() < System.currentTimeMillis()) {
                // 클라이언트의 at 초기화
                loggedinUser.setAccessToken(null);
                // At 갱신 api 호출
                String RT = getRT(ctx);
                JsonObject body = api.updateAt(RT).execute().body();
                Log.e(Tag, "at 갱신 중");

                // 토큰 갱신 완료, 다시 request 보내기
                if (body != null) {
                    Log.e(Tag, "at 갱신 완료");
                    String At = body.get("AccessToken").toString();
                    loggedinUser.setAccessToken(At);

                    //Sliding Session - at 갱신시 rt 유효기간 확인, 일정 시간 미만 남았을 때 갱신 요청
                    Date RTremain = SaveSharedPreference.getRTtime(ctx);
                    if(TimetoUpdateRT(RTremain)){
                        api.updateRt().enqueue(new UpdateRefreshToken());
                    }

                    newRequest = chain.request().newBuilder().addHeader("Authorization", loggedinUser.getAccessToken()).build();
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

    private boolean TimetoUpdateRT(Date expireDate) {
        //RT 유효기간 지금부터 한달 남음(overflow때문에 expireDate/1000)
        return expireDate.getTime()/1000 <= System.currentTimeMillis()/1000 - 60*60*24*30;
    }
}
