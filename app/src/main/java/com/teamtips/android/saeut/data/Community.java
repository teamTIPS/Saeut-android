package com.teamtips.android.saeut.data;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Community {

    private int post_id;


    private int id;
    private String contents;
    private Date post_date;
    private int cnt_like;
    private int cnt_reply;
    private String nickname;
    private String addess;
    private String rank;

    public Community() {
    }

    public int getPost_id() {
        return post_id;
    }

    public void setPost_id(int post_id) {
        this.post_id = post_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public Date getPost_date() {
        return post_date;
    }

    public void setPost_date(Date post_date) {
        this.post_date = post_date;
    }

    public int getCnt_like() {
        return cnt_like;
    }

    public void setCnt_like(int cnt_like) {
        this.cnt_like = cnt_like;
    }

    public int getCnt_reply() {
        return cnt_reply;
    }

    public void setCnt_reply(int cnt_reply) {
        this.cnt_reply = cnt_reply;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAddess() {
        return addess;
    }

    public void setAddess(String addess) {
        this.addess = addess;
    }

    public String getRank() {
        return rank;
    }

    public String getDateByString(){
        SimpleDateFormat transFormat = new SimpleDateFormat("MM/dd HH:mm");
        return transFormat.format(post_date);
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public Community(int post_id, int id, String contents, Date post_date, int cnt_like, int cnt_reply, String nickname, String addess, String rank) {
        this.post_id = post_id;
        this.id = id;
        this.contents = contents;
        this.post_date = post_date;
        this.cnt_like = cnt_like;
        this.cnt_reply = cnt_reply;
        this.nickname = nickname;
        this.addess = addess;
        this.rank = rank;
    }
}
