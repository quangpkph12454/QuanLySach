package com.example.duanmau;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.duanmau.dao.NguoiDungDao;
import com.example.duanmau.model.NguoiDung;

public class NguoiDungDetailActivity extends AppCompatActivity {
    EditText edPhone, edFullname;
    NguoiDungDao nguoiDungDao;
    Button btnUpdate, btnCancel;
    String phone, hoten, username, password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Chi tiet nguoi dung");
        setContentView(R.layout.activity_nguoi_dung_detail);
        edPhone = findViewById(R.id.edPhone);
        edFullname = findViewById(R.id.edFullName);
        btnUpdate = findViewById(R.id.btnUpdateUser);
        btnCancel = findViewById(R.id.btnCancel);
        nguoiDungDao = new NguoiDungDao(NguoiDungDetailActivity.this);
        Intent intent = getIntent();
        Bundle b = intent.getExtras();
        phone = b.getString("phone");
        hoten = b.getString("hoten");
        username = b.getString("username");
        password = b.getString("password");
        edFullname.setText(hoten);
        edPhone.setText(phone);
    }
    public void updateUser(View view) {
        NguoiDung n = new NguoiDung();
        n.setUsername(username);
        n.setPhone(edPhone.getText().toString());
        n.setHoten(edFullname.getText().toString());
        n.setPassword(password);
        int kq = nguoiDungDao.updateUser(n);
        if(kq<=0)
        {
            Toast.makeText(getApplicationContext(),"Update that bai",Toast.LENGTH_LONG).show();
        }
        else
        {
            Toast.makeText(getApplicationContext(),"Update thanh cong",Toast.LENGTH_LONG).show();
        }

    }

    public void Huy(View view) {
        finish();
    }
}
