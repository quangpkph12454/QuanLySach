package com.example.duanmau.model;

public class NguoiDung {
    private String username,password,phone,hoten;

    public NguoiDung(String username, String password, String phone, String hoten) {
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.hoten = hoten;
    }

    public NguoiDung() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }
}
