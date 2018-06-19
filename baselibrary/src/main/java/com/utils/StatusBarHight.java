package com.utils;

import android.content.Context;
import android.os.Build;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.base.BaseToolbarActivity;

/**
 * @author :created by ${yangpf}
 * 时间:2018/4/17 15
 * 邮箱：xxx@.qq.com
 * 处理头部与底部控件布局
 */
public class StatusBarHight {
    /**
     * 填充状态栏高度
     *
     * @param toptop xml头部布局中的一个控件
     */
    private void statusBarHight(Context context, LinearLayout toptop, BaseToolbarActivity baseToolbarActivity) {
        //android4.3以上的沉浸式 ，4.3以下没效果，所以不要头部填充状态栏高度
        int sysVersion = Build.VERSION.SDK_INT;
        if (sysVersion > 18) {
            int result = 0;
            int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
            if (resourceId > 0) {
                result = context.getResources().getDimensionPixelSize(resourceId);
            }
            RelativeLayout.LayoutParams para = new RelativeLayout.LayoutParams(baseToolbarActivity.getWindowManager().getDefaultDisplay().getWidth(), result);
            toptop.setLayoutParams(para); //设置修改后的布局。

        }
    }

    /**
     * 底部如果有静态栏，往上托
     *
     * @param mainBottom 底部布局控件
     */
    private void bottomStatusBar(RelativeLayout mainBottom, Context context) {
        //设置屏幕点击高度
        int totalHeight = GetScreenSize.getDpi(context);
        int contentHeight = GetScreenSize.getScreenHeight(context);
        int bottom = totalHeight - contentHeight;
        if (bottom > 0) {
            RelativeLayout.LayoutParams rh = (RelativeLayout.LayoutParams) mainBottom.getLayoutParams();
            rh.height = bottom;
            mainBottom.setLayoutParams(rh);
        }
    }
}
