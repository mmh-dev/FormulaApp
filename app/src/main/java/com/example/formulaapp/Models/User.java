package com.example.formulaapp.Models;

public class User {

    private String id;
    private String email;
    private String username;
    private String imageUrl;
    private boolean hasChat;
    private String status;

    public User(String id, String email, String username, String imageUrl, boolean hasChat, String status) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.imageUrl = imageUrl;
        this.hasChat = hasChat;
        this.status = status;
    }

    public User() {
    }

    public boolean isHasChat() {
        return hasChat;
    }

    public void setHasChat(boolean hasChat) {
        this.hasChat = hasChat;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
