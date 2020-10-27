package com.teamtips.android.saeut.data;

public class Rank {

    // 등급 순서 : 내림차순
    private static String[] rankArray = {   "먼우주이웃",
                                            "해외이웃",
                                            "도시이웃",
                                            "동네이웃",
                                            "가까운이웃",
                                            "옆집이웃",
                                            "마스터이웃"
                                        };

    private String id;          // userEssential(id)
    private int rank;           // 등급
    private int score;          // 점수

    public Rank() {
    }

    public Rank(String id, int rank, int score) {
        this.id = id;
        this.rank = rank;
        this.score = score;
    }

    // rank number에 따른 rank String 반환 method
    public String getRankString(int rank) {
        return rankArray[rank];
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "Rank{" +
                "id='" + id + '\'' +
                ", rank=" + rank +
                ", score=" + score +
                '}';
    }
}
