package com.example.formulaapp.Models;

public class User {

    private String id;
    private String email;
    private String username;
    private String imageUrl;
    private String status;
    private Integer dvigatel;
    private Integer transmissiya;
    private Integer podveska;
    private Integer rul;
    private Integer ohlazhdeniye;
    private Integer zajiganiye;
    private Integer toplivo;
    private Integer tormoz;
    private Integer electro;
    private Integer datchiki;
    private Integer kuzov;
    private Integer salon;
    private Integer masla;

    public User() {
    }

    public User(String id, String email, String username, String imageUrl, String status, Integer dvigatel, Integer transmissiya, Integer podveska, Integer rul, Integer ohlazhdeniye, Integer zajiganiye, Integer toplivo, Integer tormoz, Integer electro, Integer datchiki, Integer kuzov, Integer salon, Integer masla) {
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

    public Integer getDvigatel() {
        return dvigatel;
    }

    public void setDvigatel(Integer dvigatel) {
        this.dvigatel = dvigatel;
    }

    public Integer getTransmissiya() {
        return transmissiya;
    }

    public void setTransmissiya(Integer transmissiya) {
        this.transmissiya = transmissiya;
    }

    public Integer getPodveska() {
        return podveska;
    }

    public void setPodveska(Integer podveska) {
        this.podveska = podveska;
    }

    public Integer getRul() {
        return rul;
    }

    public void setRul(Integer rul) {
        this.rul = rul;
    }

    public Integer getOhlazhdeniye() {
        return ohlazhdeniye;
    }

    public void setOhlazhdeniye(Integer ohlazhdeniye) {
        this.ohlazhdeniye = ohlazhdeniye;
    }

    public Integer getZajiganiye() {
        return zajiganiye;
    }

    public void setZajiganiye(Integer zajiganiye) {
        this.zajiganiye = zajiganiye;
    }

    public Integer getToplivo() {
        return toplivo;
    }

    public void setToplivo(Integer toplivo) {
        this.toplivo = toplivo;
    }

    public Integer getTormoz() {
        return tormoz;
    }

    public void setTormoz(Integer tormoz) {
        this.tormoz = tormoz;
    }

    public Integer getElectro() {
        return electro;
    }

    public void setElectro(Integer electro) {
        this.electro = electro;
    }

    public Integer getDatchiki() {
        return datchiki;
    }

    public void setDatchiki(Integer datchiki) {
        this.datchiki = datchiki;
    }

    public Integer getKuzov() {
        return kuzov;
    }

    public void setKuzov(Integer kuzov) {
        this.kuzov = kuzov;
    }

    public Integer getSalon() {
        return salon;
    }

    public void setSalon(Integer salon) {
        this.salon = salon;
    }

    public Integer getMasla() {
        return masla;
    }

    public void setMasla(Integer masla) {
        this.masla = masla;
    }
}
