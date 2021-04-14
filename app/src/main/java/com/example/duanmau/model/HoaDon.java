package com.example.duanmau.model;

import java.util.Date;

public class HoaDon {
    private String maHoaDon;
    private Date ngayMua;

    @Override
    public String toString() {
        return "HoaDon{" +
                "maHoaDon='" + maHoaDon + '\'' +
                ", ngayMua=" + ngayMua +
                '}';
    }

    public HoaDon() {
    }

    public HoaDon(String maHoaDon, Date ngayMua) {
        this.maHoaDon = maHoaDon;
        this.ngayMua = ngayMua;
    }

    public String getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(String maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public Date getNgayMua() {
        return ngayMua;
    }

    public void setNgayMua(Date ngayMua) {
        this.ngayMua = ngayMua;
    }
}
