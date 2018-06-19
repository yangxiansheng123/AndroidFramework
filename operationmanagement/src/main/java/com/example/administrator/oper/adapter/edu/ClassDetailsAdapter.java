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
 * 适配器 班级管理-->班级详情
 */

public class ClassDetailsAdapter extends BaseQuickAdapter<ClassManagementDetailsBean, BaseViewHolder> {
    private Context mContext;

    /**
     * @param layoutResId layouy.xml布局
     * @param data        数据
     * @param context     上下文
     */
    public ClassDetailsAdapter(@LayoutRes int layoutResId, @Nullable List<ClassManagementDetailsBean> data, Context context) {
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
        TextView tv_memberName = baseViewHolder.getView(R.id.tv_memberName);
        TextView tv_memberTimetable = baseViewHolder.getView(R.id.tv_memberTimetable);
        TextView tv_attendance = baseViewHolder.getView(R.id.tv_attendance);


        tv_memberName.setText(new SpanUtils()
                .appendSpace(30)
                .append("●")
                .setForegroundColor(Color.parseColor("#333333"))
                .setFontSize(32)
                .appendSpace(20)
                .append("苏大牙")
                .setForegroundColor(Color.parseColor("#333333"))
                .setFontSize(40)
                .create());

        tv_memberTimetable.setText(new SpanUtils()
                .append("开始：2018-01-05")
                .setForegroundColor(Color.parseColor("#bdbdbd"))
                .setFontSize(38)
                .appendSpace(20)
                .appendLine()
                .append("结束：2018-03-28")
                .setForegroundColor(Color.parseColor("#bdbdbd"))
                .setFontSize(38)
                .appendSpace(60)
                .create());

        tv_attendance.setText(new SpanUtils()
                .append("出勤率")
                .setForegroundColor(Color.parseColor("#bdbdbd"))
                .setFontSize(38)
                .appendSpace(20)
                .append("100%")
                .setForegroundColor(Color.parseColor("#fe471d"))
                .setFontSize(38)
                .appendLine()
                .append("剩余课时")
                .setForegroundColor(Color.parseColor("#bdbdbd"))
                .setFontSize(38)
                .appendSpace(20)
                .append("20")
                .setForegroundColor(Color.parseColor("#fe471d"))
                .setFontSize(38)
                .appendSpace(30)
                .create());


    }



}
