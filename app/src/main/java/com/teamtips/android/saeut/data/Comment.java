package com.teamtips.android.saeut.data;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Comment {

    private int comment_id;	 	// sequence
    private int post_id;		// FK Post(post_id)
    private String id;			// FK UserEssential(id)
    private String contents; 	// 댓글 내용
    private Date write_time; 	// 작성 시간

    public Comment() {
    }

    public Comment(int comment_id, int post_id, String id, String contents, Date write_time) {
        this.comment_id = comment_id;
        this.post_id = post_id;
        this.id = id;
        this.contents = contents;
        this.write_time = write_time;
    }

    public int getComment_id() {
        return comment_id;
    }

    public void setComment_id(int comment_id) {
        this.comment_id = comment_id;
    }

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

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public Date getWrite_time() {
        return write_time;
    }

    public String getDateByString() {
        SimpleDateFormat transFormat = new SimpleDateFormat("MM/dd HH:mm");
        return transFormat.format(write_time);
    }

    public void setWrite_time(Date write_time) {
        this.write_time = write_time;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "comment_id=" + comment_id +
                ", post_id=" + post_id +
                ", id='" + id + '\'' +
                ", contents='" + contents + '\'' +
                ", write_time=" + write_time +
                '}';
    }
}
