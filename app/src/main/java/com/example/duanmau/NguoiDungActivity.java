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
import com.example.duanmau.model.NguoiDung;

public class NguoiDungActivity extends AppCompatActivity {
    NguoiDungDao nguoiDungDao;
    EditText edUser,edPass,edRepass,edPhone,edFullname;
    Button btnCancelUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nguoi_dung);
        setTitle("Nguoi Dung");
        edUser = findViewById(R.id.edUserName);
        edPass = findViewById(R.id.edPassword);
        edRepass = findViewById(R.id.edRePassword);
        edPhone = findViewById(R.id.edPhone);
        edFullname = findViewById(R.id.edFullName);
        btnCancelUser = findViewById(R.id.btnCancelUser);
        btnCancelUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NguoiDungActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }

    public void showUsers(View view) {
        finish();

    }

    public void addUser(View view) {
        nguoiDungDao = new NguoiDungDao(NguoiDungActivity.this);
        NguoiDung n = new NguoiDung(edUser.getText().toString(),
                edPass.getText().toString(),
                edPhone.getText().toString(),
                edFullname.getText().toString());
        try {
            if (validateForm()>0){
                if (nguoiDungDao.insertNguoiDung(n)>0){
                    Toast.makeText(getApplicationContext(),"Insert successful",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getApplicationContext(),"Insert not successful",Toast.LENGTH_SHORT).show();
                }
            }
        }catch (Exception e){
            Log.e("NguoiDungActivity_TAG",e.getMessage());
        }
    }
    public int validateForm(){
        int check = 1;
        if (edUser.length() == 0||edPass.length() == 0
                || edRepass.length()== 0 || edFullname.length() == 0
        ||edPhone.length() ==0){
            Toast.makeText(getApplicationContext(),"Bạn phải điền đầy đủ",Toast.LENGTH_SHORT).show();
            check=-1;
        }else {
            String pass = edPass.getText().toString();
            String rePass = edRepass.getText().toString();
            if (!pass.equalsIgnoreCase(rePass)){
                Toast.makeText(getApplicationContext(),"Mật khẩu không khớp",Toast.LENGTH_SHORT).show();
                check=-1;
            }
        }
        return check;
    }
}
