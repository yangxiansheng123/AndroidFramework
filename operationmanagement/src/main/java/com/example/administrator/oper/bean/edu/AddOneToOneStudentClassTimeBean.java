package com.example.administrator.oper.bean.edu;

/**
 * @author :created by ${yangpf}
 * 时间:2018/5/14 11
 * 邮箱：xxx@.qq.com
 * 添加一对一学员上课时间
 */
public class AddOneToOneStudentClassTimeBean {
    //上课类型
    private String coruseType;
    //上课时间
    private String classTime;
    //上课教室
    private String classroom;

    public AddOneToOneStudentClassTimeBean(String coruseType, String classTime, String classroom) {
        this.coruseType = coruseType;
        this.classTime = classTime;
        this.classroom = classroom;
    }

    public String getCoruseType() {
        return coruseType;
    }

    public void setCoruseType(String coruseType) {
        this.coruseType = coruseType;
    }

    public String getClassTime() {
        return classTime;
    }

    public void setClassTime(String classTime) {
        this.classTime = classTime;
    }

    public String getClassroom() {
        return classroom;
    }

    public void setClassroom(String classroom) {
        this.classroom = classroom;
    }
}
