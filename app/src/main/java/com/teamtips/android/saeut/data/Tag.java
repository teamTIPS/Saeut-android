package com.teamtips.android.saeut.data;

public class Tag {

    private int post_id;            // FK Post(post_id)
    private String tag_name;        // 태그 명

    public Tag() {
    }

    public Tag(int post_id, String tag_name) {
        this.post_id = post_id;
        this.tag_name = tag_name;
    }

    public int getPost_id() {
        return post_id;
    }

    public void setPost_id(int post_id) {
        this.post_id = post_id;
    }

    public String getTag_name() {
        return tag_name;
    }

    public void setTag_name(String tag_name) {
        this.tag_name = tag_name;
    }

    @Override
    public String toString() {
        return "Tag{" +
                "post_id=" + post_id +
                ", tag_name='" + tag_name + '\'' +
                '}';
    }
}
