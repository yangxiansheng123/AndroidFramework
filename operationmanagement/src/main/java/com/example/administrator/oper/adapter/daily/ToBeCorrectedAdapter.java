package com.example.administrator.oper.adapter.daily;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.TextView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.oper.R;
import com.example.administrator.oper.bean.daily.ToBeCorrectedBean;
import com.utils.SpanUtils;

import java.util.List;

/**
 * @author yang
 * @date 20185/4/16
 * 适配器 待批改
 */

public class ToBeCorrectedAdapter extends BaseQuickAdapter<ToBeCorrectedBean, BaseViewHolder> {
    private Context mContext;

    /**
     * @param layoutResId layouy.xml布局
     * @param data        数据
     * @param context     上下文
     */
    public ToBeCorrectedAdapter(@LayoutRes int layoutResId, @Nullable List<ToBeCorrectedBean> data, Context context) {
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
    protected void convert(BaseViewHolder baseViewHolder, ToBeCorrectedBean data) {
        TextView tv_homeworkTitle = baseViewHolder.getView(R.id.tv_homeworkTitle);
        TextView tv_homeworkState = baseViewHolder.getView(R.id.tv_homeworkState);
        TextView tv_homewordDesc = baseViewHolder.getView(R.id.tv_homewordDesc);
        TextView tv_homeworkByTime = baseViewHolder.getView(R.id.tv_homeworkByTime);
        TextView tv_totalCount = baseViewHolder.getView(R.id.tv_totalCount);
        TextView tv_Unpaid = baseViewHolder.getView(R.id.tv_Unpaid);
        TextView tv_notChanged = baseViewHolder.getView(R.id.tv_notChanged);
        TextView tv_haveChanged = baseViewHolder.getView(R.id.tv_haveChanged);


        tv_homeworkTitle.setText("第三节课堂作业");

        tv_homeworkState.setText(new SpanUtils()
                .append("进行中")
                .setForegroundColor(Color.parseColor("#00cf3a"))
                .setFontSize(48)
                .create());

        tv_homewordDesc.setText("用自己拿手的程序语言编写程序用自己拿手的程序语言编写程序用自己拿手的程序语言编写");

        tv_homeworkByTime.setText(new SpanUtils()
                .append("截止于")
                .setForegroundColor(Color.parseColor("#0090ff"))
                .setFontSize(36)
                .append(" 2018-03-23")
                .setForegroundColor(Color.parseColor("#a5a5a5"))
                .setFontSize(36)
                .create());

        tv_totalCount.setText("总数:30人  ");
        tv_Unpaid.setText("未交:10人 ");
        tv_notChanged.setText("未改:10人");
        tv_haveChanged.setText("已改:10人");
//        Glide.with(mContext)
//                .load(data.getPic_info())
//                .centerCrop()
//                .dontAnimate()//防止设置placeholder导致第一次不显示网络图片,只显示默认图片的问题
//                .placeholder(R.mipmap.ic_launcher)
//                .error(R.mipmap.ic_launcher)
//                .into(img_artForumHead);

    }


}
