package com.example.administrator.oper.bean.daily;

/**
 * @author :created by ${yangpf}
 * 时间:2018/5/14 11
 * 邮箱：xxx@.qq.com
 * 续约提醒
 */
public class RenewRemindingBean {
    private String sa;
    private boolean isChecked;

    public RenewRemindingBean(String sa) {
        this.sa = sa;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }
}
