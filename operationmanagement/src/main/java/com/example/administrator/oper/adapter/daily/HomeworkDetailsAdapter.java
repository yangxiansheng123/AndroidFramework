package com.example.administrator.oper.adapter.daily;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.oper.R;
import com.example.administrator.oper.bean.MyHomeworkBean;
import com.example.administrator.oper.bean.daily.HomeworkDetailsBean;
import com.example.administrator.oper.manager.Constants;
import com.utils.SpanUtils;
import de.hdodenhof.circleimageview.CircleImageView;

import java.util.List;

/**
 * @author yang
 * @date 20185/4/16
 * 适配器 xx课堂作业列表详情
 */

public class HomeworkDetailsAdapter extends BaseQuickAdapter<HomeworkDetailsBean, BaseViewHolder> {
    private Context mContext;

    /**
     * @param layoutResId layouy.xml布局
     * @param data        数据
     * @param context     上下文
     */
    public HomeworkDetailsAdapter(@LayoutRes int layoutResId, @Nullable List<HomeworkDetailsBean> data, Context context) {
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
    protected void convert(BaseViewHolder baseViewHolder, HomeworkDetailsBean data) {
        CircleImageView img_head = baseViewHolder.getView(R.id.img_head);
        TextView tv_perName = baseViewHolder.getView(R.id.tv_perName);
        TextView tv_homeworkTime = baseViewHolder.getView(R.id.tv_homeworkTime);
        TextView tv_homeworkState = baseViewHolder.getView(R.id.tv_homeworkState);


        tv_perName.setText("苏达雅");

        tv_homeworkTime.setText("2018-03-23");


        tv_homeworkState.setText(new SpanUtils()
                .append("状态")
                .setForegroundColor(Color.parseColor("#0090ff"))
                .setFontSize(36)
                .create());







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


}
