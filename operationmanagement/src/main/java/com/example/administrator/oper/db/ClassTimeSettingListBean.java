package com.example.administrator.oper.db;

import java.util.List;

/**
 * @author :created by ${yangpf}
 * 时间:2018/5/16 10
 * 邮箱：xxx@.qq.com
 * 上课时间设置
 */
public class ClassTimeSettingListBean {

    public List<ClassTimeSettingBean> alarm;

    public List<ClassTimeSettingBean> getAlarm() {
        return alarm;
    }

    public void setAlarm(List<ClassTimeSettingBean> alarm) {
        this.alarm = alarm;
    }
}
