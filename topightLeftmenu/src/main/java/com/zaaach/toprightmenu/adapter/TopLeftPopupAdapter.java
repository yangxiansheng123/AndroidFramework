package com.zaaach.toprightmenu.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.zaaach.toprightmenu.R;
import com.zaaach.toprightmenu.bean.TopLeftPopupBean;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by imahe001 on 2015/9/29.
 */
public class TopLeftPopupAdapter extends BaseAdapter {

    private Context context;

    List<TopLeftPopupBean> lisData = new ArrayList<>();

    public TopLeftPopupAdapter(Context context) {
        this.context = context;
    }

    public void setLisData(List<TopLeftPopupBean> lisData) {
        this.lisData = lisData;
    }

    @Override
    public int getCount() {
        return lisData.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.popup_home_page_top_left_pattern, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }


        holder.tv_accLocCity.setText(lisData.get(position).getLocationCity());
        holder.tv_city.setText(lisData.get(position).getCity());
        return convertView;
    }

    class ViewHolder {
        TextView tv_accLocCity;
        TextView tv_city;

        public ViewHolder(View view) {
            tv_accLocCity = (TextView) view.findViewById(R.id.tv_accLocCity);
            tv_city = (TextView) view.findViewById(R.id.tv_city);
        }
    }
}
