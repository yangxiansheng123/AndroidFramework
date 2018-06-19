package com.example.administrator.oper.adapter;

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
 * 适配器 学员退款列表
 */

public class StudentRefundsAdapter extends BaseQuickAdapter<MyHomeworkBean, BaseViewHolder> {
    private Context mContext;

    /**
     * @param layoutResId layouy.xml布局
     * @param data        数据
     * @param context     上下文
     */
    public StudentRefundsAdapter(@LayoutRes int layoutResId, @Nullable List<MyHomeworkBean> data, Context context) {
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
        TextView tv_refundTitle = baseViewHolder.getView(R.id.tv_refundTitle);
        TextView tv_refundCTime = baseViewHolder.getView(R.id.tv_refundCTime);
        TextView tv_refundContractName = baseViewHolder.getView(R.id.tv_refundContractName);
        TextView tv_refundTeacherName = baseViewHolder.getView(R.id.tv_refundTeacherName);
        TextView tv_refundTuition = baseViewHolder.getView(R.id.tv_refundTuition);
        TextView tv_refundReceipts = baseViewHolder.getView(R.id.tv_refundReceipts);

        tv_refundTitle.setText("张晓涵");
        tv_refundCTime.setText(new SpanUtils().append("已消耗课时/总课时：")
                .setForegroundColor(Color.parseColor("#a5a5a5"))
                .setFontSize(48)
                .append("66/96")
                .setForegroundColor(Color.parseColor("#333333"))
                .setFontSize(48)
                .create());

        tv_refundContractName.setText(new SpanUtils().append("合同名：")
                .setForegroundColor(Color.parseColor("#a5a5a5"))
                .setFontSize(48)
                .append("钢琴初级课程")
                .setForegroundColor(Color.parseColor("#333333"))
                .setFontSize(48)
                .create());

        tv_refundTeacherName.setText(new SpanUtils().append("老师：")
                .setForegroundColor(Color.parseColor("#a5a5a5"))
                .setFontSize(48)
                .append("苏亚")
                .setForegroundColor(Color.parseColor("#333333"))
                .setFontSize(48)
                .create());

        tv_refundTuition.setText(new SpanUtils().append("●")
                .setForegroundColor(Color.parseColor("#3ed548"))
                .setFontSize(32)
                .appendSpace(20)
                .append("学费：9600元")
                .setForegroundColor(Color.parseColor("#333333"))
                .setFontSize(48)
                .create());

        tv_refundReceipts.setText(new SpanUtils().append("●")
                .setForegroundColor(Color.parseColor("#f39019"))
                .setFontSize(32)
                .appendSpace(20)
                .append("实收：6600元")
                .setForegroundColor(Color.parseColor("#333333"))
                .setFontSize(48)
                .create());

//        Glide.with(mContext)
//                .load(data.getPic_info())
//                .centerCrop()
//                .dontAnimate()//防止设置placeholder导致第一次不显示网络图片,只显示默认图片的问题
//                .placeholder(R.mipmap.ic_launcher)
//                .error(R.mipmap.ic_launcher)
//                .into(img_artForumHead);

    }


}
