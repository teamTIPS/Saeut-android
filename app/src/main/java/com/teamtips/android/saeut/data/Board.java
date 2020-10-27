package com.teamtips.android.saeut.data;

import android.util.Log;

import androidx.annotation.Nullable;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Board {

    private static String[] rankArray = {"먼우주이웃",
            "해외이웃",
            "도시이웃",
            "동네이웃",
            "가까운이웃",
            "옆집이웃",
            "마스터이웃"
    };

    private int post_id;
    private String id;
    private String contents;
    private long post_date;
    private int cnt_like;
    private int cnt_reply;
    private String nickname;
    private String address1;
    private int rank;

    public Board() {
    }

    public String getRankString() {
        return rankArray[rank];
    }

    public int getPost_id() {
        return post_id;
    }

    public void setPost_id(int post_id) {
        this.post_id = post_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public long getPost_date() {
        return post_date;
    }

    public void setPost_date(long post_date) {
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

    public String getAddress1() {
        String[] temp = address1.split(" ");
        String t = temp[0] + " " + temp[1] + " " + temp[2];
        Log.e("Community_getAddress 변환", t);
        return t;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public String getDateByString() {
        SimpleDateFormat transFormat = new SimpleDateFormat("MM/dd HH:mm");
        return transFormat.format(post_date);
    }

    public Board(int post_id, String id, String contents, long post_date, int cnt_like, int cnt_reply, String nickname, String address1, int rank) {
        this.post_id = post_id;
        this.id = id;
        this.contents = contents;
        this.post_date = post_date;
        this.cnt_like = cnt_like;
        this.cnt_reply = cnt_reply;
        this.nickname = nickname;
        this.address1 = address1;
        this.rank = rank;
    }

    public Board(String id, String contents, long post_date, int cnt_like, int cnt_reply, String nickname, String address1, int rank) {
        this.id = id;
        this.contents = contents;
        this.post_date = post_date;
        this.cnt_like = cnt_like;
        this.cnt_reply = cnt_reply;
        this.nickname = nickname;
        this.address1 = address1;
        this.rank = rank;
    }

    public JSONObject CommunityToJsonObject() throws JSONException {
        JSONObject root = new JSONObject();
        root.put("id", id)
                .put("contents", contents)
                .put("post_date", post_date)
                .put("cnt_like", cnt_like)
                .put("cnt_reply", cnt_reply)
                .put("address1", address1)
                .put("nickname", nickname)
                .put("rank", rank);

        return root;

    }
}
