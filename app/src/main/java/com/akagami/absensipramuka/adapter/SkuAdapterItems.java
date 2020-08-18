package com.akagami.absensipramuka.adapter;

public class SkuAdapterItems {

    private String no_sku;
    private String judul_test;
    private String hasil;
    private String nama;

    public SkuAdapterItems(String no_sku, String judul_test, String hasil, String nama) {
        this.no_sku = no_sku;
        this.judul_test = judul_test;
        this.hasil = hasil;
        this.nama = nama;
    }

    public String getNo_sku() { return no_sku; }

    public String getJudul_test() {
        return judul_test;
    }

    public String getHasil() {
        return hasil;
    }

    public String getNama() {
        return nama;
    }
}
