package com.example.duanmau.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.duanmau.R;
import com.example.duanmau.dao.HoaDonChiTietDao;
import com.example.duanmau.model.HoaDonChiTiet;

import java.util.List;

public class CartAdapter extends BaseAdapter {
    public Activity context;
    List<HoaDonChiTiet> arrHoaDonChiTiet;
    public LayoutInflater inflater;
    HoaDonChiTietDao chiTietDao;

    public CartAdapter(Activity context, List<HoaDonChiTiet> arrHoaDonChiTiet) {
        this.context = context;
        this.arrHoaDonChiTiet = arrHoaDonChiTiet;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        chiTietDao = new HoaDonChiTietDao(context);
    }

    @Override
    public int getCount() {
        return arrHoaDonChiTiet.size();
    }

    @Override
    public Object getItem(int position) {
        return arrHoaDonChiTiet.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public static class ViewHolder{
        ImageView img;
        TextView txtThanhTien;
        TextView txtMaSach;
        TextView txtGiaBia;
        TextView txtSoLuong;
        ImageView imgDelete;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null){
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.item_cart,null);
            holder.img = convertView.findViewById(R.id.ivIcon);
            holder.txtMaSach = convertView.findViewById(R.id.tvMaSach);
            holder.txtSoLuong = convertView.findViewById(R.id.tvSoLuong);
            holder.txtGiaBia = convertView.findViewById(R.id.tvGiaBia);
            holder.txtThanhTien = convertView.findViewById(R.id.tvThanhTien);
            holder.imgDelete = convertView.findViewById(R.id.ivDelete);

            holder.imgDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    chiTietDao.delHoaDonChiTiet(String.valueOf(arrHoaDonChiTiet.get(position).getMaHDCT()));
                    arrHoaDonChiTiet.remove(position);
                    notifyDataSetChanged();
                }
            });
            convertView.setTag(holder);

        }else {
            holder = (ViewHolder) convertView.getTag();
        }

        HoaDonChiTiet hdct = (HoaDonChiTiet) arrHoaDonChiTiet.get(position);

        holder.txtMaSach.setText("Mã Sách: "+hdct.getSach().getMaSach());
        holder.txtSoLuong.setText("Số Lượng: "+hdct.getSoLuong());
        holder.txtGiaBia.setText("Giá: "+hdct.getSach().getGiaBia() + " VND");
        holder.txtThanhTien.setText("Thành Tiền: "+ hdct.getSoLuong()* hdct.getSach().getGiaBia() + " VND");
        return convertView;
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }
    public void changeDataset(List<HoaDonChiTiet> item){
        this.arrHoaDonChiTiet = item;
        notifyDataSetChanged();
    }
}
