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
import com.example.administrator.oper.bean.edu.AddOneToOneStudentClassTimeBean;
import com.example.administrator.oper.db.ClassTimeSettingBean;

import java.util.List;

/**
 * @author yang
 * 添加一对一学员适配器
 */
public class AddOneToOneStudentAdapter extends BaseAdapter {

    private UpdateStudentTime mDeleteSettingTime;
    private List<AddOneToOneStudentClassTimeBean> listData;
    private Context mContext;
    private LayoutInflater inflater;
    private int selectionPosition;


    public AddOneToOneStudentAdapter(Context context, UpdateStudentTime deleteSettingTime) {
        mContext = context;
        inflater = LayoutInflater.from(mContext);
        mDeleteSettingTime = deleteSettingTime;
    }

    public void setListData(List<AddOneToOneStudentClassTimeBean> listData) {
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
            convertView = inflater.inflate(R.layout.activity_add_one_to_one_students_pattern, null);
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

        holder.tv_courseType.setText(listData.get(position).getCoruseType());
        holder.tv_settingYMD.setText(listData.get(position).getClassTime());
        holder.tv_selectClassroom.setText(listData.get(position).getClassroom());

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
        ViewHolder finalHolder = holder;
        holder.tv_settingYMD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDeleteSettingTime.modifyCourseYMD(listData, position);
                finalHolder.tv_settingYMD.setTextColor(Color.parseColor("#333333"));

            }
        });
        holder.tv_selectClassroom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDeleteSettingTime.selectClassroom(listData, position);
                finalHolder.tv_selectClassroom.setTextColor(Color.parseColor("#333333"));

            }
        });



        return convertView;
    }

    class ViewHolder {
        TextView tv_courseType;
        TextView tv_settingYMD;
        TextView tv_selectClassroom;
        ImageView img_delete;
        LinearLayout ll_timesetting;

        public ViewHolder(View view) {
            tv_courseType = (TextView) view.findViewById(R.id.tv_courseType);
            tv_settingYMD = (TextView) view.findViewById(R.id.tv_settingYMD);
            tv_selectClassroom = (TextView) view.findViewById(R.id.tv_selectClassroom);
            img_delete = (ImageView) view.findViewById(R.id.img_delete);
            ll_timesetting = (LinearLayout) view.findViewById(R.id.ll_timesetting);
        }
    }

    public interface UpdateStudentTime {
        public void deleteTime(List<AddOneToOneStudentClassTimeBean> listData, int position);

        public void modifyCourseType(List<AddOneToOneStudentClassTimeBean> listData, int position);

        public void modifyCourseYMD(List<AddOneToOneStudentClassTimeBean> listData, int position);

        public void selectClassroom(List<AddOneToOneStudentClassTimeBean> listData, int position);

    }
}
