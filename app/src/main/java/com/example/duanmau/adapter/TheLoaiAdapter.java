package com.example.duanmau.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.IntentFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.duanmau.R;
import com.example.duanmau.dao.TheLoaiDao;
import com.example.duanmau.model.NguoiDung;
import com.example.duanmau.model.TheLoai;

import java.util.List;

public class TheLoaiAdapter extends BaseAdapter {
    public Activity context;
    private TheLoaiDao theLoaiDao;
    private List<TheLoai> lsTheLoai;
    private LayoutInflater inflater;

    public TheLoaiAdapter(Activity context, List<TheLoai> lsTheLoai) {
        this.context = context;
        theLoaiDao = new TheLoaiDao(context);
        this.lsTheLoai = lsTheLoai;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return lsTheLoai.size();
    }

    @Override
    public Object getItem(int position) {
        return lsTheLoai.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public static class ViewHolder{
        ImageView img;
        TextView tvMaTheLoai;
        TextView tvTenTheLoai;
        ImageView ivDelete;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView == null){
            viewHolder = new ViewHolder();
            convertView = inflater.inflate(R.layout.item_theloai,null);
            viewHolder.img = (ImageView) convertView.findViewById(R.id.ivIcon);
            viewHolder.tvMaTheLoai = (TextView) convertView.findViewById(R.id.tvMaTheLoai);
            viewHolder.tvTenTheLoai = (TextView) convertView.findViewById(R.id.tvTenTheLoai);
            viewHolder.ivDelete = (ImageView) convertView.findViewById(R.id.ivDelete);

            viewHolder.ivDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    TheLoai tl = lsTheLoai.get(position);
                    lsTheLoai.remove(tl);//xoa trong list, but doen't remove it in database
                    notifyDataSetChanged();//update
                    theLoaiDao.deleteTheLoai(tl.getMaTheLoai());//delete the NguoiDung in database
                }
            });

            convertView.setTag(viewHolder);

        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        TheLoai tl = (TheLoai) lsTheLoai.get(position);
        viewHolder.img.setImageResource(R.drawable.cateicon);
        viewHolder.tvMaTheLoai.setText(tl.getMaTheLoai());
        viewHolder.tvTenTheLoai.setText(tl.getTenTheLoai());
        return convertView;
    }
    public void changeDataset(List<TheLoai> ls)
    {
        this.lsTheLoai = ls;
        notifyDataSetChanged();
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }
}
