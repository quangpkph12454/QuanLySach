package com.example.duanmau.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.duanmau.database.DatabaseHelper;
import com.example.duanmau.model.HoaDon;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class HoaDonDao {
    private SQLiteDatabase db;
    private DatabaseHelper dbHelper;

    public static final String TABLE_NAME ="HoaDon";
    public static final String SQL_HOA_DON = "create table HoaDon(maHoaDon text primary key ," +
            "ngayMua date);";

    public static final String TAG = "HoaDonDao";
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    public HoaDonDao(Context context){
        dbHelper = new DatabaseHelper(context);
        db = dbHelper.getWritableDatabase();
    }
    public int insertHoaDon(HoaDon hd){
        ContentValues values = new ContentValues();
        values.put("maHoaDon",hd.getMaHoaDon());
        values.put("ngayMua",sdf.format(hd.getNgayMua()));
        try{
            if (db.insert(TABLE_NAME,null,values)==-1){
                return -1;
            }else {
                return 1;
            }
        }catch (Exception ex){
            Log.e(TAG,ex.toString());
        }
        return 1;
    }
    public List<HoaDon> getAllHoaDon(){
        List<HoaDon> dsHoaDon = new ArrayList<>();
        Cursor cursor = db.query(TABLE_NAME,null,null,null,null,null,null);
        cursor.moveToFirst();
        try {
            while (cursor.isAfterLast()==false){
                HoaDon ee = new HoaDon();
                ee.setMaHoaDon(cursor.getString(0));
                ee.setNgayMua(sdf.parse(cursor.getString(1)));

                dsHoaDon.add(ee);
                Log.d("//=====", ee.toString());
                cursor.moveToNext();
            }
            cursor.close();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dsHoaDon;
    }
    public int updateHoaDon(HoaDon hd){
        ContentValues values = new ContentValues();
        values.put("maHoaDon",hd.getMaHoaDon());
        values.put("ngayMua",hd.getNgayMua().toString());
        int result = db.update(TABLE_NAME,values,"maHoaDon=?",new String[]{String.valueOf(hd.getMaHoaDon())});
        if (result == 0){
            return -1;
        }
        return 1;
    }
    public int delHoaDonByID(String maHoaDon){
        int result = db.delete(TABLE_NAME,"maHoaDon=?",new String[]{maHoaDon});
        if (result ==0){
            return -1;
        }
        return 1;
    }

}
