package com.example.duanmau.model;

public class TheLoai {
    private String maTheLoai,tenTheLoai,vitri,mota;

    public TheLoai() {
    }

    public String getMaTheLoai() {
        return maTheLoai;
    }

    public void setMaTheLoai(String maTheLoai) {
        this.maTheLoai = maTheLoai;
    }

    public String getTenTheLoai() {
        return tenTheLoai;
    }

    public void setTenTheLoai(String tenTheLoai) {
        this.tenTheLoai = tenTheLoai;
    }

    public String getVitri() {
        return vitri;
    }

    public void setVitri(String vitri) {
        this.vitri = vitri;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }

    public TheLoai(String maTheLoai, String tenTheLoai, String vitri, String mota) {
        this.maTheLoai = maTheLoai;
        this.tenTheLoai = tenTheLoai;
        this.vitri = vitri;
        this.mota = mota;
    }

    @Override
    public String toString() {
        return tenTheLoai;
    }
}
