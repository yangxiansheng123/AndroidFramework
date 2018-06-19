package com.example.administrator.oper.adapter.daily;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.oper.R;
import com.example.administrator.oper.bean.daily.ParentalDistributionBean;
import com.example.administrator.oper.manager.Constants;
import de.hdodenhof.circleimageview.CircleImageView;

import java.util.List;

/**
 * @author yang
 * @date 20185/4/16
 * 适配器 课程签到----->再读学员
 */

public class CourseCIReadingStuAdapter extends BaseQuickAdapter<ParentalDistributionBean, BaseViewHolder> {
    private Context mContext;
    List<ParentalDistributionBean> listData;
    private FrameLayout framel_bg;
    private LinearLayout ll_bg;

    /**
     * @param layoutResId layouy.xml布局
     * @param data        数据
     * @param context     上下文
     */
    public CourseCIReadingStuAdapter(@LayoutRes int layoutResId, @Nullable List<ParentalDistributionBean> data, Context context) {
        super(layoutResId, data);
        mContext = context;
        listData = data;
    }

    /**
     * 绑定xml布局 显示数据
     *
     * @param baseViewHolder
     * @param data
     */
    @Override
    protected void convert(BaseViewHolder baseViewHolder, ParentalDistributionBean data) {
        TextView tv_signInName = baseViewHolder.getView(R.id.tv_signInName);
       ll_bg = baseViewHolder.getView(R.id.ll_bg);

        tv_signInName.setText("赵县");

    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int positions) {
        super.onBindViewHolder(holder, positions);
        if (0 == positions) {
//            ll_bg.setBackgroundResource(R.drawable.solid_shape_white_top_left_right);
        } else if (listData.size()-1 == positions) {
            ll_bg.setBackgroundResource(R.drawable.solid_shape_white_bootom_lright);
        }
    }
}
