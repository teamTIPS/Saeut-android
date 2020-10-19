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
Protocol: REST API, Data type: json\
REST API Key: a980cc15274214f283c3a720c92e85ad
*/
final class RequestLocationInfo {
    private static final String Tag = "RequestLocationInfo";
    String APPKEY = "KakaoAK a980cc15274214f283c3a720c92e85ad";


//    OkHttpClient.Builder builder = new OkHttpClient.Builder().addInterceptor(chain -> {
//        String Header = Base64.encodeToString("Authorization : KakaoAK".getBytes(), Base64.NO_WRAP);
//        Request newRequest = chain.request().newBuilder().addHeader(Header, "a980cc15274214f283c3a720c92e85ad").build();
//        return chain.proceed(newRequest);
//    });

    //    OkHttpClient client = new OkHttpClient.Builder().build();
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://dapi.kakao.com/")
            .addConverterFactory(GsonConverterFactory.create())
//            .client(client)
            .build();
    RetrofitInterface retrofitInterface = retrofit.create(RetrofitInterface.class);
//    KaKaoLocationAPI resultLocation = null;
}

//    public KaKaoLocationAPI requestLocation(double longitude, double latitude) throws IOException {
//        HashMap<String, Double> paramMap = new HashMap<>();
//        paramMap.put("x", longitude);
//        paramMap.put("y", latitude);
//
////        JsonObject result = retrofitInterface.getLocationInfo(paramMap).execute().body();
////        ResponseBody error = retrofitInterface.getLocationInfo(paramMap).execute().errorBody();
//        Response<JsonObject> call = retrofitInterface.getLocationInfo("json", longitude, latitude).execute();
//
//        if(call.isSuccessful()){
//            Log.e(Tag,"hi");
//            Log.e(Tag,"there: "+call.toString());
//        }
////        Log.e(Tag,"here?:"+call.errorBody().string());
//
////        if(result != null){
////            Log.e(Tag,"return: "+result.get("errorType").toString());
////
////            Log.e(Tag,"test");
////            Log.e(Tag,"kakaoLocation not null, "+resultLocation.toString());
////        }
//
////        call.enqueue(new Callback<KaKaoLocationAPI>() {
////            @Override
////            public void onResponse(Call<KaKaoLocationAPI> call, retrofit2.Response<KaKaoLocationAPI> response) {
////                assert response.body() != null;
////            }
////            @Override
////            public void onFailure(Call<KaKaoLocationAPI> call, Throwable t) {
////                Log.e(Tag,"onFailure");
////            }
////        });
////        if(!call.){
////            Log.e(Tag,"test");
////            Log.e(Tag,result);
////
////            Gson gson = new Gson();
////            JsonReader reader = new JsonReader(new StringReader(result));
////            reader.setLenient(true);
//////            JsonParser parser = new JsonParser();
//////            JsonElement rootObj = parser.parse(result).getAsJsonObject();
////
////            KaKaoLocationAPI kaKaoLocation = gson.fromJson(reader, KaKaoLocationAPI.class);
////            if(kaKaoLocation != null){
////                resultLocation.setKaKaoLocationAPI(kaKaoLocation);
////                Log.e(Tag,"kakaoLocation not null, "+kaKaoLocation.documentsArrayList.get(0).address_name);
////            }
////            else{
////                Log.e(Tag,"kaKaoLocation null");
////            }
////        }
////        else{
////            Log.e(Tag,"result:"+result);
////        }
//        KaKaoLocationAPI kaKaoLocationAPI =null;
//        return kaKaoLocationAPI;
//    }
//}
