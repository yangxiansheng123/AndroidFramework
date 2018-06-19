package com.example.administrator.oper.adapter.edu;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.TextView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.oper.R;
import com.example.administrator.oper.bean.MyHomeworkBean;
import com.example.administrator.oper.bean.edu.ClassManagementBean;
import com.utils.SpanUtils;

import java.util.List;

/**
 * @author yang
 * @date 20185/4/16
 * 适配器 班级管理
 */

public class ClassManagementAdapter extends BaseQuickAdapter<ClassManagementBean, BaseViewHolder> {
    private Context mContext;

    /**
     * @param layoutResId layouy.xml布局
     * @param data        数据
     * @param context     上下文
     */
    public ClassManagementAdapter(@LayoutRes int layoutResId, @Nullable List<ClassManagementBean> data, Context context) {
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
    protected void convert(BaseViewHolder baseViewHolder, ClassManagementBean data) {
        TextView tv_classmangType = baseViewHolder.getView(R.id.tv_classmangType);
        TextView tv_cmNameType = baseViewHolder.getView(R.id.tv_cmNameType);
        TextView tv_cmCategory = baseViewHolder.getView(R.id.tv_cmCategory);
        TextView tv_cmRedOrAudition = baseViewHolder.getView(R.id.tv_cmRedOrAudition);
        TextView tv_cmTimeTable = baseViewHolder.getView(R.id.tv_cmTimeTable);

        tv_classmangType.setText("1对1");
        tv_cmNameType.setText("苏大牙 老师");
        tv_cmCategory.setText("钢琴类别");

        tv_cmRedOrAudition.setText(new SpanUtils()
                .append("●")
                .setForegroundColor(Color.parseColor("#3ed548"))
                .setFontSize(32)
                .appendSpace(20)
                .append("在读：4/50")
                .setForegroundColor(Color.parseColor("#333333"))
                .setFontSize(48)
                .appendSpace(60)

                .append("●")
                .setForegroundColor(Color.parseColor("#f39019"))
                .setFontSize(32)
                .appendSpace(20)
                .append("试听：0")
                .setForegroundColor(Color.parseColor("#333333"))
                .setFontSize(48)
                .create());

        tv_cmTimeTable.setText("周二（12:00-12:45）/周三（12:00-12:45）/周六...");


//        Glide.with(mContext)
//                .load(data.getPic_info())
//                .centerCrop()
//                .dontAnimate()//防止设置placeholder导致第一次不显示网络图片,只显示默认图片的问题
//                .placeholder(R.mipmap.ic_launcher)
//                .error(R.mipmap.ic_launcher)
//                .into(img_artForumHead);

    }


}
