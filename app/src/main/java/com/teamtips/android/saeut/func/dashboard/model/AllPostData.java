package com.teamtips.android.saeut.func.dashboard.model;

// Post, Demand(Account), Number of Apply User Data Class
public class AllPostData {
    private Post post;
    private Demand demand;
    private int applyCount;

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public Demand getDemand() {
        return demand;
    }

    public void setDemand(Demand demand) {
        this.demand = demand;
    }

    public int getApplyCount() {
        return applyCount;
    }

    public void setApplyCount(int applyCount) {
        this.applyCount = applyCount;
    }
}
