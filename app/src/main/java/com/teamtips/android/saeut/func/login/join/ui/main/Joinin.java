package com.teamtips.android.saeut.func.login.join.ui.main;

import java.util.Date;

public class Joinin {

    //UserEssential
    private String id;
    private String password;
    private String name;
    private String phonenum;
    private int gender;
    private Date birth;
    private boolean perm;

    //UserAdditional
    private String nickname;
    private String zipcode;
    private String address1;
    private String address2;
    private String picpath;
    private String description;
    private int type;

    //constructor
    Joinin() {}

    public Joinin(String id, String password, String name, String phonenum, int gender, Date birth, boolean perm1) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.phonenum = phonenum;
        this.gender = gender;
        this.birth = birth;
        this.perm = perm;
    }

    Joinin(String name, String id, String password, String phonenum, String nickname) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.phonenum = phonenum;
        this.nickname = nickname;
    }

    Joinin(String name, String id, String password, String phonenum, String nickname, String address1, String address2, String picpath, String description) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.phonenum = phonenum;
        this.nickname = nickname;
        this.address1 = address1;
        this.address2 = address2;
        this.picpath = picpath;
        this.description = description;
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

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public boolean isPerm() {
        return perm;
    }

    public void setPerm(boolean perm) {
        this.perm = perm;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
