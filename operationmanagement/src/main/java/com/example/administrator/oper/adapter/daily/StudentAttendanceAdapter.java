package com.example.administrator.oper.adapter.daily;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.oper.R;
import com.example.administrator.oper.bean.daily.ParentalDistributionBean;
import com.example.administrator.oper.bean.daily.StudentAttendanceBean;
import com.example.administrator.oper.manager.Constants;

import java.util.List;

/**
 * @author yang
 * @date 20185/4/16
 * 适配器 学员考勤
 */

public class StudentAttendanceAdapter extends BaseQuickAdapter<StudentAttendanceBean, BaseViewHolder> {
    private Context mContext;

    /**
     * @param layoutResId layouy.xml布局
     * @param data        数据
     * @param context     上下文
     */
    public StudentAttendanceAdapter(@LayoutRes int layoutResId, @Nullable List<StudentAttendanceBean> data, Context context) {
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
    protected void convert(BaseViewHolder baseViewHolder, StudentAttendanceBean data) {
        TextView tv_attendanceTitle = baseViewHolder.getView(R.id.tv_attendanceTitle);
        TextView tv_readingSign = baseViewHolder.getView(R.id.tv_readingSign);

        tv_attendanceTitle.setText("初级钢琴体验课程");
        tv_readingSign.setText("在读：4  签到：0  请假：1  未签到：3");




    }


}
