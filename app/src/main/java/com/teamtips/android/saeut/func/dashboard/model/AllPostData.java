package com.teamtips.android.saeut.func.dashboard.model;

import java.io.Serializable;
import java.util.ArrayList;

// Post, Demand(Account), Number of Apply User Data Class
public class AllPostData implements Serializable {
    private int post_id;
    private Post post;
//    private UserEssential userEssential;
//    private UserAdditional userAdditional;
    private ArrayList<Apply> applyArrayList;
    private int applyCount;

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public int getApplyCount() {
        return applyCount;
    }

    public void setApplyCount(int applyCount) {
        this.applyCount = applyCount;
    }
}
