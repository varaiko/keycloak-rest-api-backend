package com.project.dto;

import java.util.Date;

public class CommentDto {

    private long id;
    private String username;
    private String comment;
    private Date date;

    public CommentDto(long id, String username, String comment, Date date) {
        this.id = id;
        this.username = username;
        this.comment = comment;
        this.date = date;
    }

    public CommentDto() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
