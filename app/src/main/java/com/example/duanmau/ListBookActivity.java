package com.example.duanmau;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.example.duanmau.adapter.SachAdapter;
import com.example.duanmau.dao.SachDao;
import com.example.duanmau.model.Sach;

import java.util.ArrayList;
import java.util.List;

public class ListBookActivity extends AppCompatActivity {
    public static List<Sach> dsSach = new ArrayList<>();
    ListView lvBook;
    SachAdapter adapter = null;
    SachDao sachDao;
    EditText edSeach;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_book);
        setTitle("QUẢN LÝ SÁCH");
        lvBook = findViewById(R.id.lvBook);

        sachDao = new SachDao(ListBookActivity.this);
        dsSach = sachDao.getAllSach();

        adapter = new SachAdapter(dsSach,this);
        lvBook.setAdapter(adapter);
        lvBook.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Sach sach = (Sach) parent.getItemAtPosition(position);
                Intent intent = new Intent(ListBookActivity.this,SachActivity.class);
                Bundle b = new Bundle();
                b.putString("maSach",sach.getMaSach());
                b.putString("maTheLoai",sach.getMaTheLoai());
                b.putString("tenSach",sach.getTenSach());
                b.putString("tacGia",sach.getTacGia());
                b.putString("NXB",sach.getNXB());
                b.putString("giaBia",String.valueOf(sach.getGiaBia()));
                b.putString("soLuong",String.valueOf(sach.getSoLuong()));
                intent.putExtras(b);
                startActivity(intent);
            }
        });
        lvBook.setTextFilterEnabled(true);
        edSeach = (EditText) findViewById(R.id.edSearchBook);
        edSeach.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                System.out.println("Text [" + s+ "] - Start [" +start+ "] - Before [" +before + "] - Count [" +count + "]");
                if (count<before){
                    adapter.resetData();
                }
                adapter.getFilter().filter(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_book,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case  R.id.add:
                Intent intent = new Intent(ListBookActivity.this,SachActivity.class);
                startActivity(intent);
                return (true);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        dsSach.clear();//xoa cai cu
        dsSach = sachDao.getAllSach();//lay lai cai moi
        adapter.changeDataset(dsSach);
    }
}
