package com.example.duanmau;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.duanmau.adapter.CartAdapter;
import com.example.duanmau.dao.HoaDonChiTietDao;
import com.example.duanmau.dao.SachDao;
import com.example.duanmau.model.HoaDon;
import com.example.duanmau.model.HoaDonChiTiet;
import com.example.duanmau.model.Sach;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HoaDonChiTietActivity extends AppCompatActivity {
    EditText edMaHoaDon,edSoLuong;
    Spinner spMaSach;
    TextView tvThanhTien;
    HoaDonChiTietDao hoaDonChiTietDao;
    SachDao sachDao;
    public List<HoaDonChiTiet> dsHDCT = new ArrayList<>();
    ListView lvCart;
    CartAdapter adapter = null;
    double thanhTien = 0;
    String maSach= "";
    List<Sach> sach = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hoa_don_chi_tiet);
        setTitle("CHI TIẾT HÓA ĐƠN");

        edMaHoaDon = findViewById(R.id.edMaHoaDon);
        edSoLuong = findViewById(R.id.edSoLuongMua);
        tvThanhTien = findViewById(R.id.tvThanhTien);
        lvCart = findViewById(R.id.lvCart);

        spMaSach = findViewById(R.id.spinnerMaSach);
        getHoaDon();

        adapter = new CartAdapter(this,dsHDCT);
        lvCart.setAdapter(adapter);
        Intent i = getIntent();
        Bundle b = i.getExtras();
        if (b!=null){
            edMaHoaDon.setText(b.getString("maHoaDon"));

        }
        spMaSach.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                maSach = sach.get(spMaSach.getSelectedItemPosition()).getMaSach();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    private int checkPositionSach(String maSach) {
        for (int i=0 ; i < sach.size();i++){
            if (maSach.equalsIgnoreCase(sach.get(i).getMaSach())){
                return i;
            }
        }
        return 0;
    }

    private void getHoaDon() {
        sachDao = new SachDao(HoaDonChiTietActivity.this);
        sach = sachDao.getAllSach();
        ArrayAdapter<Sach> adapter = new ArrayAdapter<Sach>(this,android.R.layout.simple_spinner_item,sach);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spMaSach.setAdapter(adapter);
    }

    public void ADDHoaDonCHITIET(View view) {
        hoaDonChiTietDao = new HoaDonChiTietDao(HoaDonChiTietActivity.this);
        sachDao = new SachDao(HoaDonChiTietActivity.this);
        try {
            if (validation()<0){
                Toast.makeText(getApplicationContext(),"Vui lòng nhập đầy đủ thông tin",Toast.LENGTH_SHORT).show();
            }else {
                Sach sach = sachDao.getSachByID(spMaSach.getSelectedItem().toString());
                if (sach != null){
                    int pos = checkMaSach(dsHDCT, spMaSach.getSelectedItem().toString());
                    HoaDon hoaDon = new HoaDon(edMaHoaDon.getText().toString(),new Date());
                    HoaDonChiTiet hoaDonChiTiet = new HoaDonChiTiet(1,hoaDon,sach,Integer.parseInt(edSoLuong.getText().toString()));
                    if (pos>=0){
                        int soLuong = dsHDCT.get(pos).getSoLuong();
                        hoaDonChiTiet.setSoLuong(soLuong + Integer.parseInt(edSoLuong.getText().toString()));
                        dsHDCT.set(pos,hoaDonChiTiet);
                    }else {
                        dsHDCT.add(hoaDonChiTiet);
                    }
                    adapter.changeDataset(dsHDCT);
                }else {
                    Toast.makeText(getApplicationContext(),"Mã sách không tồn tại",Toast.LENGTH_SHORT).show();
                }
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

    }

    public void thanhToanHoaDon(View view) {
        hoaDonChiTietDao = new HoaDonChiTietDao((HoaDonChiTietActivity.this));
        thanhTien =0;
        try {
            for (HoaDonChiTiet hd :dsHDCT){
                hoaDonChiTietDao.insertHoaDonChiTiet(hd);
                thanhTien = thanhTien + hd.getSoLuong() * hd.getSach().getGiaBia();
            }
            tvThanhTien.setText("Tổng tiền : " + thanhTien);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int checkMaSach(List<HoaDonChiTiet> lsHD,String maSach){
        int pos = -1;
        for (int i=0 ;i<lsHD.size() ; i++){
            HoaDonChiTiet hdct = lsHD.get(i);
            if (hdct.getSach().getMaSach().equalsIgnoreCase(maSach)){
                pos = i;
                break;
            }
        }
        return pos;
    }
    public int validation(){
        if ( edMaHoaDon.getText().toString().isEmpty()|| edSoLuong.getText().toString().isEmpty()){
            return -1;
        }
        return 1;
    }
}
