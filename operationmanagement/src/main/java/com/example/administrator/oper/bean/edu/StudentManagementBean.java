package com.example.administrator.oper.bean.edu;

/**
 * @author :created by ${yangpf}
 * 时间:2018/5/14 11
 * 邮箱：xxx@.qq.com
 * 学员管理列表
 */
public class StudentManagementBean {
    private String sa;
    public boolean isSelect = false;


    public StudentManagementBean(String sa) {
        this.sa = sa;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }
}
