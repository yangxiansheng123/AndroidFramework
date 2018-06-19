package com.example.administrator.oper.ui.widget;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

/**
 * @author :created by ${yangpf}
 * 时间:2018/5/17 11
 * 邮箱：xxx@.qq.com
 */
public class SetListviewHeight {
    /**
     * 计算listview的高度
     * @param listView
     * @param adapter
     */
    public static void setListViewHeightBasedOnChildren(ListView listView, BaseAdapter adapter) {
        if (listView == null) {
            return;
        }
        adapter = (BaseAdapter) listView.getAdapter();
        if (adapter == null) {
            return;
        }
        int totalHeight = 0;
        for (int i = 0; i < adapter.getCount(); i++) {
            View listItem = adapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (adapter.getCount() - 1));
        listView.setLayoutParams(params);
    }
}
