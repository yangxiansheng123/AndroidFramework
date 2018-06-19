package com.example.administrator.oper.adapter.daily;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.oper.R;
import com.example.administrator.oper.bean.daily.RenewRemindingBean;
import com.example.administrator.oper.bean.edu.ClassManagementBean;
import com.utils.SpanUtils;

import java.util.List;

/**
 * @author yang
 * @date 20185/4/16
 * 适配器 续约提醒
 */

public class RenewRemindingAdapter extends BaseQuickAdapter<RenewRemindingBean, BaseViewHolder> {
    private Context mContext;
    private ICheckData mICheckData;


    /**
     * @param layoutResId layouy.xml布局
     * @param data        数据
     * @param context     上下文
     */
    public RenewRemindingAdapter(@LayoutRes int layoutResId, @Nullable List<RenewRemindingBean> data, Context context,ICheckData ICheckData) {
        super(layoutResId, data);
        mContext = context;
        mICheckData = ICheckData;
    }

    /**
     * 绑定xml布局 显示数据
     *
     * @param baseViewHolder
     * @param data
     */
    @Override
    protected void convert(BaseViewHolder baseViewHolder, RenewRemindingBean data) {
        TextView tv_renewRemindName = baseViewHolder.getView(R.id.tv_renewRemindName);
        TextView tv_renewRemindClassHour = baseViewHolder.getView(R.id.tv_renewRemindClassHour);
        TextView tv_renewRemindTorF = baseViewHolder.getView(R.id.tv_renewRemindTorF);
        TextView tv_renewRemindPhone = baseViewHolder.getView(R.id.tv_renewRemindPhone);
        CheckBox check_renewRemind = baseViewHolder.getView(R.id.check_renewRemind);

        tv_renewRemindName.setText("苏大牙");
        tv_renewRemindTorF.setText("3岁5个月");

        tv_renewRemindClassHour.setText(new SpanUtils()
                .append("剩余课时")
                .setForegroundColor(Color.parseColor("#a5a5a5"))
                .setFontSize(48)
                .appendSpace(20)
                .append("60")
                .setForegroundColor(Color.parseColor("#333333"))
                .setFontSize(48)
                .create());

        tv_renewRemindPhone.setText("18092820976  苏亚");


        check_renewRemind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mICheckData.checkItem(data);
            }
        });

    }

    public interface ICheckData {
        public void checkItem(RenewRemindingBean data);
    }

}
