package com.example.formulaapp.Models;

public class MenuBullet {
    private String header;
    private String desc;
    private int catImage;
    private int questions;

    public MenuBullet() {
    }

    public MenuBullet(String header, String desc, int catImage, int questions) {
        this.header = header;
        this.desc = desc;
        this.catImage = catImage;
        this.questions = questions;
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

    public int getQuestions() {
        return questions;
    }

    public void setQuestions(int questions) {
        this.questions = questions;
    }
}
