package com.example.administrator.oper.adapter.me;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.oper.R;
import com.example.administrator.oper.bean.edu.ClassManagementBean;
import com.example.administrator.oper.bean.me.SettingManagementBean;
import com.utils.SpanUtils;

import java.util.List;

/**
 * @author yang
 * @date 20185/4/16
 * 适配器 设置管理 --->权限列表
 */

public class SettingManagementAdapter extends BaseQuickAdapter<SettingManagementBean, BaseViewHolder> {
    private Context mContext;
    private static final int MYLIVE_MODE_CHECK = 0;
    int mEditMode = MYLIVE_MODE_CHECK;



    public void setEditMode(int editMode) {
        mEditMode = editMode;
        notifyDataSetChanged();
    }

    /**
     * @param layoutResId layouy.xml布局
     * @param data        数据
     * @param context     上下文
     */
    public SettingManagementAdapter(@LayoutRes int layoutResId, @Nullable List<SettingManagementBean> data, Context context) {
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
    protected void convert(BaseViewHolder baseViewHolder, SettingManagementBean data) {
        TextView tv_shopowner = baseViewHolder.getView(R.id.tv_shopowner);
        TextView tv_jurisdiction = baseViewHolder.getView(R.id.tv_jurisdiction);
        ImageView img_isChecked = baseViewHolder.getView(R.id.img_isChecked);

        tv_shopowner.setText("店长");
        tv_jurisdiction.setText("具有系统操作的所有权限");

        if (mEditMode == MYLIVE_MODE_CHECK) {
            img_isChecked.setVisibility(View.GONE);
        } else {
            img_isChecked.setVisibility(View.VISIBLE);

            if (data.isSelect()) {
                img_isChecked.setImageResource(R.mipmap.checked_true);
            } else {
                img_isChecked.setImageResource(R.mipmap.un_checked);
            }
        }
    }


}
