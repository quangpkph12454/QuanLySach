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

import com.example.duanmau.adapter.HoaDonAdapter;
import com.example.duanmau.dao.HoaDonDao;
import com.example.duanmau.model.HoaDon;

import java.util.ArrayList;
import java.util.List;

public class ListHoaDonActivity extends AppCompatActivity {
    public List<HoaDon> dsHoaDon = new ArrayList<>();
    ListView lvHoaDon;
    HoaDonAdapter adapter = null;
    HoaDonDao hoaDonDao;
    EditText edSearch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_hoa_don);
        setTitle("HÓA ĐƠN");
        edSearch = findViewById(R.id.edSearch);
        lvHoaDon = (ListView) findViewById(R.id.lvHoaDon);
        hoaDonDao = new HoaDonDao(ListHoaDonActivity.this);
        try {
            dsHoaDon = hoaDonDao.getAllHoaDon();

        } catch (Exception e) {
            e.printStackTrace();
        }
        adapter = new HoaDonAdapter(dsHoaDon,this);
        lvHoaDon.setAdapter(adapter);
        lvHoaDon.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                HoaDon hoaDon = (HoaDon) parent.getItemAtPosition(position);
                Intent intent = new Intent(ListHoaDonActivity.this,ListHoaDonChiTietByIDActivity.class);
                Bundle b = new Bundle();
                b.putString("maHoaDon",hoaDon.getMaHoaDon());
                intent.putExtras(b);
                startActivity(intent);
            }
        });
        lvHoaDon.setTextFilterEnabled(true);
        edSearch.addTextChangedListener(new TextWatcher() {
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
        getMenuInflater().inflate(R.menu.menu_theloai,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case  R.id.add:
                Intent intent = new Intent(ListHoaDonActivity.this,HoaDonActivity.class);
                startActivity(intent);
                return (true);
        }
        return super.onOptionsItemSelected(item);
    }
}
