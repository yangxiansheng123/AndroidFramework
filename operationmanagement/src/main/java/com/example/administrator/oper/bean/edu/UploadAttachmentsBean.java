package com.example.administrator.oper.bean.edu;

/**
 * @author :created by ${yangpf}
 * 时间:2018/5/16 10
 * 邮箱：xxx@.qq.com
 * 上传附件
 */
public class UploadAttachmentsBean {

    public UploadAttachmentsBean(String title, String fileType) {
        Title = title;
        this.fileType = fileType;
    }

    public String Title;
    public String fileType;

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }
}
