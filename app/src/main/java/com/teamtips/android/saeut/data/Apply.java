package com.teamtips.android.saeut.data;

import java.io.Serializable;

public class Apply implements Serializable {

    private int post_id;
    private String id;
    private String introduce;
    private int type;

    public Apply() { }

    public Apply(int post_id, String id, String introduce, int type) {
        this.post_id = post_id;
        this.id = id;
        this.introduce = introduce;
        this.type = type;
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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Apply{" +
                "post_id=" + post_id +
                ", id='" + id + '\'' +
                ", introduce='" + introduce + '\'' +
                ", type=" + type +
                '}';
    }
}
