package com.example.administrator.oper.adapter.finance;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.TextView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.oper.R;
import com.example.administrator.oper.bean.MyHomeworkBean;

import java.util.List;

/**
 * @author yang
 * @date 20185/4/16
 * 适配器 工资列表
 */

public class PayrollsListAdapter extends BaseQuickAdapter<MyHomeworkBean, BaseViewHolder> {
    private Context mContext;

    /**
     * @param layoutResId layouy.xml布局
     * @param data        数据
     * @param context     上下文
     */
    public PayrollsListAdapter(@LayoutRes int layoutResId, @Nullable List<MyHomeworkBean> data, Context context) {
        super(layoutResId, data);
        mContext = context;
    }

    /**
     * 绑定xml布局 显示数据
     * @param baseViewHolder
     * @param data
     */
    @Override
    protected void convert(BaseViewHolder baseViewHolder, MyHomeworkBean data) {
        TextView tv_financeName = baseViewHolder.getView(R.id.tv_financeName);
        TextView tv_financeSalary = baseViewHolder.getView(R.id.tv_financeSalary);
        TextView tv_financePerformance = baseViewHolder.getView(R.id.tv_financePerformance);
        TextView tv_financePayroll = baseViewHolder.getView(R.id.tv_financePayroll);

        tv_financeName.setText("扎龙");
        tv_financeSalary.setText("3000.0");
        tv_financePerformance.setText("245641");
        tv_financePayroll.setText("3300");
//        Glide.with(mContext)
//                .load(data.getPic_info())
//                .centerCrop()
//                .dontAnimate()//防止设置placeholder导致第一次不显示网络图片,只显示默认图片的问题
//                .placeholder(R.mipmap.ic_launcher)
//                .error(R.mipmap.ic_launcher)
//                .into(img_artForumHead);

    }


}
