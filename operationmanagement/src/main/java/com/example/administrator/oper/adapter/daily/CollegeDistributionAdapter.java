package com.example.administrator.oper.adapter.daily;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.View;
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
import com.example.administrator.oper.bean.edu.StudentManagementBean;
import com.example.administrator.oper.manager.Constants;
import de.hdodenhof.circleimageview.CircleImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yang
 * @date 20185/4/16
 * 适配器 学员分配-->家长分配
 */

public class CollegeDistributionAdapter extends BaseQuickAdapter<StudentManagementBean, BaseViewHolder> {
    private Context mContext;
    List<StudentManagementBean> listData;
    private FrameLayout fl_collegeDisBg;
    private ICollegeDistribution mCollegeDistribution;
    private static final int MYLIVE_MODE_CHECK = 0;
    int mEditMode = MYLIVE_MODE_CHECK;
    private List<StudentManagementBean> mMyLiveList;


    public List<StudentManagementBean> getMyLiveList() {
        if (mMyLiveList == null) {
            mMyLiveList = new ArrayList<>();
        }
        return mMyLiveList;
    }


    /**
     * @param layoutResId layouy.xml布局
     * @param data        数据
     * @param context     上下文
     */
    public CollegeDistributionAdapter(@LayoutRes int layoutResId, @Nullable List<StudentManagementBean> data, Context context, ICollegeDistribution collegeDistribution) {
        super(layoutResId, data);
        mContext = context;
        listData = data;
        mCollegeDistribution = collegeDistribution;
    }

    /**
     * 绑定xml布局 显示数据
     *
     * @param baseViewHolder
     * @param data
     */
    @Override
    protected void convert(BaseViewHolder baseViewHolder, StudentManagementBean data) {
        CircleImageView img_head = baseViewHolder.getView(R.id.img_head);
        TextView tv_renewRemindName = baseViewHolder.getView(R.id.tv_renewRemindName);
        TextView tv_renewRemindClassHour = baseViewHolder.getView(R.id.tv_renewRemindClassHour);
        fl_collegeDisBg = baseViewHolder.getView(R.id.fl_collegeDisBg);
        ImageView check_renewRemind = baseViewHolder.getView(R.id.check_renewRemind);


        tv_renewRemindName.setText("苏大牙");
        tv_renewRemindClassHour.setText("15858533");



        RequestOptions options = new RequestOptions()
                .placeholder(com.yalantis.ucrop.R.color.ucrop_color_grey)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL);

        Glide.with(mContext)
                .load(Constants.URL)
//                .transition(DrawableTransitionOptions.withCrossFade())
                .apply(options)
                .into(img_head);

        if (data.isSelect()) {
            check_renewRemind.setImageResource(R.mipmap.checked_true);
        } else {
            check_renewRemind.setImageResource(R.mipmap.un_checked);
        }
    }


    @Override
    public void onBindViewHolder(BaseViewHolder holder, int positions) {
        super.onBindViewHolder(holder, positions);
        if (0 == positions) {
            fl_collegeDisBg.setBackgroundResource(R.drawable.solid_shape_white_top_left_right);
        } else if (listData.size() - 1 == positions) {
            fl_collegeDisBg.setBackgroundResource(R.drawable.solid_shape_white_bootom_lright);
        }
    }

    public interface ICollegeDistribution {
        public void chexbBoxView(ImageView chexb_browseCourse);//

    }
}
