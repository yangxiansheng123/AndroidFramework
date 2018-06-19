package com.androidframework.ui.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import android.widget.ImageView;
import android.widget.TextView;
import com.androidframework.R;
import com.androidframework.bean.ExampleBean;
import com.androidframework.bean.LoginBean;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.oper.manager.Constants;

import java.util.List;

/**
 * @author yang
 * @date 20185/4/16
 * 适配器
 */

public class ExampleAdapter extends BaseQuickAdapter<ExampleBean.DataBean, BaseViewHolder> {
    private Context mContext;

    /**
     * @param layoutResId layouy.xml布局
     * @param data        数据
     * @param context     上下文
     */
    public ExampleAdapter(@LayoutRes int layoutResId, @Nullable List<ExampleBean.DataBean> data, Context context) {
        super(layoutResId, data);
        mContext = context;
    }

    /**
     * 绑定xml布局 显示数据
     * @param baseViewHolder
     * @param data
     */
    @Override
    protected void convert(BaseViewHolder baseViewHolder, ExampleBean.DataBean data) {
        ImageView img_artForumHead = baseViewHolder.getView(R.id.img_institution);




        RequestOptions options = new RequestOptions()
                .placeholder(com.yalantis.ucrop.R.color.ucrop_color_grey)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL);

        Glide.with(mContext)
                .load(data.getPic_info())
                .transition(DrawableTransitionOptions.withCrossFade())
                .apply(options)
                .into(img_artForumHead);

    }


}
