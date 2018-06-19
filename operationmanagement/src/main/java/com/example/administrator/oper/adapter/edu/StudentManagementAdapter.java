package com.example.administrator.oper.adapter.edu;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.*;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.oper.R;
import com.example.administrator.oper.bean.edu.StudentManagementBean;
import com.example.administrator.oper.manager.Constants;
import de.hdodenhof.circleimageview.CircleImageView;

import java.util.List;

/**
 * @author yang
 * @date 20185/4/16
 * 适配器 学员管理
 */

public class StudentManagementAdapter extends BaseQuickAdapter<StudentManagementBean, BaseViewHolder> {
    private Context mContext;
    List<StudentManagementBean> listData;
    private FrameLayout frameLayout_bg;

    /**
     * @param layoutResId layouy.xml布局
     * @param data        数据
     * @param context     上下文
     */
    public StudentManagementAdapter(@LayoutRes int layoutResId, @Nullable List<StudentManagementBean> data, Context context) {
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
    protected void convert(BaseViewHolder baseViewHolder, StudentManagementBean data) {
        CircleImageView img_studentImg = baseViewHolder.getView(R.id.img_studentImg);
        TextView tv_studentName = baseViewHolder.getView(R.id.tv_studentName);
        TextView tv_studentAccount = baseViewHolder.getView(R.id.tv_studentAccount);
        TextView tv_studentDtate = baseViewHolder.getView(R.id.tv_studentDtate);
        TextView tv_studentParrent = baseViewHolder.getView(R.id.tv_studentParrent);
        frameLayout_bg = baseViewHolder.getView(R.id.frameLayout_bg);

        tv_studentName.setText("苏大牙");
        tv_studentAccount.setText("15858533");
        tv_studentDtate.setText("已约课");
        tv_studentParrent.setText("家长 苏亚 ");





        RequestOptions options = new RequestOptions()
                .placeholder(com.yalantis.ucrop.R.color.ucrop_color_grey)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL);

        Glide.with(mContext)
                .load(Constants.URL)
//                .transition(DrawableTransitionOptions.withCrossFade())
//                .apply(options)
                .into(img_studentImg);
    }




    @Override
    public void onBindViewHolder(BaseViewHolder holder, int positions) {
        super.onBindViewHolder(holder, positions);
      if (listData.size() == positions) {
            frameLayout_bg.setBackgroundResource(R.drawable.solid_shape_white_bootom_lright);
        }
    }

}
