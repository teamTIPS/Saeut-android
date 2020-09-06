package com.teamtips.android.saeut.func.login.ui.generalLogin;


import java.util.Map;

import retrofit2.Call;
import retrofit2.http.*;

//서버쪽 수정부탁
public interface RestApi {

    //로그인 성공했을 때 at, rt를 result에 같이 넣어서 보내줄 수 있는지? >> 서버에 물어보기
//    //at, rt 가져오기 - 로그인에 추가
//    @FormUrlEncoded
//    @GET("/auth/token")
//    Call<Map<String, String>> getToken();

    //at 갱신
    @FormUrlEncoded
    @GET("/auth/token")
    Call<Map<String, String>> updateAt(@Field("updated_At") String updatedAt);
//    Call<Map<String, String>>

    //rt 갱신 - Sliding Session
    @FormUrlEncoded
    @PATCH("/auth/token")
    Call<Map<String, String>> updateRt(@Field("updated_Rt") String updateRt);
}
