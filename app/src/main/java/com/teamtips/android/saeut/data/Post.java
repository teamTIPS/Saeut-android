package com.teamtips.android.saeut.data;

import java.io.Serializable;
import java.util.List;

/*
 * Post Domain Class
 * */
public class Post implements Serializable {

    // after
    private int post_id;            // sequence
    private String id;              // (FK) account 클래스의 id
    private String title;           // 게시물 제목
    private String post_date;       // 게시글 추가한 날짜
    private String post_age;        // 원하는 연령대
    private String post_schedule;   // 원하는 시간
    private String post_gender;     // 원하는 나잇대
    private String contents;        // 게시글 내용
    private String start_date;      // 돌봄 요청 시작 날짜 -> 모바일 캘린더
    private String due_date;        // 돌봄 요청 마지막 날짜  -> 모바일 캘린더
    private int recruit_status;             // 현재 돌봄 진행상태 (모집 중(0)/모집 마감(1)/완료(2))
    private int type;               // 장애인(0) & 아동(1) & 노인(2)
    private int payment;            // 돌봄요청자 한 명이 돌봄 서비스에 지불하는 시급
    private int wage;               // 돌봄제공자가 받는 시급, 자동 계산
    private int limit_supply;       // 최대 돌봄제공자 수
    private int limit_demand;       // 최대 돌봄요청자 수
    private List<String> tagList;

    // Constructor
    public Post() {
    }

    // new version constructor
    public Post(String id, String title, String post_age, String post_schedule, String post_gender,
                String contents, String start_date, String due_date,
                int recruit_status, int type, int wage, int limit_supply, int limit_demand, List<String> tagList) {
        this.id = id;
        this.title = title;
        this.post_age = post_age;
        this.post_schedule = post_schedule;
        this.post_gender = post_gender;
        this.contents = contents;
        this.start_date = start_date;
        this.due_date = due_date;
        this.recruit_status = recruit_status;
        this.type = type;
        this.wage = wage;
        this.limit_supply = limit_supply;
        this.limit_demand = limit_demand;
        this.payment = getPayment();
        this.tagList = tagList;
    }

    public Post(int post_id, String post_date, String id, String title, String post_age,
                String post_schedule, String post_gender, String contents, String start_date, String due_date,
                int recruit_status, int type, int wage, int limit_supply, int limit_demand, int payment) {
        this.post_id = post_id;
        this.post_date = post_date;
        this.id = id;
        this.title = title;
        this.post_age = post_age;
        this.post_schedule = post_schedule;
        this.post_gender = post_gender;
        this.contents = contents;
        this.start_date = start_date;
        this.due_date = due_date;
        this.recruit_status = recruit_status;
        this.type = type;
        this.payment = payment;
        this.wage = wage;
        this.limit_supply = limit_supply;
        this.limit_demand = limit_demand;
    }

    // Setter & Getter
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContents() {
        return contents;
    }

    public int getRecruit_status() {
        return recruit_status;
    }

    public void setRecruit_status(int recruit_status) {
        this.recruit_status = recruit_status;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getPost_date() {
        return post_date;
    }

    public void setPost_date(String post_date) {
        this.post_date = post_date;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getDue_date() {
        return due_date;
    }

    public void setDue_date(String due_date) {
        this.due_date = due_date;
    }

    public String getPost_age() {
        return post_age;
    }

    public void setPost_age(String post_age) {
        this.post_age = post_age;
    }

    public String getPost_schedule() {
        return post_schedule;
    }

    public void setPost_schedule(String post_schedule) {
        this.post_schedule = post_schedule;
    }

    public String getPost_gender() {
        return post_gender;
    }

    public void setPost_gender(String post_gender) {
        this.post_gender = post_gender;
    }

    public int getPayment() {
        if(limit_demand == 1 || limit_supply == 1) {
            return wage;
        } else {
            return (wage * limit_supply) / limit_demand;
        }
    }

    public void setPayment(int payment) {
        this.payment = payment;
    }

    public int getWage() {
        return wage;
    }

    public void setWage(int wage) {
        this.wage = wage;
    }

    public int getLimit_supply() {
        return limit_supply;
    }

    public void setLimit_supply(int limit_supply) {
        this.limit_supply = limit_supply;
    }

    public int getLimit_demand() {
        return limit_demand;
    }

    public void setLimit_demand(int limit_demand) {
        this.limit_demand = limit_demand;
    }

    public String getStatusForText(int status) {
        if (status == 0) {
            return "모집 중";
        } else if (status == 1) {
            return "모집 마감";
        } else {
            return "완료";
        }
    }

    public String getTypeForText(int type) {
        if(type == 0) {
            return "맞춤돌봄";
        } else {
            return "함께돌봄";
        }
    }

    @Override
    public String toString() {
        return "Post{" +
                "post_id=" + post_id +
                ", id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", post_date='" + post_date + '\'' +
                ", post_age='" + post_age + '\'' +
                ", post_schedule='" + post_schedule + '\'' +
                ", post_gender='" + post_gender + '\'' +
                ", contents='" + contents + '\'' +
                ", start_date='" + start_date + '\'' +
                ", due_date='" + due_date + '\'' +
                ", recruit_status=" + recruit_status +
                ", type=" + type +
                ", payment=" + payment +
                ", wage=" + wage +
                ", limit_supply=" + limit_supply +
                ", limit_demand=" + limit_demand +
                '}';
    }
}