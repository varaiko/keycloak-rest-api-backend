package com.keycloak.keycloak_integration.entity;

import jakarta.persistence.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "stories")
public class Story {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long id;

    private String username;

    private String title;

    private String content;

    private String roleName;

    private Date date;
    private String url;

    public Story(long id, String username, String title, String content, String roleName, Date date, String url, Set<Comment> comments) {
        this.id = id;
        this.username = username;
        this.title = title;
        this.content = content;
        this.roleName = roleName;
        this.date = date;
        this.url = url;
        this.comments = comments;
    }

    public Story() {
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

    public void setUsername(String name) {
        this.username = name;
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

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
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

    @OneToMany(mappedBy = "story", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Comment> comments = new HashSet<>();


}
