package com.akagami.absensipramuka.adapter;

public class SkuAdapterItems {

    private String nomor;
    private String judul_test;
    private String hasil;
    private String penguji;

    public SkuAdapterItems(String nomor, String judul_test, String hasil, String penguji) {
        this.nomor = nomor;
        this.judul_test = judul_test;
        this.hasil = hasil;
        this.penguji = penguji;
    }

    public String getNomor() {
        return nomor;
    }

    public String getJudul_test() {
        return judul_test;
    }

    public String getHasil() {
        return hasil;
    }

    public String getPenguji() {
        return penguji;
    }
}
