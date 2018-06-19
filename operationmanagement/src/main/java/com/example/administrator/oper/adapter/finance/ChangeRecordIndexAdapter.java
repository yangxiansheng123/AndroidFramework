package com.example.administrator.oper.adapter.finance;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.example.administrator.oper.R;

import java.util.List;

/**
 * @author yang
 * listView适配器 变更记录首页数据展示
 */
public class ChangeRecordIndexAdapter extends BaseAdapter {


    private List<String> listData;
    private Context context;
    private LayoutInflater inflater;
    private int selectionPosition;

    public void setListData(List<String> listData) {
        this.listData = listData;
    }

    public ChangeRecordIndexAdapter(Context context) {
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
            convertView = inflater.inflate(R.layout.activity_change_record_pattern, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.tv_performance.setText("绩效");

        return convertView;
    }

    class ViewHolder {
        TextView tv_performance;

        public ViewHolder(View view) {
            tv_performance = (TextView) view.findViewById(R.id.tv_performance);
        }

    }
}
