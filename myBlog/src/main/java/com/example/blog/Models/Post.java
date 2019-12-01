package com.example.blog.Models;

import java.sql.Date;

public class Post {

    private int id;
    private String title;
    private String body;
    private Date dt;
    private int userId;

    public Post() {
    }

    public Post(int id, String title, String body, Date dt, int userId) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.dt = dt;
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Date getDt() {
        return dt;
    }

    public void setDt(Date dt) {
        this.dt = dt;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                ", dt=" + dt +
                ", userId=" + userId +
                '}';
    }
}
