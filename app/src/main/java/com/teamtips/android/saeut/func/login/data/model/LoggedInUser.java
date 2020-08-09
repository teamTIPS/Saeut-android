package com.teamtips.android.saeut.func.login.data.model;

/**
 * Data class that captures user information for logged in users retrieved from LoginRepository
 */
public class LoggedInUser {

    //어플에서 임의로 부여하는 시퀀스
    private String userId;
    //유저에게 보여줄 본인 별명(별명으로 하는 것이 나아보임)
    private String nickname;

    public LoggedInUser(String userId, String nickname) {
        this.userId = userId;
        this.nickname = nickname;
    }

    public String getUserId() {
        return userId;
    }

    public String getnickname() {
        return nickname;
    }
}