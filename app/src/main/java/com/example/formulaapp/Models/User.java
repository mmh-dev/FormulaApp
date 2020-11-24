package com.example.formulaapp.Models;

import java.io.Serializable;

public class User implements Serializable {

    private String id;
    private String email;
    private String username;
    private String imageUrl;
    private String status;
    private int dvigatel;
    private int transmissiya;
    private int podveska;
    private int rul;
    private int ohlazhdeniye;
    private int zajiganiye;
    private int toplivo;
    private int tormoz;
    private int electro;
    private int datchiki;
    private int kuzov;
    private int salon;
    private int masla;
    private int totalPoints;
    private int allPoints;
    private int ranking;

    public User() {
    }

    public User(String id, String email, String username, String imageUrl, String status, int dvigatel, int transmissiya, int podveska, int rul, int ohlazhdeniye, int zajiganiye, int toplivo, int tormoz, int electro, int datchiki, int kuzov, int salon, int masla, int totalPoints, int allPoints, int ranking) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.imageUrl = imageUrl;
        this.status = status;
        this.dvigatel = dvigatel;
        this.transmissiya = transmissiya;
        this.podveska = podveska;
        this.rul = rul;
        this.ohlazhdeniye = ohlazhdeniye;
        this.zajiganiye = zajiganiye;
        this.toplivo = toplivo;
        this.tormoz = tormoz;
        this.electro = electro;
        this.datchiki = datchiki;
        this.kuzov = kuzov;
        this.salon = salon;
        this.masla = masla;
        this.totalPoints = totalPoints;
        this.allPoints = allPoints;
        this.ranking = ranking;
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

    public int getDvigatel() {
        return dvigatel;
    }

    public void setDvigatel(int dvigatel) {
        this.dvigatel = dvigatel;
    }

    public int getTransmissiya() {
        return transmissiya;
    }

    public void setTransmissiya(int transmissiya) {
        this.transmissiya = transmissiya;
    }

    public int getPodveska() {
        return podveska;
    }

    public void setPodveska(int podveska) {
        this.podveska = podveska;
    }

    public int getRul() {
        return rul;
    }

    public void setRul(int rul) {
        this.rul = rul;
    }

    public int getOhlazhdeniye() {
        return ohlazhdeniye;
    }

    public void setOhlazhdeniye(int ohlazhdeniye) {
        this.ohlazhdeniye = ohlazhdeniye;
    }

    public int getZajiganiye() {
        return zajiganiye;
    }

    public void setZajiganiye(int zajiganiye) {
        this.zajiganiye = zajiganiye;
    }

    public int getToplivo() {
        return toplivo;
    }

    public void setToplivo(int toplivo) {
        this.toplivo = toplivo;
    }

    public int getTormoz() {
        return tormoz;
    }

    public void setTormoz(int tormoz) {
        this.tormoz = tormoz;
    }

    public int getElectro() {
        return electro;
    }

    public void setElectro(int electro) {
        this.electro = electro;
    }

    public int getDatchiki() {
        return datchiki;
    }

    public void setDatchiki(int datchiki) {
        this.datchiki = datchiki;
    }

    public int getKuzov() {
        return kuzov;
    }

    public void setKuzov(int kuzov) {
        this.kuzov = kuzov;
    }

    public int getSalon() {
        return salon;
    }

    public void setSalon(int salon) {
        this.salon = salon;
    }

    public int getMasla() {
        return masla;
    }

    public void setMasla(int masla) {
        this.masla = masla;
    }

    public int getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints(int totalPoints) {
        this.totalPoints = totalPoints;
    }

    public int getAllPoints() {
        return dvigatel + transmissiya + podveska + rul + ohlazhdeniye + zajiganiye + toplivo + tormoz + electro + datchiki + kuzov + salon + masla + totalPoints;
    }

    public void setAllPoints(int allPoints) {
        this.allPoints = allPoints;
    }

    public int getRanking() {
        return ranking;
    }

    public void setRanking(int ranking) {
        this.ranking = ranking;
    }
}
