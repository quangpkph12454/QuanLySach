package com.example.duanmau.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.duanmau.R;
import com.example.duanmau.dao.HoaDonChiTietDao;
import com.example.duanmau.dao.HoaDonDao;
import com.example.duanmau.model.HoaDon;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class HoaDonAdapter extends BaseAdapter implements Filterable {
    List<HoaDon> arrHoaDon;
    public Activity context;
    List<HoaDon> arrSortHoaDon;
    private Filter hoaDonFilter;
    public LayoutInflater inflater;
    HoaDonDao hoaDonDao;
    HoaDonChiTietDao hoaDonChiTietDao;
    SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");

    public HoaDonAdapter(List<HoaDon> arrHoaDon, Activity context) {
        super();
        this.arrHoaDon = arrHoaDon;
        this.context = context;
        this.arrSortHoaDon = arrHoaDon;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        hoaDonDao = new HoaDonDao(context);
        hoaDonChiTietDao = new HoaDonChiTietDao(context);
    }

    @Override
    public int getCount() {
        return arrHoaDon.size();
    }

    @Override
    public Object getItem(int position) {
        return arrHoaDon.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public static class ViewHolder{
        ImageView img;
        TextView txtMaHoaDon;
        TextView txtNgayMua;
        ImageView imgDelete;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView ==null){
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.item_hoadon,null);
            holder.img = convertView.findViewById(R.id.ivIcon);
            holder.txtMaHoaDon = convertView.findViewById(R.id.tvMaHoaDon);
            holder.txtNgayMua = convertView.findViewById(R.id.tvNgayMua);
            holder.imgDelete = convertView.findViewById(R.id.ivDelete);
            holder.imgDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (hoaDonChiTietDao.checkHoaDon(arrHoaDon.get(position).getMaHoaDon())){
                        Toast.makeText(context,"Cần phải xóa hóa đơn chi tiết trước khi xóa hóa đơn này",Toast.LENGTH_SHORT).show();
                    }else {
                        hoaDonDao.delHoaDonByID(arrHoaDon.get(position).getMaHoaDon());
                        arrHoaDon.remove(position);
                        notifyDataSetChanged();
                    }
                }
            });
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        HoaDon hd = (HoaDon) arrHoaDon.get(position);
        holder.img.setImageResource(R.drawable.hdicon);
        holder.txtMaHoaDon.setText(hd.getMaHoaDon());
        holder.txtNgayMua.setText(sdf.format(hd.getNgayMua()));
        return convertView;
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    public void changeDataset(List<HoaDon> item){
        this.arrHoaDon = item;
        notifyDataSetChanged();
    }
    public void resetData(){
        arrHoaDon = arrSortHoaDon;
    }


    @Override
    public Filter getFilter() {
        if (hoaDonFilter == null){
            hoaDonFilter = new CustomFilter();
        }
        return hoaDonFilter;
    }
    public class CustomFilter extends Filter{

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            if (constraint == null || constraint.length() == 0){
                results.values =arrSortHoaDon;
                results.count = arrSortHoaDon.size();
            }else {
                List<HoaDon> lsHoaDon = new ArrayList<>();
                for (HoaDon hd :arrHoaDon){
                    if (hd.getMaHoaDon().toUpperCase().startsWith(constraint.toString().toUpperCase())){
                        lsHoaDon.add(hd);
                    }
                    results.count = lsHoaDon.size();
                    results.values = lsHoaDon;
                }
            }
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            if (results.count==0){
                notifyDataSetInvalidated();
            }else {
                arrHoaDon = (List<HoaDon>)results.values;
                notifyDataSetChanged();
            }
        }
    }
}
