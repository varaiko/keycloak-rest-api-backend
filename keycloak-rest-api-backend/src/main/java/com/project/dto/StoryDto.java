package com.project.dto;

import java.util.Date;

public class StoryDto {

    private long id;
    private String username;
    private String title;
    private String content;
    private String roleName;
    private Date date;
    private String url;

    public StoryDto(long id, String username, String title, String content, String roleName, Date date, String url) {
        this.id = id;
        this.username = username;
        this.title = title;
        this.content = content;
        this.roleName = roleName;
        this.date = date;
        this.url = url;
    }

    public StoryDto() {
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

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
