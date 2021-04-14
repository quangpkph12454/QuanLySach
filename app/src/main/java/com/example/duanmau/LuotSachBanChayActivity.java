package com.example.duanmau;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.duanmau.adapter.SachAdapter;
import com.example.duanmau.dao.SachDao;
import com.example.duanmau.model.Sach;

import java.util.ArrayList;
import java.util.List;

public class LuotSachBanChayActivity extends AppCompatActivity {
    public static List<Sach> dsSach = new ArrayList<>();
    ListView lvBook;
    SachAdapter adapter = null;
    SachDao sachDao;
    EditText edThang;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_luot_sach_ban_chay);
        setTitle("TOP 10 SÁCH BÁN CHẠY");
        lvBook = findViewById(R.id.lvBookTop);
        edThang = findViewById(R.id.edThang);
    }

    public void VIEW_SACH_TOP_10(View view) {
        if (Integer.parseInt(edThang.getText().toString())>13 || Integer.parseInt(edThang.getText().toString())<0){
            Toast.makeText(getApplicationContext(),"Không đúng định dạng tháng (1-12",Toast.LENGTH_SHORT).show();
        }else {
            sachDao = new SachDao(LuotSachBanChayActivity.this);
            dsSach = sachDao.getAllTop10(Integer.parseInt(edThang.getText().toString()));

            adapter = new SachAdapter(dsSach,this);
            lvBook.setAdapter(adapter);
        }
    }
}
