package com.example.duanmau;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    EditText edUserName;
    EditText edPassword;
    CheckBox chkRememberPass ;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edUserName = findViewById(R.id.edUserName);
        edPassword = findViewById(R.id.edPassword);
        chkRememberPass = findViewById(R.id.chkRememberPass);
        btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edUserName.getText().toString().equals("admin") && edPassword.getText().toString().equals("admin")){
                    String user = edUserName.getText().toString();
                    save();
                    Intent i = new Intent(LoginActivity.this,MainActivity.class);
                    i.putExtra("User",user);
                    startActivity(i);
                    finish();
                }
                else {
                    Toast.makeText(LoginActivity.this,"Bạn đăng nhập thất bại!",Toast.LENGTH_SHORT).show();
                }
            }
        });
        this.load();
    }

    public void HuyLogin(View view) {
        finish();
    }
    private void save(){
        SharedPreferences sharedPreferences = getSharedPreferences("filename.share",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String user = edUserName.getText().toString();
        String pass = edPassword.getText().toString();
        boolean remember = chkRememberPass.isChecked();
        if(!remember){
            editor.clear();
        }else{
            editor.putString("User",user);
            editor.putString("Pass",pass);
            editor.putBoolean("Remember",remember);
        }editor.commit();
    }
    private void load(){
        SharedPreferences sharedPreferences = getSharedPreferences("filename.share",MODE_PRIVATE);
        boolean remember = sharedPreferences.getBoolean("Remember",false);
        if(remember){
            String User = sharedPreferences.getString("User",null);
            String Pass = sharedPreferences.getString("Pass",null);
            edUserName.setText(User);
            edPassword.setText(Pass);
        }
        chkRememberPass.setChecked(remember);
    }
}
