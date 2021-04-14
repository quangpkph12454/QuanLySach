package com.example.duanmau;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.duanmau.adapter.NguoiDungAdapter;
import com.example.duanmau.dao.NguoiDungDao;
import com.example.duanmau.model.NguoiDung;

import java.util.ArrayList;
import java.util.List;

public class ListNguoiDungActivity extends AppCompatActivity {
    public static List<NguoiDung> lsND ;
    ListView lvNguoiDung;
    NguoiDungDao nguoiDungDao;
    NguoiDungAdapter ndAdapter;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_nguoi_dung);
        lvNguoiDung = findViewById(R.id.lvNguoiDung);

        nguoiDungDao = new NguoiDungDao(ListNguoiDungActivity.this);

        lsND = nguoiDungDao.getAllNguoiDung();

        ndAdapter = new NguoiDungAdapter(this,lsND);
        lvNguoiDung.setAdapter(ndAdapter);
        lvNguoiDung.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                intent = new Intent(ListNguoiDungActivity.this,NguoiDungDetailActivity.class);
                Bundle b = new Bundle();
                b.putString("username",lsND.get(position).getUsername());
                b.putString("password",lsND.get(position).getPassword());
                b.putString("phone",lsND.get(position).getPhone());
                b.putString("hoten",lsND.get(position).getHoten());
                intent.putExtras(b);
                startActivity(intent);
            }
        });
        lvNguoiDung.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ListNguoiDungActivity.this,ChangePasswordActivity.class);
                startActivity(intent);
                return false;
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        lsND.clear();//xoa cai cu
        lsND = nguoiDungDao.getAllNguoiDung();//lay lai cai moi
        ndAdapter.changeDataset(lsND);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_user,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent i;
        switch (item.getItemId()){
            case R.id.add:
                i = new Intent(ListNguoiDungActivity.this,NguoiDungActivity.class);
                startActivity(i);
                return true;
            case R.id.changePass:
                i = new Intent(ListNguoiDungActivity.this,ChangePasswordActivity.class);
                startActivity(i);
                return true;
            case R.id.logOut:
                SharedPreferences pref = getSharedPreferences("filename.share",MODE_PRIVATE);
                SharedPreferences.Editor edit = pref.edit();
                edit.clear();
                edit.commit();
                i = new Intent(ListNguoiDungActivity.this,MainActivity.class);
                startActivity(i);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
