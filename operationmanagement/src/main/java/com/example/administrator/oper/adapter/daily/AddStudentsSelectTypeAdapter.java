package com.example.administrator.oper.adapter.daily;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.administrator.oper.R;

import java.util.List;

/**
 * @author yang
 * 添加学员选择各种类型 eg:来源,客户评定
 */
public class AddStudentsSelectTypeAdapter extends BaseAdapter {


    private List<String> listData;
    private Context mContext;
    private LayoutInflater inflater;
    private int selectionPosition;

    public AddStudentsSelectTypeAdapter(Context context) {
        mContext = context;
        inflater = LayoutInflater.from(mContext);
    }

    public void setListData(List<String> listData) {
        this.listData = listData;
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



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.add_students_select_type_pattern, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.tv_allTypes.setText(listData.get(position));

        return convertView;
    }

    class ViewHolder {
        TextView tv_allTypes;

        public ViewHolder(View view) {
            tv_allTypes = (TextView) view.findViewById(R.id.tv_allTypes);
        }
    }
}
