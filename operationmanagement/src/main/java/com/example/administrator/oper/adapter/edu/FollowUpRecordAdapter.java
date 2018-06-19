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
import com.example.administrator.oper.bean.edu.FollowUpRecordBean;
import com.utils.SpanUtils;

import java.util.List;

/**
 * @author yang
 * @date 20185/4/16
 * 适配器  家长详情-->跟进记录
 */

public class FollowUpRecordAdapter extends BaseQuickAdapter<FollowUpRecordBean, BaseViewHolder> {
    private Context mContext;

    /**
     * @param layoutResId layouy.xml布局
     * @param data        数据
     * @param context     上下文
     */
    public FollowUpRecordAdapter(@LayoutRes int layoutResId, @Nullable List<FollowUpRecordBean> data, Context context) {
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
    protected void convert(BaseViewHolder baseViewHolder, FollowUpRecordBean data) {
        TextView tv_followCount = baseViewHolder.getView(R.id.tv_followCount);
        TextView tv_followRecordM = baseViewHolder.getView(R.id.tv_followRecordM);
        TextView tv_communicationResults = baseViewHolder.getView(R.id.tv_communicationResults);
        TextView tv_nextFollowUp = baseViewHolder.getView(R.id.tv_nextFollowUp);
        TextView tv_followTime = baseViewHolder.getView(R.id.tv_followTime);
        TextView tv_followPerson = baseViewHolder.getView(R.id.tv_followPerson);

        tv_followCount.setText("第二次跟进");
        tv_followRecordM.setText("电话沟通");
        tv_communicationResults.setText("电话沟通 对课程设置比较满意，想对看看其它机构");

        tv_nextFollowUp.setText("放弃跟进");
        tv_followTime.setText("2018-03-23");
        tv_followPerson.setText("跟进人：汪大东");

//        Glide.with(mContext)
//                .load(data.getPic_info())
//                .centerCrop()
//                .dontAnimate()//防止设置placeholder导致第一次不显示网络图片,只显示默认图片的问题
//                .placeholder(R.mipmap.ic_launcher)
//                .error(R.mipmap.ic_launcher)
//                .into(img_artForumHead);

    }


}
