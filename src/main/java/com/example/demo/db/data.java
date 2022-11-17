package com.example.demo.db;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class data {
    private int Id;
    private String title;
    private String content;
    private int userId;
    private Timestamp postTime;

    public int getdataId() {
        return Id;
    }

    public void setdataId(int blogId) {
        this.Id = blogId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getPostTime() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.format(postTime);
    }


    public void setPostTime(Timestamp postTime) {
        this.postTime = postTime;
    }
}

