package com.teamtips.android.saeut.data;

public class Review {

    private int review_id;          // sequence
    private String writer;          // userEssential(id)
    private String reviewer;        // userEssential(id)
    private int grade;              // 평점
    private String reply;           // 내용

    public Review() {
    }

    public Review(int review_id, String writer, String reviewer, int grade, String reply) {
        this.review_id = review_id;
        this.writer = writer;
        this.reviewer = reviewer;
        this.grade = grade;
        this.reply = reply;
    }

    public int getReview_id() {
        return review_id;
    }

    public void setReview_id(int review_id) {
        this.review_id = review_id;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getReviewer() {
        return reviewer;
    }

    public void setReviewer(String reviewer) {
        this.reviewer = reviewer;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    @Override
    public String toString() {
        return "Review{" +
                "review_id=" + review_id +
                ", writer='" + writer + '\'' +
                ", reviewer='" + reviewer + '\'' +
                ", grade=" + grade +
                ", reply='" + reply + '\'' +
                '}';
    }
}
