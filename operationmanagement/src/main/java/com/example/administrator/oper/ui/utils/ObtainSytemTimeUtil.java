package com.example.administrator.oper.ui.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * @author :created by ${yangpf}
 * 时间:2018/6/7 16
 * 邮箱：xxx@.qq.com
 */
public class ObtainSytemTimeUtil {
    private static SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);


    public static String getDateToString(long time) {
        Date d = new Date(time);
        return sf.format(d);
    }
}
