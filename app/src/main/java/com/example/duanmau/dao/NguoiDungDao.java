package com.example.duanmau.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.duanmau.database.DatabaseHelper;
import com.example.duanmau.model.NguoiDung;

import java.util.ArrayList;
import java.util.List;

public class NguoiDungDao {
    private SQLiteDatabase db;
    private SQLiteOpenHelper dbHelper;
    public static final  String TABLE_NAME = "NguoiDung";
    public  static final String SQL_NGUOI_DUNG = "Create table NguoiDung(" +
            "username text primary key," +
            "password text," +
            "phone text," +
            "hoten text" +
            ");";

    public NguoiDungDao(Context context) {
        dbHelper = new DatabaseHelper(context);
        db = dbHelper.getWritableDatabase();
    }
    public  int insertNguoiDung(NguoiDung n){
        ContentValues values = new ContentValues();
        values.put("username",n.getUsername());
        values.put("password",n.getPassword());
        values.put("phone",n.getPhone());
        values.put("hoten",n.getHoten());
        try {
            if (db.insert(TABLE_NAME,null,values)<0){
                return -1;//insert not successful
            }else {
                return 1; //insert successful
            }

        }catch (Exception e){
            Log.e("NguoiDung_TAG",e.getMessage());
        }
        return 1; //insert successful
    }

    public List<NguoiDung> getAllNguoiDung(){
        List<NguoiDung> lsND = new ArrayList<>();
        Cursor cursor = db.query(TABLE_NAME,null,null,null,null,null,null);
        cursor.moveToFirst();
        while (cursor.isAfterLast()== false){
            NguoiDung nd = new NguoiDung();
            nd.setUsername(cursor.getString(0));
            nd.setPassword(cursor.getString(1));
            nd.setPhone(cursor.getString(2));
            nd.setHoten(cursor.getString(3));
            lsND.add(nd);
            cursor.moveToNext();
        }
        cursor.close();
        return lsND;
    }
    public int updateUser(NguoiDung n)
    {
        ContentValues values = new ContentValues();
        values.put("username",n.getUsername());
        values.put("password",n.getPassword());
        values.put("phone",n.getPhone());
        values.put("hoten",n.getHoten());
        int kq = db.update(TABLE_NAME,values,"username=?",new String[]{n.getUsername()});
        if(kq==0)
        {
            return -1;
        }
        return 1;
    }
    public int deleteNguoiDung(String username)
    {
        int kq = db.delete(TABLE_NAME,"username=?",new String[]{username});
        if(kq==0)
        {
            return -1;//xoa khong thanh cong
        }
        return 1;//xoa thanh cong
    }
    public int changePasswordNguoiDung(NguoiDung nd){
        ContentValues values = new ContentValues();
        values.put("username",nd.getUsername());
        values.put("password",nd.getPassword());
        int result = db.update(TABLE_NAME,values,"username=?",new String[]{nd.getUsername()});
        if (result ==0){
            return -1;
        }
        return 1;
    }
    public  int checkLogin(String username, String password){
        int result = db.delete(TABLE_NAME,"username=? AND password=?",new String[]{username,password});
        if (result==0){
            return -1;
        }
        return 1;
    }

}
