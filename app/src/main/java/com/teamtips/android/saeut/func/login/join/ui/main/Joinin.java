package com.teamtips.android.saeut.func.login.join.ui.main;

public class Joinin {

    //필수정보
    private String name;
    private String id;
    private String password;
    private String email;
    private String phonenum;

    //선택정보
    private String nickname;
    private String address1;
    private String address2;
    private String picpath;
    private String self_introduction;

    //constructor

    Joinin() {}

    Joinin(String name, String id, String password, String email, String phonenum) {
        this.name = name;
        this.id = id;
        this.password = password;
        this.email = email;
        this.phonenum = phonenum;
    }

    Joinin(String name, String id, String password, String email, String phonenum, String nickname, String address1, String address2, String picpath, String self_introduction) {
        this.name = name;
        this.id = id;
        this.password = password;
        this.email = email;
        this.phonenum = phonenum;
        this.nickname = nickname;
        this.address1 = address1;
        this.address2 = address2;
        this.picpath = picpath;
        this.self_introduction = self_introduction;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhonenum() {
        return phonenum;
    }

    public void setPhonenum(String phonenum) {
        this.phonenum = phonenum;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getPicpath() {
        return picpath;
    }

    public void setPicpath(String picpath) {
        this.picpath = picpath;
    }

    public String getSelf_introduction() {
        return self_introduction;
    }

    public void setSelf_introduction(String self_introduction) {
        this.self_introduction = self_introduction;
    }
}
