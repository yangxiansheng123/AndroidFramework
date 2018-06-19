package com.example.administrator.oper.adapter.edu;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.example.administrator.oper.R;
import com.example.administrator.oper.db.ClassTimeSettingBean;

import java.util.List;

/**
 * @author yang
 * 上课时间列表
 */
public class ClassTimeSettingListAdapter extends BaseAdapter {

    private List<String> listData;
    private Context mContext;
    private LayoutInflater inflater;
    private int selectionPosition;


    public ClassTimeSettingListAdapter(Context context) {
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
        ViewHolder  holder = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.activity_class_time_list_pattern, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        if (listData.size() - 1 == position) {
            holder.ll_timesetting.setBackgroundDrawable(ContextCompat.getDrawable(mContext, R.drawable.solid_shape_white_bootom_lright));
        } else {
            holder.ll_timesetting.setBackgroundColor(mContext.getResources().getColor(R.color.white));
        }

        holder.tv_time.setText(listData.get(position));

        return convertView;
    }

    class ViewHolder {
        TextView tv_time;
        LinearLayout ll_timesetting;

        public ViewHolder(View view) {
            tv_time = (TextView) view.findViewById(R.id.tv_time);
            ll_timesetting = (LinearLayout) view.findViewById(R.id.ll_timesetting);
        }
    }

}
