package com.teamtips.android.saeut.func.dashboard.model;

import java.io.Serializable;

/*
* Demand Domain Class
* */
public class Demand {
	
	private int rank;
	private float score;
	private String description;
	private int type; // (FK)
	private String id; // (FK) - PK

	public Demand() { }
	public Demand(String id, int type, int rank, int score, String description) {
		this.id = id;
		this.type = type;
		this.rank = rank;
		this.score = score;
		this.description = description;
	}
	
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	public float getScore() {
		return score;
	}
	public void setScore(float score) {
		this.score = score;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

}
