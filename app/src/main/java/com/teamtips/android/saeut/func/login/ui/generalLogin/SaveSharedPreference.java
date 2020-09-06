package com.teamtips.android.saeut.func.login.ui.generalLogin;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.preference.PreferenceManager;

import com.teamtips.android.saeut.func.login.data.model.LoggedInUser;

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

    public static void setLoggedInUser(Context context, LoggedInUser loggedInUser){
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putString(PREF_LOGINUSER, loggedInUser.toString());
    }

    public static String getLoggedInUser(Context context){
        return getSharedPreferences(context).getString(PREF_LOGINUSER,"");
    }
}
