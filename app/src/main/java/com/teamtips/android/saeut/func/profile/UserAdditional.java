package com.teamtips.android.saeut.func.profile;

public class UserAdditional {

    private String id;
    private String pic = null;
    private String description = null;
    private String address2 = null;
    private String add_latitude = null;
    private String add_longitude = null;
    private double grade = 0;
    private String address1;
    private String nickname;

    public UserAdditional() {
    }

    public UserAdditional(String id, String pic, String description, String address2, String add_latitude, String add_longitude, double grade, String address1, String nickname) {
        this.id = id;
        this.pic = pic;
        this.description = description;
        this.address2 = address2;
        this.add_latitude = add_latitude;
        this.add_longitude = add_longitude;
        this.grade = grade;
        this.address1 = address1;
        this.nickname = nickname;
    }

    public UserAdditional(String id, String address1, String nickname) {
        this.id = id;
        this.pic = "";
        this.description = "";
        this.address2 = "";
        this.add_latitude = "";
        this.add_longitude = "";
        this.grade = 0;
        this.address1 = address1;
        this.nickname = nickname;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }


}
