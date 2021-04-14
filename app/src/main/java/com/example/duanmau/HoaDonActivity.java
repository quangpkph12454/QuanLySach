package com.example.duanmau;


import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.duanmau.dao.HoaDonDao;
import com.example.duanmau.model.HoaDon;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class HoaDonActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    EditText edNgayMua,edMaHoaDon;
    HoaDonDao hoaDonDao;
    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("THÊM HÓA ĐƠN");
        setContentView(R.layout.activity_hoa_don);
        edMaHoaDon = findViewById(R.id.edMaHoaDon);
        edNgayMua = findViewById(R.id.edNgayMua);
    }
    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar calendar = new GregorianCalendar(year,month,dayOfMonth);
        setDate(calendar);
    }
    private void setDate(final Calendar calendar){
        edNgayMua.setText(sdf.format(calendar.getTime()));
    }
    public static class DatePickerFragment extends DialogFragment{
        @Override
        public Dialog onCreateDialog( Bundle savedInstanceState) {
            final Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);
            return new DatePickerDialog(getActivity(),(DatePickerDialog.OnDateSetListener) getActivity(),year,month,day);
        }
    }
    public void datePicker(View view) {
        DatePickerFragment fragment = new DatePickerFragment();
        fragment.show(getFragmentManager(),"date");
    }

    public void ADDHoaDon(View view) {
        hoaDonDao = new HoaDonDao(HoaDonActivity.this);
        try {
            if (validation()<0){
                Toast.makeText(getApplicationContext(),"Vui lòng nhập đày đủ thông tin",Toast.LENGTH_SHORT).show();
            }else {
        HoaDon hoaDon = new HoaDon(edMaHoaDon.getText().toString(),sdf.parse(edNgayMua.getText().toString()));
        if (hoaDonDao.insertHoaDon(hoaDon) >0){
                    Toast.makeText(getApplicationContext(),"Thêm thành công",Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(HoaDonActivity.this,HoaDonChiTietActivity.class);
                    Bundle b = new Bundle();
                    b.putString("maHoaDon",edMaHoaDon.getText().toString());
                    i.putExtras(b);
                    startActivity(i);
                }else {
                    Toast.makeText(getApplicationContext(),"Thêm thất bại",Toast.LENGTH_SHORT).show();
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public int validation(){
        if (edMaHoaDon.getText().toString().isEmpty()|| edNgayMua.getText().toString().isEmpty()){
            return -1;
        }
        return 1;
    }
}
