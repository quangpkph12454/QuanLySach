package com.example.duanmau.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.duanmau.database.DatabaseHelper;
import com.example.duanmau.model.HoaDon;
import com.example.duanmau.model.HoaDonChiTiet;
import com.example.duanmau.model.Sach;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class HoaDonChiTietDao {
    private SQLiteDatabase db;
    private DatabaseHelper dbHelper;

    public static final String TABLE_NAME ="HoaDonChiTiet";
    public static final String SQL_HOA_DON_CHI_TIET = "create table HoaDonChiTiet(maHDCT integer primary key autoincrement," +
            "maHoaDon text not null, maSach text not null,soLuong integer);";

    public static final String TAG = "HoaDonChiTiet";
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    public HoaDonChiTietDao(Context context){
        dbHelper = new DatabaseHelper(context);
        db = dbHelper.getWritableDatabase();
    }
    public int insertHoaDonChiTiet(HoaDonChiTiet hdct){
        ContentValues values = new ContentValues();
        values.put("maHoaDon",hdct.getHoaDon().getMaHoaDon());
        values.put("maSach",hdct.getSach().getMaSach());
        values.put("soLuong",hdct.getSoLuong());
        try{
            if (db.insert(TABLE_NAME,null,values)==-1){
                return -1;
            }
        }catch (Exception ex){
            Log.e(TAG,ex.toString());
        }
        return 1;
    }
    public List<HoaDonChiTiet> getAllHoaDonChiTiet(){
        List<HoaDonChiTiet> dsHoaDOnChiTiet = new ArrayList<>();
        String sSQL = "SELECT maHDCT, HoaDon.maHoaDon,HoaDon.ngayMua," +
                "Sach.maSach,Sach.maTheLoai,Sach.tenSach,Sach.tacGia,Sach.NXB,Sach.giaBia," +
                "Sach.soLuong, HoaDonChiTiet.soLuong FROM HoaDonChiTiet INNER JOIN HoaDon" +
                " on HoaDonChiTiet.maHoaDon = HoaDon.maHoaDon INNER JOIN Sach on Sach.maSach = HoaDonChiTiet.maSach";
        Cursor c = db.rawQuery(sSQL,null);
        c.moveToFirst();
        try{
            while (c.isAfterLast()==false){
                HoaDonChiTiet hd = new HoaDonChiTiet();
                hd.setMaHDCT(c.getInt(0));
                hd.setHoaDon(new HoaDon(c.getString(1),sdf.parse(c.getString(2))));
                hd.setSach(new Sach(c.getString(3),c.getString(4),c.getString(5),c.getString(6),c.getString(7),c.getInt(8),c.getInt(9)));
                hd.setSoLuong(c.getInt(10));
                dsHoaDOnChiTiet.add(hd);
                Log.d("//=======",hd.toString());
                c.moveToNext();
            }
            c.close();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dsHoaDOnChiTiet;
    }
    public List<HoaDonChiTiet> getAllHoaDonChiTietbyID(String maHoaDon){
        List<HoaDonChiTiet> dsHoaDOnChiTiet = new ArrayList<>();
        String sSQL = "SELECT maHDCT, HoaDon.maHoaDon,HoaDon.ngayMua," +
                "Sach.maSach,Sach.maTheLoai,Sach.tenSach,Sach.tacGia,Sach.NXB,Sach.giaBia," +
                "Sach.soLuong, HoaDonChiTiet.soLuong FROM HoaDonChiTiet INNER JOIN HoaDon" +
                " on HoaDonChiTiet.mahoaDon = HoaDon.maHoaDon INNER JOIN Sach on Sach.maSach = HoaDonChiTiet.maSach " +
                " where HoaDonChiTiet.maHoaDon = '"+maHoaDon+"' ";
        Cursor c = db.rawQuery(sSQL,null);
        c.moveToFirst();
        try{
            while (c.isAfterLast()==false){
                HoaDonChiTiet hd = new HoaDonChiTiet();
                hd.setMaHDCT(c.getInt(0));
                hd.setHoaDon(new HoaDon(c.getString(1),sdf.parse(c.getString(2))));
                hd.setSach(new Sach(c.getString(3),c.getString(4),c.getString(5),c.getString(6),c.getString(7),c.getInt(8),c.getInt(9)));
                hd.setSoLuong(c.getInt(10));
                dsHoaDOnChiTiet.add(hd);
                Log.d("//=======",hd.toString());
                c.moveToNext();
            }
            c.close();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dsHoaDOnChiTiet;
    }

    public int updateHoaDonChiTiet(HoaDonChiTiet hdct){
        ContentValues values = new ContentValues();
        values.put("maHDCT",hdct.getMaHDCT());
        values.put("maHoaDon",hdct.getHoaDon().getMaHoaDon());
        values.put("maSach",hdct.getSach().getMaSach());
        values.put("soLuong",hdct.getSoLuong());
        int result = db.update(TABLE_NAME,values,"maHDCT=?",new String[]{String.valueOf(hdct.getMaHDCT())});
        if (result == 0){
            return -1;
        }
        return 1;
    }
    public int delHoaDonChiTiet (String maHDCT){
        int result = db.delete(TABLE_NAME,"maHDCT=?",new String[]{maHDCT});
        if (result ==0){
            return -1;
        }
        return 1;
    }
    public boolean checkHoaDon(String maHoaDon){
        String [] columns = {"maHoaDon"};
        String selection = "maHoaDon=?";
        String[] selectionAgrs = {maHoaDon};
        Cursor c = null;
        try {
            c = db.query(TABLE_NAME,columns,selection,selectionAgrs,null,null,null);
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
    public double getDoanhThuTheoNgay(){
        double doanhThu = 0;
        String sSQL = "SELECT SUM(tongtien) FROM (SELECT SUM(Sach.giaBia * HoaDonChiTiet.soLuong) as 'tongtien'" +
                " From HoaDon INNER JOIN HoaDonChiTiet on HoaDon.maHoaDon = HoaDonChiTiet.maHoaDon where HoaDon.ngayMua = date('now')" +
                " Group by HoaDonChiTiet.maSach) tmp";

        Cursor c = db.rawQuery(sSQL,null);
        c.moveToFirst();
        while (c.isAfterLast() == false){
            doanhThu = c.getDouble(0);
            c.moveToNext();
        }
        c.close();
        return doanhThu;
    }
    public double getDoanhThuThang(){
        double doanhThu = 0;
        String sSQL = "SELECT SUM(tongtien) FROM (SELECT SUM(Sach.giaBia * HoaDonChiTiet.soLuong) as 'tongtien'" +
                " From HoaDon INNER JOIN HoaDonChiTiet on HoaDon.maHoaDon = HoaDonChiTiet.maHoaDon" +
                " INNER JOIN Sach on HoaDonChiTiet.maSach = Sach.maSach" +
                " where strftime('%m',HoaDon.ngayMua) = strftime('%m','now')" +
                " Group by HoaDonChiTiet.maSach) tmp";

        Cursor c = db.rawQuery(sSQL,null);
        c.moveToFirst();
        while (c.isAfterLast() == false){
            doanhThu = c.getDouble(0);
            c.moveToNext();
        }
        c.close();
        return doanhThu;
    }
    public  double getDoanhThuNam(){
        double doanhThu = 0;
        String sSQL = "SELECT SUM(tongtien) FROM (SELECT SUM(Sach.giaBia * HoaDonChiTiet.soLuong) as 'tongtien'" +
                " From HoaDon INNER JOIN HoaDonChiTiet on HoaDon.maHoaDon = HoaDonChiTiet.maHoaDon" +
                " INNER JOIN Sach on HoaDonChiTiet.maSach = Sach.maSach" +
                " where strftime('%Y',HoaDon.ngayMua) = strftime('%Y','now')" +
                " Group by HoaDonChiTiet.maSach) tmp";

        Cursor c = db.rawQuery(sSQL,null);
        c.moveToFirst();
        while (c.isAfterLast() == false){
            doanhThu = c.getDouble(0);
            c.moveToNext();
        }
        c.close();
        return doanhThu;
    }
}
