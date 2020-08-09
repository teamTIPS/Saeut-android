package com.teamtips.android.saeut.dashboard.model;

import java.util.Date;

/*
 * Post Domain Class
 * */

public class Post {

    // Variable
    private int post_id; //sequence
    private int account_id; // (FK) account 클래스의 id
    private String title; //게시물 제목
    private Date post_date; //게시글 추가한 날짜
    private String contents; // 게시글 내용
    private Date start_date; // 돌봄 요청 시작 날짜 -> 모바일 캘린더
    private Date due_date; // 돌봄 요청 마지막 날짜  -> 모바일 캘린더
    private Date birth; // 돌봄이 필요한 대상의 생년월일 -> 모바일 캘린더
    private String address1; // 돌봄이 필요한 대상의 주소1
    private String address2; // 돌봄이 필요한 대상의 주소2

    // Constructor
    public Post() { }

    public Post(int post_id, int account_id, String title, Date post_date, String address1) {
        this.post_id = post_id;
        this.account_id = account_id;
        this.title = title;
        this.post_date = post_date;
        this.address1 = address1;
    }

    public Post(int post_id, int account_id, String title, Date post_date, String contents, Date start_date, Date due_date, Date birth, String address1, String address2) {
        this.post_id = post_id;
        this.account_id = account_id;
        this.title = title;
        this.post_date = post_date;
        this.contents = contents;
        this.start_date = start_date;
        this.due_date = due_date;
        this.birth = birth;
        this.address1 = address1;
        this.address2 = address2;
    }

    // Setter & Getter
    public int getPost_id() {
        return post_id;
    }
    public void setPost_id(int post_id) {
        this.post_id = post_id;
    }
    public int getAccount_id() {
        return account_id;
    }
    public void setAccount_id(int account_id) {
        this.account_id = account_id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public Date getPost_date() {
        return post_date;
    }
    public void setPost_date(Date post_date) {
        this.post_date = post_date;
    }
    public String getContents() {
        return contents;
    }
    public void setContents(String contents) {
        this.contents = contents;
    }
    public Date getStart_date() {
        return start_date;
    }
    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }
    public Date getDue_date() {
        return due_date;
    }
    public void setDue_date(Date due_date) {
        this.due_date = due_date;
    }
    public Date getBirth() {
        return birth;
    }
    public void setBirth(Date birth) {
        this.birth = birth;
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
}