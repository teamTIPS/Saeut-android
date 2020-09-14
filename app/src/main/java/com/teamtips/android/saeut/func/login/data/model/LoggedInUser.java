package com.teamtips.android.saeut.func.login.data.model;

import android.util.Log;

import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.teamtips.android.saeut.func.login.ui.generalLogin.SaveSharedPreference;

import java.util.Calendar;
import java.util.Date;

import okhttp3.Response;
/**
 * Data class that captures user information for logged in users retrieved from LoginRepository
 */
public class LoggedInUser {

    //jwt
    private String accesstoken;
    private Date AccessexpireDateTime;

    //UserEssential
    private String account_id;
    private String password;
    private String name;
    private String phonenum;
    private String nickname;

    //UserAdditional
    private Date birth;
    private String zipcode;
    private String address1;
    private String address2;
    private String picpath;
    private String description;
    private int type;

    /////////
    //싱글톤 패턴으로 구현
    private static class userHolder {
        public static final LoggedInUser Instance = new LoggedInUser();
    }

    public static LoggedInUser getLoggedInUser(){
        return userHolder.Instance;
    }
    /////////

    public void setAccount_id(String account_id) {
        this.account_id = account_id;
    }
    public String getAccount_id() {
        return account_id;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    public String getnickname() {
        return nickname;
    }

    public void setAccessToken(String s) {
        this.accesstoken = s;
        try{
            DecodedJWT jwt = JWT.decode(s);
            AccessexpireDateTime = jwt.getExpiresAt();
        } catch (JWTDecodeException e){
            Log.e("setAccessexpireDateTime", "JWTDecodeException");
        }
    }
    public String getAccessToken() {
        return accesstoken;
    }

    public Date getAccessexpireDateTime() {
        return AccessexpireDateTime;
    }
}
