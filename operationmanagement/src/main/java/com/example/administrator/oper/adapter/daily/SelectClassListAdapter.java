package com.example.administrator.oper.adapter.daily;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.TextView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.oper.R;
import com.example.administrator.oper.bean.MyHomeworkBean;
import com.utils.SpanUtils;

import java.util.List;

/**
 * @author yang
 * @date 20185/4/16
 * 适配器 工资列表
 */

public class SelectClassListAdapter extends BaseQuickAdapter<MyHomeworkBean, BaseViewHolder> {
    private Context mContext;

    /**
     * @param layoutResId layouy.xml布局
     * @param data        数据
     * @param context     上下文
     */
    public SelectClassListAdapter(@LayoutRes int layoutResId, @Nullable List<MyHomeworkBean> data, Context context) {
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
    protected void convert(BaseViewHolder baseViewHolder, MyHomeworkBean data) {
        TextView tv_classTitle = baseViewHolder.getView(R.id.tv_classTitle);
        TextView tv_curriculum = baseViewHolder.getView(R.id.tv_curriculum);
        TextView tv_teacher = baseViewHolder.getView(R.id.tv_teacher);
        TextView tv_teacherName = baseViewHolder.getView(R.id.tv_teacherName);
        TextView tv_classroom = baseViewHolder.getView(R.id.tv_classroom);
        TextView tv_studying = baseViewHolder.getView(R.id.tv_studying);
        TextView tv_auditioned = baseViewHolder.getView(R.id.tv_auditioned);

        tv_classTitle.setText("09:00-12:00   钢琴");
        tv_curriculum.setText("18届钢琴初级春季课程");
        tv_teacher.setText("老师");
        tv_teacherName.setText("苏大牙");
        tv_classroom.setText("钢琴1002教室");
        tv_studying.setText(new SpanUtils().append("在读：")
                .setForegroundColor(Color.parseColor("#059b05"))
                .setFontSize(32)
                .append("4/50")
                .setForegroundColor(Color.parseColor("#333333"))
                .setFontSize(32)
                .create());
        tv_auditioned.setText(new SpanUtils().append("试听：")
                .setForegroundColor(Color.parseColor("#f1ee15"))
                .setFontSize(32)
                .append("0")
                .setForegroundColor(Color.parseColor("#a5a5a5"))
                .setFontSize(32)
                .create());

    }


}
