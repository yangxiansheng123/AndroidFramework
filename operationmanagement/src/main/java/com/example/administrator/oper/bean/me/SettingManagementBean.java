package com.example.administrator.oper.bean.me;

/**
 * @author :created by ${yangpf}
 * 时间:2018/5/14 11
 * 邮箱：xxx@.qq.com
 * 权限列表
 */
public class SettingManagementBean {
    private String sa;
    public boolean isSelect;

    public SettingManagementBean(String sa) {
        this.sa = sa;
    }

    protected String getSa() {
        return sa;
    }

    protected void setSa(String sa) {
        this.sa = sa;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }
}
