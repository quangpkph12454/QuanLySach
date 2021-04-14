package com.example.duanmau;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Quản Lí Sách");
        setContentView(R.layout.activity_main);
        Intent i = getIntent();
        String user = i.getStringExtra("User");
        Toast.makeText(this,"Chào mừng "+ user +" đã đăng nhập thành công!",Toast.LENGTH_SHORT ).show();
    }

    public void viewNguoiDung(View view) {
        Intent intent = new Intent(MainActivity.this,ListNguoiDungActivity.class);
        startActivity(intent);
    }

    public void viewTheLoai(View view) {
        Intent intent = new Intent(MainActivity.this,ListTheLoaiActivity.class);
        startActivity(intent);
    }

    public void ViewListHoaDonActivity(View view) {
        Intent intent = new Intent(MainActivity.this,ListHoaDonActivity.class);
        startActivity(intent);
    }

    public void viewListBookActivity(View view) {
        Intent intent = new Intent(MainActivity.this,ListBookActivity.class);
        startActivity(intent);
    }

    public void ViewTopSach(View view) {
        Intent intent = new Intent(MainActivity.this,LuotSachBanChayActivity.class);
        startActivity(intent);

    }

    public void ViewThongKeActivity(View view) {
        Intent intent = new Intent(MainActivity.this,ThongKeDoanhThuActivity.class);
        startActivity(intent);
    }
}
