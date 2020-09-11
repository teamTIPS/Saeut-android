package com.teamtips.android.saeut.func.login.ui.generalLogin;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.preference.PreferenceManager;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.teamtips.android.saeut.func.login.data.model.LoggedInUser;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

//아이디 및 비밀번호 자동완성, AuthActivity와 함께 나중에 완성하기
public class SaveSharedPreference {
    static final String PREF_USER_NAME = "username";
    static final String PREF_LOGINUSER = "loginuser";

    static SharedPreferences getSharedPreferences(Context ctx) {
        return PreferenceManager.getDefaultSharedPreferences(ctx);
    }

    // 계정 정보 저장
    public static void setUserName(Context ctx, String userName) {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(PREF_USER_NAME, userName);
        editor.commit();
    }

    // 저장된 정보 가져오기
    public static String getUserName(Context ctx) {
        return getSharedPreferences(ctx).getString(PREF_USER_NAME, "");
    }

    // 로그아웃
    public static void clearUserName(Context ctx) {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.clear();
        editor.commit();
    }

    // RT 저장
    public static void setRT(Context ctx, String rt) {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString("RefreshToken",rt);
        editor.commit();
    }

    public static void updateRTtime(Context ctx, Date time){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(time);
        calendar.add(Calendar.DATE, 14);
        Date updatedRTtime = calendar.getTime();
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        String rt = getRT(ctx);
        DecodedJWT jwt = JWT.decode(rt);
        jwt.
        editor.putString("RefreshTokenDate", DateToString(updatedRTtime));
        editor.commit();
    }

    // 저장된 정보 가져오기
    public static String getRT(Context ctx) {
        return getSharedPreferences(ctx).getString(PREF_USER_NAME, "");
    }

    public static Date getRTtime(Context ctx){
        String rt = getRT(ctx);
        DecodedJWT jwt = JWT.decode(rt);
        return jwt.getExpiresAt();
    }

    public static String DateToString(Date date) {
        SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX");
        return transFormat.format(date);
    }

    public static Date StringtoDate(String s){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX");
        Date birthDate = null;
        try {
            birthDate = sdf.parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return birthDate;
    }
}
