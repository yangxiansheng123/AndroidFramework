package com.example.administrator.oper.adapter.edu;

import android.content.Context;
import android.graphics.Color;
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
 * 上课时间设置
 */
public class ClassTimeSettingAdapter extends BaseAdapter {

    private DeleteSettingTime mDeleteSettingTime;
    private List<ClassTimeSettingBean> listData;
    private Context mContext;
    private LayoutInflater inflater;
    private int selectionPosition;


    public ClassTimeSettingAdapter(Context context, DeleteSettingTime deleteSettingTime) {
        mContext = context;
        inflater = LayoutInflater.from(mContext);
        mDeleteSettingTime = deleteSettingTime;
    }

    public void setListData(List<ClassTimeSettingBean> listData) {
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
            convertView = inflater.inflate(R.layout.activity_class_time_setting_pattern, null);
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

        holder.tv_courseType.setText(listData.get(position).getClassTimeType());
        holder.tv_settingYMD.setText(listData.get(position).getClassTimeTimeYear());
        holder.tv_settingHMS.setText(listData.get(position).getClassTimeTimeSlot());

        holder.img_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDeleteSettingTime.deleteTime(listData, position);
            }
        });
        holder.tv_courseType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDeleteSettingTime.modifyCourseType(listData, position);
            }
        });
        holder.tv_settingYMD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDeleteSettingTime.modifyCourseYMD(listData, position);

            }
        });

        holder.tv_settingHMS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mDeleteSettingTime.modifyCourseHMS(listData, position);
            }
        });

        return convertView;
    }

    class ViewHolder {
        TextView tv_courseType;
        TextView tv_settingYMD;
        TextView tv_settingHMS;
        ImageView img_delete;
        LinearLayout ll_timesetting;

        public ViewHolder(View view) {
            tv_courseType = (TextView) view.findViewById(R.id.tv_courseType);
            tv_settingYMD = (TextView) view.findViewById(R.id.tv_settingYMD);
            tv_settingHMS = (TextView) view.findViewById(R.id.tv_settingHMS);
            img_delete = (ImageView) view.findViewById(R.id.img_delete);
            ll_timesetting = (LinearLayout) view.findViewById(R.id.ll_timesetting);
        }
    }

    public interface DeleteSettingTime {
        public void deleteTime(List<ClassTimeSettingBean> listData, int position);

        public void modifyCourseType(List<ClassTimeSettingBean> listData, int position);

        public void modifyCourseYMD(List<ClassTimeSettingBean> listData, int position);

        public void modifyCourseHMS(List<ClassTimeSettingBean> listData, int position);
    }
}
