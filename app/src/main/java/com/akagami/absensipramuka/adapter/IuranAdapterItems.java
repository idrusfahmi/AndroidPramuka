package com.akagami.absensipramuka.adapter;

public class IuranAdapterItems {
    private String bulan;
    private String iuran_minggu1;
    private String iuran_minggu2;
    private String iuran_minggu3;
    private String iuran_minggu4;
    private String iuran_minggu5;

    public IuranAdapterItems(String bulan, String iuran_minggu1, String iuran_minggu2, String iuran_minggu3, String iuran_minggu4, String iuran_minggu5) {
        this.bulan = bulan;
        this.iuran_minggu1 = iuran_minggu1;
        this.iuran_minggu2 = iuran_minggu2;
        this.iuran_minggu3 = iuran_minggu3;
        this.iuran_minggu4 = iuran_minggu4;
        this.iuran_minggu5 = iuran_minggu5;
    }

    public String getBulan() {
        return bulan;
    }

    public String getIuran_minggu1() {
        return iuran_minggu1;
    }

    public String getIuran_minggu2() {
        return iuran_minggu2;
    }

    public String getIuran_minggu3() {
        return iuran_minggu3;
    }

    public String getIuran_minggu4() {
        return iuran_minggu4;
    }

    public String getIuran_minggu5() {
        return iuran_minggu5;
    }
}
