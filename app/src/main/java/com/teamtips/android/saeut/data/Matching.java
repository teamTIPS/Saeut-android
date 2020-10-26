package com.teamtips.android.saeut.data;

import java.io.Serializable;

public class Matching  implements Serializable {
    private String post_schedule;   // 원하는 시간
    private int wage;               // 원하는 시급
    private String location;        // 원하는 위치
    private int type;               // 타입 (돌봄제공자/돌봄요청자)

    public Matching() { }
    public Matching(String post_schedule, int wage, String location, int type) {
        this.post_schedule = post_schedule;
        this.wage = wage;
        this.location = location;
        this.type = type;
    }

    public String getPost_schedule() {
        return post_schedule;
    }

    public void setPost_schedule(String post_schedule) {
        this.post_schedule = post_schedule;
    }

    public int getWage() {
        return wage;
    }

    public void setWage(int wage) {
        this.wage = wage;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Matching{" +
                "post_schedule='" + post_schedule + '\'' +
                ", wage=" + wage +
                ", location='" + location + '\'' +
                ", type=" + type +
                '}';
    }
}
