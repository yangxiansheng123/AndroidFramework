package com.example.administrator.oper.bean.finance;

/**
 * @author :created by ${yangpf}
 * 时间:2018/5/24 17
 * 邮箱：xxx@.qq.com 退款记录
 */
public class RefundRecordBean {
    private String personName;
    private String deductions;
    private String financeType;//扣费,提成



    public RefundRecordBean(String personName, String deductions, String financeType) {
        this.personName = personName;
        this.deductions = deductions;
        this.financeType = financeType;
    }


    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getDeductions() {
        return deductions;
    }

    public void setDeductions(String deductions) {
        this.deductions = deductions;
    }

    public String getFinanceType() {
        return financeType;
    }

    public void setFinanceType(String financeType) {
        this.financeType = financeType;
    }
}
