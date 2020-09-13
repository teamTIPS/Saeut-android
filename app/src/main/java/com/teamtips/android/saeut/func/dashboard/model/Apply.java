package com.teamtips.android.saeut.func.dashboard.model;

import java.io.Serializable;

public class Apply implements Serializable {

    private int post_id;
    private String id;
    private String introduce;

    public Apply() { }

    public Apply(int post_id, String id, String introduce) {
        this.post_id = post_id;
        this.id = id;
        this.introduce = introduce;
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

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }
}
