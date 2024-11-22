package com.webproject.minisns.dto;

import java.sql.Timestamp;

public class PostDTO {

    private String username;
    private String content;
    private Timestamp createdAt;

    public PostDTO(String username, String content, Timestamp createdAt) {
        this.username = username;
        this.content = content;
        this.createdAt = createdAt;
    }

    // Getters and Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}
