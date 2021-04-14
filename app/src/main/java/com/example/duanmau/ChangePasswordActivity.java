package com.example.duanmau;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.duanmau.dao.NguoiDungDao;
import com.example.duanmau.model.NguoiDung;

public class ChangePasswordActivity extends AppCompatActivity {
    EditText edPass,edRePass;
    NguoiDungDao nguoiDungDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("ĐỔI MẬT KHẨU");
        setContentView(R.layout.activity_change_password);
        edPass = findViewById(R.id.edPassword);
        edRePass = findViewById(R.id.edRePassword);
    }

    public int validateForm(){
        int check =1;
        if (edPass.length() == 0 || edRePass.length()== 0){
            Toast.makeText(getApplicationContext(),"Bạn phải điền đầy đủ",Toast.LENGTH_SHORT).show();
            check=-1;
        }else {
            String pass = edPass.getText().toString();
            String rePass = edRePass.getText().toString();
            if (!pass.equalsIgnoreCase(rePass)){
                Toast.makeText(getApplicationContext(),"Mật khẩu không khớp",Toast.LENGTH_SHORT).show();
                check=-1;
            }
        }
        return check;
    }
    public void changePassword(View view) {
        SharedPreferences pref = getSharedPreferences("filename.share",MODE_PRIVATE);
        String strUserName = pref.getString("username","");
        nguoiDungDao = new NguoiDungDao(ChangePasswordActivity.this);
        NguoiDung user = new NguoiDung(strUserName,edPass.getText().toString(),"","");
        try {
            if (validateForm()>0){
                if (nguoiDungDao.changePasswordNguoiDung(user)>0){
                    Toast.makeText(getApplicationContext(),"Lưu thành công",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getApplicationContext(),"Lưu thất bại",Toast.LENGTH_SHORT).show();
                }
            }
            finish();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
