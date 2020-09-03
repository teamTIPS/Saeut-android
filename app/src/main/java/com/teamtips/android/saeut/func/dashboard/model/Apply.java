package com.teamtips.android.saeut.func.dashboard.model;

public class Apply {

	private int post_id; // (FK)
	private String account_id; // (FK)
	private String introduce;

	public Apply() { }
	public Apply(int post_id, String account_id, String introduce) {
		this.account_id = account_id;
		this.post_id = post_id;
		this.introduce = introduce;
	}

	public int getPost_id() {
		return post_id;
	}
	public void setPost_id(int post_id) {
		this.post_id = post_id;
	}
	public String getAccount_id() {
		return account_id;
	}
	public void setAccount_id(String account_id) {
		this.account_id = account_id;
	}
	public String getIntroduce() {
		return introduce;
	}
	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
	
}
