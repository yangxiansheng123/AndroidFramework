package com.utils;

import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

/**
 * @author :created by ${yangpf}
 * 时间:2018/4/28 16
 * 邮箱：xxx@.qq.com
 */
public class ToolbarHelper {
    private Toolbar mToolbar;

    public ToolbarHelper(Toolbar toolbar) {
        this.mToolbar = toolbar;
    }

    public Toolbar getToolbar() {
        return mToolbar;
    }

    public void setTitle(String title) {
        TextView titleTV = (TextView) mToolbar.findViewById(com.zxl.dropdownmenue.R.id.toolbar_title);
        titleTV.setText(title);
    }

    public void setMenuTitle(String menuTitle, View.OnClickListener listener) {
        TextView menuTitleTV = (TextView) mToolbar.findViewById(com.zxl.dropdownmenue.R.id.toolbar_menu_title);
        menuTitleTV.setText(menuTitle);
        menuTitleTV.setOnClickListener(listener);
    }
}
