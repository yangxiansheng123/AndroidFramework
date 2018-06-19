package com.example.administrator.oper.widget;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

/**
 * @author :created by ${yangpf}
 * 时间:2018/5/3 17
 * 邮箱：xxx@.qq.com
 * 格式化x轴数值
 */
public class MyXFormatter implements IAxisValueFormatter {

    private String[] mValues;

    public MyXFormatter(String[] values) {
        this.mValues = values;
    }
    @Override
    public String getFormattedValue(float value, AxisBase axis) {

        return mValues[(int) value % mValues.length];
    }
}
