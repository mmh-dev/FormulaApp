package com.example.formulaapp.Models;

import java.util.List;

public class User {

    private String id;
    private String email;
    private String username;
    private String imageUrl;
    private String status;
    private List<Points> pointsList;

    public User(String id, String email, String username, String imageUrl, String status, List<Points> pointsList) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.imageUrl = imageUrl;
        this.status = status;
        this.pointsList = pointsList;
    }

    public User() {
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Points> getPointsList() {
        return pointsList;
    }

    public void setPointsList(List<Points> pointsList) {
        this.pointsList = pointsList;
    }
}
