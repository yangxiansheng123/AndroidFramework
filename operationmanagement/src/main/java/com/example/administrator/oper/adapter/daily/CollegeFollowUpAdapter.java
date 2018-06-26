package com.example.administrator.oper.adapter.daily;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
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
import com.example.administrator.oper.bean.daily.CollegeFollowUpBean;
import com.example.administrator.oper.bean.daily.WaitForClassBean;
import com.example.administrator.oper.manager.Constants;
import de.hdodenhof.circleimageview.CircleImageView;

import java.util.List;

/**
 * @author yang
 * @date 20185/4/16
 * 适配器 日常-->学员跟进
 */

public class CollegeFollowUpAdapter extends BaseQuickAdapter<CollegeFollowUpBean, BaseViewHolder> {
    private Context mContext;
    private FrameLayout fl_collegeFollowBg;
    List<CollegeFollowUpBean> listData;

    /**
     * @param layoutResId layouy.xml布局
     * @param data        数据
     * @param context     上下文
     */
    public CollegeFollowUpAdapter(@LayoutRes int layoutResId, @Nullable List<CollegeFollowUpBean> data, Context context) {
        super(layoutResId, data);
        mContext = context;
        listData =data;
    }

    /**
     * 绑定xml布局 显示数据
     *
     * @param baseViewHolder
     * @param data
     */
    @Override
    protected void convert(BaseViewHolder baseViewHolder, CollegeFollowUpBean data) {
        CircleImageView img_head = baseViewHolder.getView(R.id.img_head);
        TextView tv_waitName = baseViewHolder.getView(R.id.tv_waitName);
        TextView tv_waitPhone = baseViewHolder.getView(R.id.tv_waitPhone);
        TextView tv_waitState = baseViewHolder.getView(R.id.tv_waitState);
        TextView tv_parentName = baseViewHolder.getView(R.id.tv_parentName);
         fl_collegeFollowBg = baseViewHolder.getView(R.id.fl_collegeFollowBg);

        tv_waitName.setText("苏大牙");
        tv_waitPhone.setText("18092820976");

        tv_waitState.setText("待排课");

        tv_parentName.setText("家长苏达雅");





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
            fl_collegeFollowBg.setBackgroundResource(R.drawable.solid_shape_white_top_left_right);
        } else if (listData.size() - 1 == positions) {
            fl_collegeFollowBg.setBackgroundResource(R.drawable.solid_shape_white_bootom_lright);
        }
    }

}
