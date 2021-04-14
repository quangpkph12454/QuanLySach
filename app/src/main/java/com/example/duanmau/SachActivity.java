package com.example.duanmau;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.duanmau.dao.SachDao;
import com.example.duanmau.dao.TheLoaiDao;
import com.example.duanmau.model.Sach;
import com.example.duanmau.model.TheLoai;

import java.util.ArrayList;
import java.util.List;

public class SachActivity extends AppCompatActivity {
    SachDao sachDao;
    TheLoaiDao theLoaiDao;
    Spinner spnTheLoai;
    EditText edMaSach, edTenSach, edNXB, edTacGia,edGiaBia, edSoLuong;
    String maTheLoai = "";
    List<TheLoai> listTheLoai = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sach);
        setTitle("THÊM SÁCH");
        spnTheLoai = (Spinner) findViewById(R.id.spnTheLoai);
        getTheLoai();
        edMaSach = findViewById(R.id.edMaSach);
        edTenSach = findViewById(R.id.edTenSach);
        edNXB = findViewById(R.id.edNXB);
        edTacGia = findViewById(R.id.edTacGia);
        edGiaBia = findViewById(R.id.edGiaBia);
        edSoLuong = findViewById(R.id.edSoLuong);

        spnTheLoai.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                maTheLoai = listTheLoai.get(spnTheLoai.getSelectedItemPosition()).getMaTheLoai();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Intent i = getIntent();
        Bundle b = i.getExtras();
        if (b!= null){
            edMaSach.setText(b.getString("maSach"));
            String maTheLoai = b.getString("maTheLoai");
            edTenSach.setText(b.getString("tenSach"));
            edNXB.setText(b.getString("NXB"));
            edTacGia.setText(b.getString("tacGia"));
            edGiaBia.setText(b.getString("giaBia"));
            edSoLuong.setText(b.getString("soLuong"));
            spnTheLoai.setSelection(checkPositionTheLoai(maTheLoai));

        }
    }
    public void showSpinner(View view){
        sachDao = new SachDao(SachActivity.this);
        sachDao.getAllSach();
    }

    private int checkPositionTheLoai(String maTheLoai) {
        for (int i=0 ; i < listTheLoai.size();i++){
            if (maTheLoai.equalsIgnoreCase(listTheLoai.get(i).getMaTheLoai())){
                return i;
            }
        }
        return 0;
    }

    private void getTheLoai() {
        theLoaiDao = new TheLoaiDao(SachActivity.this);
        listTheLoai = theLoaiDao.getAllTheLoai();
        ArrayAdapter<TheLoai> adapter = new ArrayAdapter<TheLoai>(this, android.R.layout.simple_spinner_item,listTheLoai);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnTheLoai.setAdapter(adapter);
    }

    public void addBook(View view) {
        sachDao = new SachDao(SachActivity.this);
        Sach sach = new Sach(edMaSach.getText().toString(),maTheLoai,edTenSach.getText().toString(),edTacGia.getText().toString(),edNXB.getText().toString(),
                Double.parseDouble(edGiaBia.getText().toString()),Integer.parseInt(edSoLuong.getText().toString()));
        try {
            if (sachDao.insertSach(sach)> 0){
                Toast.makeText(getApplicationContext(),"Thêm thành công",Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(getApplicationContext(),"Thêm thất bại",Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showBook(View view) {
        Intent i = new Intent(SachActivity.this,ListBookActivity.class);
        startActivity(i);
    }

}
