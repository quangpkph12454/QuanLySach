package com.example.duanmau.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.duanmau.database.DatabaseHelper;
import com.example.duanmau.model.TheLoai;

import java.util.ArrayList;
import java.util.List;

public class TheLoaiDao {
    private SQLiteOpenHelper dbHelper;
    private SQLiteDatabase db;
    public static final String TABLE_NAME = "TheLoai";
    public static final String SQL_THE_LOAI = "Create table TheLoai(" +
            "maTheLoai text primary key," +
            "tenTheLoai text," +
            "vitri text," +
            "mota text" +
            ");";

    public TheLoaiDao(Context context) {
        dbHelper = new DatabaseHelper(context);
        db = dbHelper.getWritableDatabase();
    }
    public  int insertTheLoai(TheLoai tl){
        ContentValues contentValues = new ContentValues();
        contentValues.put("maTheLoai",tl.getMaTheLoai());
        contentValues.put("tenTheLoai",tl.getTenTheLoai());
        contentValues.put("vitri",tl.getVitri());
        contentValues.put("mota",tl.getMota());
                try {
                    if (db.insert(TABLE_NAME,null,contentValues)==-1){
                        return -1;
                    }else {
                        return 1;
                    }
                }catch (Exception e){
                    Log.e("THELOAI_TAG",e.getMessage());
                }

        return 1;
    }
    public List<TheLoai> getAllTheLoai(){
        List<TheLoai> lsTheLoai = new ArrayList<>();
        Cursor cursor = db.query(TABLE_NAME,null,null,null,null,null,null);
        cursor.moveToFirst();
        while (cursor.isAfterLast() == false){
            TheLoai tl = new TheLoai();
            tl.setMaTheLoai(cursor.getString(0));
            tl.setTenTheLoai(cursor.getString(1));
            tl.setVitri(cursor.getString(2));
            tl.setMota(cursor.getString(3));
            lsTheLoai.add(tl);
            cursor.moveToNext();
        }
        cursor.close();
        return lsTheLoai;
    }
    public int updateTheLoai(TheLoai tl){
        ContentValues contentValues = new ContentValues();
        contentValues.put("maTheLoai",tl.getMaTheLoai());
        contentValues.put("tenTheLoai",tl.getTenTheLoai());
        contentValues.put("vitri",tl.getVitri());
        contentValues.put("mota",tl.getMota());
        int result = db.update(TABLE_NAME,contentValues,"maTheLoai=?",new String[]{tl.getMaTheLoai()});
        if (result == 0){
            return -1;
        }
        return 1;
    }
    public int deleteTheLoai(String maTheLoai) {
        int kq = db.delete(TABLE_NAME,"maTheLoai=?",new String[]{maTheLoai});
        if(kq==0)
        {
            return -1;//xoa khong thanh cong
        }
        return 1;//xoa thanh cong
    }

    public boolean checkPrimaryKey(String strPrimaryKey){
        String [] columns = {"maTheLoai"};
        String selection = "maTheLoai =?";
        String[] selectionArgs ={strPrimaryKey};
        Cursor c;
        try {
            c = db.query(TABLE_NAME,columns,selection,selectionArgs,null,null,null);
            c.moveToFirst();
            int i = c.getCount();
            c.close();
            if (i<=0){
                return false;
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
