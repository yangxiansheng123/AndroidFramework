package com.example.administrator.oper.adapter.daily;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.oper.R;
import com.example.administrator.oper.bean.daily.ParentalDistributionBean;
import com.example.administrator.oper.bean.daily.RenewRemindingBean;
import com.example.administrator.oper.manager.Constants;
import com.utils.SpanUtils;
import de.hdodenhof.circleimageview.CircleImageView;

import java.util.List;

/**
 * @author yang
 * @date 20185/4/16
 * 适配器 家长分配
 */

public class ParentalDistributionAdapter extends BaseQuickAdapter<ParentalDistributionBean, BaseViewHolder> {
    private Context mContext;
    List<ParentalDistributionBean> listData;
    private FrameLayout framel_bg;

    /**
     * @param layoutResId layouy.xml布局
     * @param data        数据
     * @param context     上下文
     */
    public ParentalDistributionAdapter(@LayoutRes int layoutResId, @Nullable List<ParentalDistributionBean> data, Context context) {
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
        CircleImageView img_head = baseViewHolder.getView(R.id.img_head);
        TextView tv_pdistributionName = baseViewHolder.getView(R.id.tv_pdistributionName);
        TextView tv_pdistributionPhone = baseViewHolder.getView(R.id.tv_pdistributionPhone);
        framel_bg = baseViewHolder.getView(R.id.framel_bg);

        tv_pdistributionName.setText("赵县");
        tv_pdistributionPhone.setText("18092820976");





        RequestOptions options = new RequestOptions()
                .placeholder(com.yalantis.ucrop.R.color.ucrop_color_grey)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL);

        Glide.with(mContext)
                .load(Constants.URL)
//                .transition(DrawableTransitionOptions.withCrossFade())
                .apply(options)
                .into(img_head);

    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int positions) {
        super.onBindViewHolder(holder, positions);
        if (1 == positions) {
            framel_bg.setBackgroundResource(R.drawable.solid_shape_white_top_left_right);
        } else if (listData.size() == positions) {
            framel_bg.setBackgroundResource(R.drawable.solid_shape_white_bootom_lright);
        }
    }
}
