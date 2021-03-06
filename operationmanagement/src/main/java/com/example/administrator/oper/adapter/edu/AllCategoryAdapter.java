package com.example.administrator.oper.adapter.edu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.example.administrator.oper.R;

import java.util.List;

/**
 * Created by imahe001 on 2015/9/29.
 * 添加班级-->选择课程分类 list one
 */
public class AllCategoryAdapter extends BaseAdapter {


    private List<String> listData;
    private Context context;
    private LayoutInflater inflater;
    private int selectionPosition;

    public AllCategoryAdapter(Context context) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }

    public void setListData(List<String> listData) {
        this.listData = listData;
    }

    @Override
    public int getCount() {
        return listData.size()>0?listData.size():0;
    }

    @Override
    public Object getItem(int position) {
//        return goodsClassifyAllInfos.get(position);
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
            convertView = inflater.inflate(R.layout.item_all_category_pattern, null);
            holder.tvClassify = (TextView) convertView.findViewById(R.id.tv_classify);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.tvClassify.setText("ALLL");
        return convertView;
    }

    class ViewHolder {
        TextView tvClassify;
    }
}
