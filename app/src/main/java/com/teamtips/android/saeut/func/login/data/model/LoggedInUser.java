package com.teamtips.android.saeut.func.login.data.model;

import android.util.Log;

import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;

/**
 * Data class that captures user information for logged in users retrieved from LoginRepository
 */
public class LoggedInUser {

    //jwt
    private String accesstoken;
    private Date AccessexpireDateTime;

    //UserEssential
    private String id;
    private String password;
    private String name;
    private String phonenum;
    private Date birth;
    private int gender;
    //마케팅 정보 수신 동의 여부
    private boolean perm1;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getNickname() {
        return nickname;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    //UserAdditional
    private String nickname;
    private String pic;

    //enum class 필요할 듯
    private int rank;
    private String score;
    private String description;

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    private String address1;
    private String address2;
    private String zipcode;
    private String add_latitude;
    private String add_longitude;
    // 추가된 부분
    private String introduce;       // 돌봄 제공자 어필용 자기소개
    private int grade;              // 평점 관리 (리뷰)

    /////////
    //싱글톤 패턴으로 구현
    private static class userHolder {
        public static final LoggedInUser Instance = new LoggedInUser();
    }

    public static LoggedInUser getLoggedInUser(){
        return userHolder.Instance;
    }
    /////////

    public void setId(String id) {
        this.id = id;
    }
    public String getId() {
        return id;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
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
