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

import com.example.duanmau.R;
import com.example.duanmau.dao.SachDao;
import com.example.duanmau.model.Sach;

import java.util.ArrayList;
import java.util.List;

public class SachAdapter extends BaseAdapter implements Filterable {

    List<Sach> arrSach;
    List<Sach> arrSortSach;
    private Filter sachFilter;
    public Activity context;
    public LayoutInflater inflater;
    SachDao sachDao;

    public SachAdapter(List<Sach> arrSach, Activity context) {
        super();
        this.arrSach = arrSach;
        this.arrSortSach = arrSach;
        this.context = context;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        sachDao = new SachDao(context) ;
    }

    @Override
    public int getCount() {
        return arrSach.size();
    }

    @Override
    public Object getItem(int position) {
        return arrSach.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public static class ViewHolder{
        ImageView img;
        TextView txtBookName;
        TextView txtBookPrice;
        TextView txtSoLuong;
        ImageView imgDelete;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null){
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.item_book,null);
            holder.img = convertView.findViewById(R.id.ivIcon);
            holder.txtBookName = convertView.findViewById(R.id.tvBookName);
            holder.txtBookPrice = convertView.findViewById(R.id.tvBookPrice);
            holder.txtSoLuong = convertView.findViewById(R.id.tvSoLuong);
            holder.imgDelete = convertView.findViewById(R.id.ivDelete);
            holder.imgDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    sachDao.delSach(arrSach.get(position).getMaSach());
                    arrSach.remove(position);
                    notifyDataSetChanged();
                }
            });
            convertView.setTag(holder);

        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        Sach sach = (Sach) arrSach.get(position);
        holder.img.setImageResource(R.drawable.bookicon);
        holder.txtBookName.setText("Mã Sách: " + sach.getMaSach());
        holder.txtBookPrice.setText("Giá Sách: " + sach.getGiaBia());
        holder.txtSoLuong.setText("Số Lượng Sách: " + sach.getSoLuong());
        return convertView;
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }
    public void changeDataset(List<Sach> item){
        this.arrSach = item;
        notifyDataSetChanged();
    }
    public void resetData(){
        arrSach = arrSortSach;
    }

    @Override
    public Filter getFilter() {
        if (sachFilter == null){
            sachFilter = new CustomFilter();
        }
        return sachFilter;
    }
    public class CustomFilter extends Filter{

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            if (constraint == null || constraint.length() == 0){
                results.values =arrSortSach;
                results.count = arrSortSach.size();
            }else {
                List<Sach> lsSach = new ArrayList<>();
                for (Sach s :arrSach){
                    if (s.getMaSach().toUpperCase().startsWith(constraint.toString().toUpperCase())){
                        lsSach.add(s);
                    }
                    results.count = lsSach.size();
                    results.values = lsSach;
                }
            }
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            if (results.count==0){
                notifyDataSetInvalidated();
            }else {
                arrSach = (List<Sach>)results.values;
                notifyDataSetChanged();
            }
        }

    }
}
