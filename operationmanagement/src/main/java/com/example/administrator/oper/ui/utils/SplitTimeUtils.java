package com.example.administrator.oper.ui.utils;

/**
 * @author :created by ${yangpf}
 * 时间:2018/5/11 10
 * 邮箱：xxx@.qq.com
 */
public class SplitTimeUtils {
    /**
     * 获得年
     *
     * @return
     */
    public static String getYear(String time) {
        return time.split("-")[0];
    }

    /**
     * 获得月
     *
     * @return
     */
    public static String getMonth(String time) {
        return time.split("-")[1];
    }


    /**
     * 获得日
     *
     * @return
     */
    public static String getDay(String time) {
        return time.split("-")[2];
    }


    /**
     * 获得年月
     *
     * @return
     */
    public static String getYandM(String time) {
        String[] times = time.split("-");
        return times[0] + "-" + times[1];
    }
}
