package com.example.administrator.oper.adapter.finance;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.TextView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.oper.R;
import com.example.administrator.oper.bean.edu.ClassManagementDetailsBean;
import com.example.administrator.oper.bean.finance.HistoryWageRecordBean;

import java.util.List;

/**
 * @author yang
 * @date 20185/4/16
 * 适配器 历史工资记录
 */

public class HistoryWageRecordAdapter extends BaseQuickAdapter<HistoryWageRecordBean, BaseViewHolder> {
    private Context mContext;

    /**
     * @param layoutResId layouy.xml布局
     * @param data        数据
     * @param context     上下文
     */
    public HistoryWageRecordAdapter(@LayoutRes int layoutResId, @Nullable List<HistoryWageRecordBean> data, Context context) {
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
    protected void convert(BaseViewHolder baseViewHolder, HistoryWageRecordBean data) {
        TextView tv_performance = baseViewHolder.getView(R.id.tv_performance);

//        tv_performance.setText("");

//        Glide.with(mContext)
//                .load(data.getPic_info())
//                .centerCrop()
//                .dontAnimate()//防止设置placeholder导致第一次不显示网络图片,只显示默认图片的问题
//                .placeholder(R.mipmap.ic_launcher)
//                .error(R.mipmap.ic_launcher)
//                .into(img_artForumHead);

    }


}
