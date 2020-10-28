package com.example.formulaapp.Models;

public class Points {

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
    private int neispavnosti;

    public Points(int dvigatel, int transmissiya, int podveska, int rul, int ohlazhdeniye, int zajiganiye, int toplivo, int tormoz, int electro, int datchiki, int kuzov, int salon, int masla, int neispavnosti) {
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
        this.neispavnosti = neispavnosti;
    }

    public Points() {
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

    public int getNeispavnosti() {
        return neispavnosti;
    }

    public void setNeispavnosti(int neispavnosti) {
        this.neispavnosti = neispavnosti;
    }
}
