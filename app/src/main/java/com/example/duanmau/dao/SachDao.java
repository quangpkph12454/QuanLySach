package com.example.duanmau.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.duanmau.database.DatabaseHelper;
import com.example.duanmau.model.Sach;

import java.util.ArrayList;
import java.util.List;

public class SachDao {
    private SQLiteDatabase db;
    private SQLiteOpenHelper dbHelper;
    public static final  String TABLE_NAME = "Sach";
    public  static final String SQL_SACH = "Create table Sach(" +
            "maSach text primary key," +
            "maTheLoai text," +
            "tenSach text," +
            "tacGia text," +
            "NXB text," +
            "giaBia double," +
            "soLuong number" +
            ");";

    public SachDao(Context context) {
        dbHelper = new DatabaseHelper(context);
        db = dbHelper.getWritableDatabase();
    }
    public int insertSach(Sach sach){
        ContentValues values = new ContentValues();
        values.put("maSach",sach.getMaSach());
        values.put("maTheLoai",sach.getMaTheLoai());
        values.put("tenSach",sach.getTenSach());
        values.put("tacGia",sach.getTacGia());
        values.put("NXB",sach.getNXB());
        values.put("giaBia",sach.getGiaBia());
        values.put("soLuong",sach.getSoLuong());
                try {
                    if (db.insert(TABLE_NAME,null,values)==-1){
                        return -1;
                    }
                }catch (Exception e){
                    e.printStackTrace();
        }return 1;
    }

    public List<Sach> getAllSach(){
        List<Sach> dsSach = new ArrayList<>();
        Cursor c = db.query(TABLE_NAME,null,null,null,null,null,null);
        c.moveToFirst();
        while (c.isAfterLast() == false){
            Sach s = new Sach();
            s.setMaSach(c.getString(0));
            s.setMaTheLoai(c.getString(1));
            s.setTenSach(c.getString(2));
            s.setTacGia(c.getString(3));
            s.setNXB(c.getString(4));
            s.setGiaBia(c.getDouble(5));
            s.setSoLuong(c.getInt(6));
            dsSach.add(s);

            Log.d("//=======",s.toString());
            c.moveToNext();
        }
        c.close();
        return dsSach;
    }
    public int updateSach(Sach s){
        ContentValues values = new ContentValues();
        values.put("maSach",s.getMaSach());
        values.put("maTheLoai",s.getMaTheLoai());
        values.put("tenSach",s.getTenSach());
        values.put("tacGia",s.getTacGia());
        values.put("NXB",s.getNXB());
        values.put("giaBia",s.getGiaBia());
        values.put("soLuong",s.getSoLuong());
        int result = db.update(TABLE_NAME,values,"maSach=?",new String[]{s.getMaSach()});
        if (result==0){
            return -1;
        }
        return 1;
    }
    public int delSach(String maSach){
        int result = db.delete(TABLE_NAME,"maSach=?",new String[]{maSach});
        if (result ==0){
            return -1;
        }
        return 1;
    }
    public boolean checkPrimaryKey(String strPrimaryKey){
        String[] columns = {"maSach"};
        String selection = "maSach=?";
        String[] selectionArgs ={strPrimaryKey};
        Cursor c = null;
        try {
            c = db.query(TABLE_NAME,columns,selection,selectionArgs,null,null,null);
            c.moveToFirst();
            int i = c.getCount();
            c.close();
            if (i<=0){
                return false;
            }
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
    public Sach checkBook(String strPrimaryKey){
        Sach s = null;
        String[] columns = {"maSach"};
        String selection = "maSach=?";
        String[] selectionArgs = {strPrimaryKey};
        Cursor c = null;
        try {
            c = db.query(TABLE_NAME,columns,selection,selectionArgs,null,null,null);
            c.moveToFirst();
            while (c.isAfterLast()==false){
                s = new Sach();
                s.setMaSach(c.getString(0));
                s.setMaTheLoai(c.getString(1));
                s.setTenSach(c.getString(2));
                s.setTacGia(c.getString(3));
                s.setNXB(c.getString(4));
                s.setGiaBia(c.getDouble(5));
                s.setSoLuong(c.getInt(6));
                Log.d("//======",s.toString());
                break;
            }
            c.close();
            return s;
        }catch (Exception e){
            e.printStackTrace();
            return  null;
        }
    }
    public Sach getSachByID(String maSach){
        Sach s = null;
        String selection = "maSach=?";
        String[] selectionArgs = {maSach};
        Cursor c = db.query(TABLE_NAME,null,selection,selectionArgs,null,null,null);
        Log.d("getSachByID", "=====>" +c.getCount());
        c.moveToFirst();
        while (c.isAfterLast()==false){
            s = new Sach();
            s.setMaSach(c.getString(0));
            s.setMaTheLoai(c.getString(1));
            s.setTenSach(c.getString(2));
            s.setTacGia(c.getString(3));
            s.setNXB(c.getString(4));
            s.setGiaBia(c.getDouble(5));
            s.setSoLuong(c.getInt(6));
            break;
        }
        c.close();
        return s;
    }
    public List<Sach> getAllTop10(int month){
        List<Sach> dsSach = new ArrayList<>();
        if ((month)<13){
            month = 0 + month;
        }
        String sSQL = "select maSach , sum(soLuong) as soLuong from HoaDonChiTiet inner join HoaDon" +
                " on HoaDon.maHoaDon = HoaDonChiTiet.maHoaDon where strftime('%m',HoaDon.ngayMua) = '"+month+"' group by maSach order by soLuong desc limit 10";

        Cursor c = db.rawQuery(sSQL,null);
        c.moveToFirst();
        while (c.isAfterLast() == false){
            Sach s = new Sach();
            s.setMaSach(c.getString(0));
            s.setSoLuong(c.getInt(1));
            dsSach.add(s);

            Log.d("//=======",s.toString());
            c.moveToNext();
        }
        c.close();
        return dsSach;
    }
}
