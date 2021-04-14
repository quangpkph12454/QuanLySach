package com.example.duanmau.model;

public class HoaDonChiTiet {
    private int maHDCT;
    private HoaDon hoaDon;
    private Sach sach;
    private int soLuong;

    @Override
    public String toString() {
        return "HoaDonChiTiet{" +
                "maHDCT=" + maHDCT +
                ", hoaDon=" + hoaDon +
                ", sach=" + sach +
                ", soLuong=" + soLuong +
                '}';
    }

    public HoaDonChiTiet() {
    }

    public HoaDonChiTiet(int maHDCT, HoaDon hoaDon, Sach sach, int soLuong) {
        this.maHDCT = maHDCT;
        this.hoaDon = hoaDon;
        this.sach = sach;
        this.soLuong = soLuong;
    }

    public int getMaHDCT() {
        return maHDCT;
    }

    public void setMaHDCT(int maHDCT) {
        this.maHDCT = maHDCT;
    }

    public HoaDon getHoaDon() {
        return hoaDon;
    }

    public void setHoaDon(HoaDon hoaDon) {
        this.hoaDon = hoaDon;
    }

    public Sach getSach() {
        return sach;
    }

    public void setSach(Sach sach) {
        this.sach = sach;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }
}
