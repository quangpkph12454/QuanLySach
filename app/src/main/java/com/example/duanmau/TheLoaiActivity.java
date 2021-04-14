package com.example.duanmau;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.duanmau.dao.NguoiDungDao;
import com.example.duanmau.dao.TheLoaiDao;
import com.example.duanmau.model.NguoiDung;
import com.example.duanmau.model.TheLoai;

public class TheLoaiActivity extends AppCompatActivity {
    TheLoaiDao theLoaiDao;
    EditText edMaTheLoai,edTenTheLoai,edViTri,edMoTa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_the_loai);
        edMaTheLoai = findViewById(R.id.edMaTheLoai);
        edTenTheLoai = findViewById(R.id.edTenTheLoai);
        edViTri = findViewById(R.id.edViTri);
        edMoTa = findViewById(R.id.edMoTa);

        Intent i = getIntent();
        Bundle b = i.getExtras();
        if (b!=null){
            edMaTheLoai.setText(b.getString("maTheLoai"));
            edTenTheLoai.setText(b.getString("tenTheLoai"));
            edMoTa.setText(b.getString("moTa"));
            edViTri.setText(b.getString("viTri"));
        }
    }

    public void addTheLoai(View view) {
        theLoaiDao = new TheLoaiDao(TheLoaiActivity.this);
        try {
           if (validation()<0){
               Toast.makeText(getApplicationContext(), "Vui lòng điền đủ thông tin",Toast.LENGTH_SHORT).show();
           }else {
               TheLoai n = new TheLoai(edMaTheLoai.getText().toString(),
                       edTenTheLoai.getText().toString(),
                       edViTri.getText().toString(),
                       edMoTa.getText().toString());
               if (theLoaiDao.insertTheLoai(n)>0){
                   Toast.makeText(getApplicationContext(),"Insert successful",Toast.LENGTH_SHORT).show();
               }else {
                   Toast.makeText(getApplicationContext(),"Insert not successful",Toast.LENGTH_SHORT).show();
               }
           }
        }catch (Exception e){
            Log.e("TheLoaiActivity_TAG",e.getMessage());
        }
    }

    public void showTheLoai(View view) {
        finish();
    }
    public int validation(){
        int check =1;
        if(edMaTheLoai.getText().toString().isEmpty() || edTenTheLoai.getText().toString().isEmpty() || edMoTa.getText().toString().isEmpty()|| edViTri.getText().toString().isEmpty()){
            check =-1;
        }
        return check;
    }
}
