package com.teamtips.android.saeut.func.login.join.ui.service;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.preference.PreferenceManager;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SaveSharedPreference {
    static final String PREF_USER_NAME = "username";
    static final String PREF_LOGINUSER = "loginuser";
    private static final String Tag = "SaveSharedPreference";

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
    public static void clearUser(Context ctx) {
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

    // 저장된 정보 가져오기
    public static String getRT(Context ctx) {
        Log.e(Tag,"GetRT:"+getSharedPreferences(ctx).getString("RefreshToken", ""));
        return getSharedPreferences(ctx).getString("RefreshToken", "");
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
