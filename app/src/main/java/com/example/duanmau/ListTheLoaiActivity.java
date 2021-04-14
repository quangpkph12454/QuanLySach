package com.example.duanmau;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.duanmau.adapter.NguoiDungAdapter;
import com.example.duanmau.adapter.TheLoaiAdapter;
import com.example.duanmau.dao.NguoiDungDao;
import com.example.duanmau.dao.TheLoaiDao;
import com.example.duanmau.model.NguoiDung;
import com.example.duanmau.model.TheLoai;

import java.util.ArrayList;
import java.util.List;

public class ListTheLoaiActivity extends AppCompatActivity {
    public static List<TheLoai> lsTL = new ArrayList<>();
    ListView lvTheLoai;
    TheLoaiDao theLoaiDao;
    TheLoaiAdapter tlAdapter;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_the_loai);

        lvTheLoai = findViewById(R.id.lvTheLoai);
        registerForContextMenu(lvTheLoai);

        theLoaiDao = new TheLoaiDao(ListTheLoaiActivity.this);

        lsTL = theLoaiDao.getAllTheLoai();

        tlAdapter = new TheLoaiAdapter(this,lsTL);
        lvTheLoai.setAdapter(tlAdapter);

        lvTheLoai.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                intent = new Intent(ListTheLoaiActivity.this,TheLoaiActivity.class);
                Bundle b = new Bundle();
                b.putString("maTheLoai",lsTL.get(position).getMaTheLoai());
                b.putString("tenTheLoai", lsTL.get(position).getTenTheLoai());
                b.putString("moTa",lsTL.get(position).getMota());
                b.putString("viTri",lsTL.get(position).getVitri());
                intent.putExtras(b);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        lsTL.clear();//xoa cai cu
        lsTL = theLoaiDao.getAllTheLoai();//lay lai cai moi
        tlAdapter.changeDataset(lsTL);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_theloai,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.add:
                Intent intent = new Intent(ListTheLoaiActivity.this,TheLoaiActivity.class);
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_context,menu);
        menu.setHeaderTitle("Chọn thông tin");
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_ctx_edit:
                Intent intent = new Intent(ListTheLoaiActivity.this,TheLoaiActivity.class);
                startActivity(intent);
                return (true);
            case R.id.menu_ctx_del:
                Intent intent1 = new Intent(ListTheLoaiActivity.this,TheLoaiActivity.class);
                startActivity(intent1);
                return  (true);
        }
        return super.onContextItemSelected(item);
    }

}
