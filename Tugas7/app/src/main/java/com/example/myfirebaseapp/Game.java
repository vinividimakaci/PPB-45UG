package com.example.myfirebaseapp;

public class Game {
    private String judul_game;
    private String tahun_rilis;
    private String pencipta;
    private String tokoh;

    public Game(String judul_game, String tahun_rilis, String pencipta, String tokoh) {
        this.judul_game = judul_game;
        this.tahun_rilis = tahun_rilis;
        this.pencipta = pencipta;
        this.tokoh = tokoh;
    }

    public String getJudul_game() {
        return judul_game;
    }

    public void setJudul_game(String judul_game) {
        this.judul_game = judul_game;
    }

    public String getTahun_rilis() {
        return tahun_rilis;
    }

    public void setTahun_rilis(String tahun_rilis) {
        this.tahun_rilis = tahun_rilis;
    }

    public String getPencipta() {
        return pencipta;
    }

    public void setPencipta(String pencipta) {
        this.pencipta = pencipta;
    }

    public String getTokoh() {
        return tokoh;
    }

    public void setTokoh(String tokoh) {
        this.tokoh = tokoh;
    }
}
