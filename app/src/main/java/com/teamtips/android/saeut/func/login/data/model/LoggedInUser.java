package com.teamtips.android.saeut.func.login.data.model;

import com.teamtips.android.saeut.func.login.ui.generalLogin.SaveSharedPreference;

import java.util.Calendar;
import java.util.Date;

import okhttp3.Response;

/**
 * Data class that captures user information for logged in users retrieved from LoginRepository
 */
public class LoggedInUser {

    //어플에서 임의로 부여하는 시퀀스
    private String account_id;
    //유저에게 보여줄 본인 별명(별명으로 하는 것이 나아보임)
    private String nickname;
    private String accesstoken;
    private Date AccessexpireDateTime;

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
    }
    public String getAccessToken() {
        return accesstoken;
    }

    public void setAccessexpireDateTime(Date accessexpireDateTime) {
        AccessexpireDateTime = accessexpireDateTime;
    }
    public Date getAccessexpireDateTime() {
        return AccessexpireDateTime;
    }
}
