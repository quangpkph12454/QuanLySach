package com.example.duanmau.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.duanmau.dao.HoaDonChiTietDao;
import com.example.duanmau.dao.HoaDonDao;
import com.example.duanmau.dao.NguoiDungDao;
import com.example.duanmau.dao.SachDao;
import com.example.duanmau.dao.TheLoaiDao;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static  final  String DATABASE_NAME = "dbBM";
    public static  final  int VERSION= 1;
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    //create table
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(NguoiDungDao.SQL_NGUOI_DUNG);//create the NguoiDung table
        db.execSQL(TheLoaiDao.SQL_THE_LOAI);//create the TheLoai table
        db.execSQL(SachDao.SQL_SACH);
        db.execSQL(HoaDonDao.SQL_HOA_DON);
        db.execSQL(HoaDonChiTietDao.SQL_HOA_DON_CHI_TIET);
    }

    //upgrade table
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + NguoiDungDao.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TheLoaiDao.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + SachDao.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + HoaDonDao.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + HoaDonChiTietDao.TABLE_NAME);

        onCreate(db);
    }
}
