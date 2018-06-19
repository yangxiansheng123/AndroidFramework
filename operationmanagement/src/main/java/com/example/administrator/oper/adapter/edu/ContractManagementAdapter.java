package com.example.administrator.oper.adapter.edu;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.TextView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.oper.R;
import com.example.administrator.oper.bean.edu.ContractManagementBean;
import com.utils.SpanUtils;

import java.util.List;

/**
 * @author yang
 * @date 20185/4/16
 * 适配器 合同管理
 */

public class ContractManagementAdapter extends BaseQuickAdapter<ContractManagementBean, BaseViewHolder> {
    private Context mContext;

    /**
     * @param layoutResId layouy.xml布局
     * @param data        数据
     * @param context     上下文
     */
    public ContractManagementAdapter(@LayoutRes int layoutResId, @Nullable List<ContractManagementBean> data, Context context) {
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
    protected void convert(BaseViewHolder baseViewHolder, ContractManagementBean data) {
        TextView tv_contractPackage = baseViewHolder.getView(R.id.tv_contractPackage);
        TextView tv_coursePrice = baseViewHolder.getView(R.id.tv_coursePrice);
        TextView tv_contractClassHour = baseViewHolder.getView(R.id.tv_contractClassHour);
        TextView tv_contractIncreaseCourse = baseViewHolder.getView(R.id.tv_contractIncreaseCourse);
        TextView tv_contractApplication = baseViewHolder.getView(R.id.tv_contractApplication);

        tv_contractPackage.setText("通用课时108套餐");
        tv_coursePrice.setText("￥18000");

        tv_contractClassHour.setText(new SpanUtils()
                .append("●")
                .setForegroundColor(Color.parseColor("#3ed548"))
                .setFontSize(32)
                .appendSpace(20)
                .append("合同课时：108")
                .setForegroundColor(Color.parseColor("#333333"))
                .setFontSize(48)
                .appendSpace(60)
                .create());
        tv_contractIncreaseCourse.setText(new SpanUtils()
                .append("●")
                .setForegroundColor(Color.parseColor("#f39019"))
                .setFontSize(32)
                .appendSpace(20)
                .append("赠送课时：0")
                .setForegroundColor(Color.parseColor("#333333"))
                .setFontSize(48)
                .appendSpace(60)
                .create());

        tv_contractApplication.setText("合同适用课时：钢琴、初级、中级、高级");


//        Glide.with(mContext)
//                .load(data.getPic_info())
//                .centerCrop()
//                .dontAnimate()//防止设置placeholder导致第一次不显示网络图片,只显示默认图片的问题
//                .placeholder(R.mipmap.ic_launcher)
//                .error(R.mipmap.ic_launcher)
//                .into(img_artForumHead);

    }


}
