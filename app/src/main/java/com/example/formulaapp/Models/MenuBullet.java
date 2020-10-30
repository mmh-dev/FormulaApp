package com.example.formulaapp.Models;

public class MenuBullet {
    private String header;
    private String desc;
    private int catImage;

    public MenuBullet(String header, String desc, int catImage) {
        this.header = header;
        this.desc = desc;
        this.catImage = catImage;
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
}
