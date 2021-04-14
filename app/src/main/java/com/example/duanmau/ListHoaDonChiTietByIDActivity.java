package com.example.duanmau;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import com.example.duanmau.adapter.CartAdapter;
import com.example.duanmau.dao.HoaDonChiTietDao;
import com.example.duanmau.model.HoaDonChiTiet;

import java.util.ArrayList;
import java.util.List;

public class ListHoaDonChiTietByIDActivity extends AppCompatActivity {
    public List<HoaDonChiTiet> dsHDCT = new ArrayList<>();
    ListView lvCart;
    CartAdapter adapter = null;
    HoaDonChiTietDao hoaDonChiTietDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_hoa_don_chi_tiet_by_id);
        setTitle("HÓA ĐƠN CHI TIẾT");
        lvCart = findViewById(R.id.lvHoaDonChiTiet);
        hoaDonChiTietDao = new HoaDonChiTietDao(ListHoaDonChiTietByIDActivity.this);
        Intent i = getIntent();
        Bundle b = i.getExtras();
        if (b!=null){
            dsHDCT = hoaDonChiTietDao.getAllHoaDonChiTietbyID(b.getString("maHoaDon"));
        }
        adapter = new CartAdapter(this,dsHDCT);
        lvCart.setAdapter(adapter);
    }
}
