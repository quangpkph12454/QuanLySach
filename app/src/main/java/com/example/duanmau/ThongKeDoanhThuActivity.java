package com.example.duanmau;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.duanmau.dao.HoaDonChiTietDao;

public class ThongKeDoanhThuActivity extends AppCompatActivity {
    TextView tvNgay, tvThang ,tvNam;
    HoaDonChiTietDao hoaDonChiTietDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_ke_doanh_thu);
        setTitle("DOANH THU");
        tvNgay = findViewById(R.id.tvThongKeNgay);
        tvThang = findViewById(R.id.tvThongKeThang);
        tvNam = findViewById(R.id.tvThongKeNam);
        hoaDonChiTietDao = new HoaDonChiTietDao(this);
        tvNam.setText("Năm này: "+ hoaDonChiTietDao.getDoanhThuNam());
        tvThang.setText("Tháng này: " + hoaDonChiTietDao.getDoanhThuThang());
        tvNgay.setText("Hôm nay: " + hoaDonChiTietDao.getDoanhThuTheoNgay());
    }
}
