package com.teamtips.android.saeut.data;

public class Group {

    private int seq_id;         // sequence
    private int post_id;        // Post(post_id)
    private String captain;     // userEssential(id) - 그룹장
    private String id;          // userEssential(id) - 그룹원
    private int type;

    public Group() { }

    public Group(int seq_id, int post_id, String captain, String id, int type) {
        this.seq_id = seq_id;
        this.post_id = post_id;
        this.captain = captain;
        this.id = id;
        this.type = type;
    }

    public int getSeq_id() {
        return seq_id;
    }

    public void setSeq_id(int seq_id) {
        this.seq_id = seq_id;
    }

    public int getPost_id() {
        return post_id;
    }

    public void setPost_id(int post_id) {
        this.post_id = post_id;
    }

    public String getCaptain() {
        return captain;
    }

    public void setCaptain(String captain) {
        this.captain = captain;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Group{" +
                "seq_id=" + seq_id +
                ", post_id=" + post_id +
                ", captain='" + captain + '\'' +
                ", id='" + id + '\'' +
                ", type=" + type +
                '}';
    }
}
