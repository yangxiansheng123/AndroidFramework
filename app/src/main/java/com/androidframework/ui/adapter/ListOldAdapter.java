package com.androidframework.ui.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.androidframework.R;
import com.androidframework.bean.LoginBean;

import java.util.List;
/**
 * @author yang
 * listView适配器
 */
public class ListOldAdapter extends BaseAdapter {


    private List<LoginBean> listData;
    private Context context;
    private LayoutInflater inflater;
    private int selectionPosition;

    public void setListData(List<LoginBean> listData) {
        this.listData = listData;
    }

    public ListOldAdapter(Context context) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return listData.size() > 0 ? listData.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void setSelectionPosition(int selectionPosition) {
        this.selectionPosition = selectionPosition;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.activity_main, null);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.tvClassify.setText("展示的数据");

        if (position == selectionPosition) {
            convertView.setBackgroundColor(Color.WHITE);
            holder.tvClassify.setTextColor(context.getResources().getColor(com.zxl.dropdownmenue.R.color.un_press_color));
        } else {
            holder.tvClassify.setTextColor(Color.BLACK);
            convertView.setBackgroundColor(Color.argb(0x0, 0xf5, 0xf5, 0xf5));
        }
        return convertView;
    }

    class ViewHolder {
        TextView tvClassify;
    }
}
