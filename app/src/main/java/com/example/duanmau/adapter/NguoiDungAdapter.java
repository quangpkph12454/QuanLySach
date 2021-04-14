package com.example.duanmau.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.duanmau.R;
import com.example.duanmau.dao.NguoiDungDao;
import com.example.duanmau.model.NguoiDung;

import java.util.List;

public class NguoiDungAdapter extends BaseAdapter {
    private Context context;
    private List<NguoiDung> arrNguoiDung;
    private LayoutInflater inflater;
    private NguoiDungDao nguoiDungDao;

    public NguoiDungAdapter(Context context, List<NguoiDung> arrNguoiDung) {
        this.context = context;
        this.arrNguoiDung = arrNguoiDung;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        nguoiDungDao = new NguoiDungDao(context);
    }

    @Override
    public int getCount() {
        return arrNguoiDung.size();
    }

    @Override
    public Object getItem(int position) {
        return arrNguoiDung.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView == null){
            viewHolder = new ViewHolder();
            convertView = inflater.inflate(R.layout.item_nguoi_dung,null);
            viewHolder.tvName = (TextView) convertView.findViewById(R.id.tvName);
            viewHolder.tvPhone = (TextView) convertView.findViewById(R.id.tvPhone);
            viewHolder.imgDelete =(ImageView) convertView.findViewById(R.id.ivDelete);
            viewHolder.img = (ImageView) convertView.findViewById(R.id.ivIcon);

            viewHolder.imgDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    NguoiDung nd = arrNguoiDung.get(position);
                    arrNguoiDung.remove(nd);//xoa trong list, but doen't remove it in database
                    notifyDataSetChanged();//update
                    nguoiDungDao.deleteNguoiDung(nd.getUsername());//delete the NguoiDung in database

                }
            });
            //tạo view

            //tạo template từ view
            convertView.setTag(viewHolder);

        }else {
            viewHolder = (ViewHolder) convertView.getTag();
            //lấy view đã tồn tại
        }
        NguoiDung nd = (NguoiDung) arrNguoiDung.get(position);
        if (position %3 == 0){
            viewHolder.img.setImageResource(R.drawable.emone);
        }else if (position %3 == 1){
            viewHolder.img.setImageResource(R.drawable.emtwo);
        }
        else {
            viewHolder.img.setImageResource(R.drawable.emthree);
        }
        viewHolder.tvName.setText(nd.getHoten());
        viewHolder.tvPhone.setText(nd.getPhone());
        return convertView;
    }
    public static class ViewHolder{
        ImageView img;
        TextView tvName;
        TextView tvPhone;
        ImageView imgDelete;
    }
    public void changeDataset(List<NguoiDung> ls)
    {
        this.arrNguoiDung = ls;
        notifyDataSetChanged();
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

}
