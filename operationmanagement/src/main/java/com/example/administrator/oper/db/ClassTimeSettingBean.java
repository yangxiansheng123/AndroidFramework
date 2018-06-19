package com.example.administrator.oper.db;

/**
 * @author :created by ${yangpf}
 * 时间:2018/5/16 10
 * 邮箱：xxx@.qq.com
 * 上课时间设置
 */
public class ClassTimeSettingBean {
    /**
     * 上课类型   单次课,周一...
     */
    public String ClassTimeType;
    /**
     * 年月日
     */
    public String ClassTimeTimeYear;
    /**
     * 时间段 时分秒
     */
    public String ClassTimeTimeSlot;

    public String getClassTimeType() {
        return ClassTimeType;
    }

    public void setClassTimeType(String classTimeType) {
        ClassTimeType = classTimeType;
    }

    public String getClassTimeTimeYear() {
        return ClassTimeTimeYear;
    }

    public void setClassTimeTimeYear(String classTimeTimeYear) {
        ClassTimeTimeYear = classTimeTimeYear;
    }

    public String getClassTimeTimeSlot() {
        return ClassTimeTimeSlot;
    }

    public void setClassTimeTimeSlot(String classTimeTimeSlot) {
        ClassTimeTimeSlot = classTimeTimeSlot;
    }
}
