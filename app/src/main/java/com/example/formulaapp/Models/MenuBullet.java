package com.example.formulaapp.Models;

public class MenuBullet {
    private String header;
    private String desc;
    private int catImage;
    private String user_photo;
    private Integer questions;
    private String user_email;

    public MenuBullet() {
    }

    public MenuBullet(String header, String desc, int catImage, String user_photo, Integer questions, String user_email) {
        this.header = header;
        this.desc = desc;
        this.catImage = catImage;
        this.user_photo = user_photo;
        this.questions = questions;
        this.user_email = user_email;
    }

    public String getHeader() {
        return header;
    }

    public String getDesc() {
        return desc;
    }

    public int getCatImage() {
        return catImage;
    }

    public Integer getQuestions() {
        return questions;
    }

    public void setQuestions(Integer questions) {
        this.questions = questions;
    }

    public String getUser_photo() {
        return user_photo;
    }

    public void setUser_photo(String user_photo) {
        this.user_photo = user_photo;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }
}
