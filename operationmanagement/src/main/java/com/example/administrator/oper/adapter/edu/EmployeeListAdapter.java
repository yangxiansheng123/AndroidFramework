package com.example.administrator.oper.adapter.edu;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.oper.R;
import com.example.administrator.oper.bean.edu.EmployeeListBean;
import com.example.administrator.oper.bean.edu.StudentManagementBean;
import com.example.administrator.oper.manager.Constants;

import java.util.List;

/**
 * @author yang
 * @date 20185/4/16
 * 适配器 员工列表
 */

public class EmployeeListAdapter extends BaseQuickAdapter<EmployeeListBean, BaseViewHolder> {
    private Context mContext;
    List<EmployeeListBean> listData;
    LinearLayout ll_employeeBg;

    /**
     * @param layoutResId layouy.xml布局
     * @param data        数据
     * @param context     上下文
     */
    public EmployeeListAdapter(@LayoutRes int layoutResId, @Nullable List<EmployeeListBean> data, Context context) {
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
    protected void convert(BaseViewHolder baseViewHolder, EmployeeListBean data) {
        TextView tv_employeePosition = baseViewHolder.getView(R.id.tv_employeePosition);
        TextView tv_employeeName = baseViewHolder.getView(R.id.tv_employeeName);
        TextView tv_employeeAccount = baseViewHolder.getView(R.id.tv_employeeAccount);
        TextView tv_employeeState = baseViewHolder.getView(R.id.tv_employeeState);
        ll_employeeBg = baseViewHolder.getView(R.id.ll_employeeBg);


        tv_employeePosition.setText("店长");
        tv_employeeName.setText("苏大牙");
        tv_employeeAccount.setText("15858533");
        tv_employeeState.setText("正常");


//        Glide.with(mContext)
//                .load(Constants.URL)
//                .centerCrop()
//                .dontAnimate()//防止设置placeholder导致第一次不显示网络图片,只显示默认图片的问题
//                .placeholder(R.mipmap.ic_launcher)
//                .error(R.mipmap.ic_launcher)
//                .into(img_studentImg);

    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int positions) {
        super.onBindViewHolder(holder, positions);
        if (0 == positions) {
            ll_employeeBg.setBackgroundDrawable(ContextCompat.getDrawable(mContext, R.drawable.solid_shape_white_top_left_right));
        } else if (listData.size() - 1 == positions) {
            ll_employeeBg.setBackgroundDrawable(ContextCompat.getDrawable(mContext, R.drawable.solid_shape_white_bootom_lright));
        }
    }
}
