package com.example.administrator.oper.adapter.edu;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.TextView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.oper.R;
import com.example.administrator.oper.bean.edu.ClassManagementDetailsBean;
import com.utils.SpanUtils;

import java.util.List;

/**
 * @author yang
 * @date 20185/4/16
 * 适配器 班级详情--> 课日程列表
 */

public class CourseArrangementListAdapter extends BaseQuickAdapter<ClassManagementDetailsBean, BaseViewHolder> {
    private Context mContext;

    /**
     * @param layoutResId layouy.xml布局
     * @param data        数据
     * @param context     上下文
     */
    public CourseArrangementListAdapter(@LayoutRes int layoutResId, @Nullable List<ClassManagementDetailsBean> data, Context context) {
        super(layoutResId, data);
        mContext = context;
    }

    /**
     * 绑定xml布局 显示数据
     *
     * @param baseViewHolder
     * @param data
     */
    @Override
    protected void convert(BaseViewHolder baseViewHolder, ClassManagementDetailsBean data) {
        TextView tv_calTime = baseViewHolder.getView(R.id.tv_calTime);
        TextView tv_calIsStartCourse = baseViewHolder.getView(R.id.tv_calIsStartCourse);
        TextView tv_calTeacher = baseViewHolder.getView(R.id.tv_calTeacher);
        TextView tv_calClassroom = baseViewHolder.getView(R.id.tv_calClassroom);
        TextView tv_calAttendance = baseViewHolder.getView(R.id.tv_calAttendance);

        tv_calTime.setText("4月12日周四  09:00-12:00");
        tv_calIsStartCourse.setText("未开始");
        tv_calTeacher.setText("老师：苏大牙");
        tv_calClassroom.setText("教室：钢琴室");
        tv_calAttendance.setText("出勤率：30%");


//        Glide.with(mContext)
//                .load(data.getPic_info())
//                .centerCrop()
//                .dontAnimate()//防止设置placeholder导致第一次不显示网络图片,只显示默认图片的问题
//                .placeholder(R.mipmap.ic_launcher)
//                .error(R.mipmap.ic_launcher)
//                .into(img_artForumHead);

    }


}
