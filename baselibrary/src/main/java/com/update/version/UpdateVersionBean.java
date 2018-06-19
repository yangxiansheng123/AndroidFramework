package com.update.version;

import com.google.gson.annotations.SerializedName;

/**
 *
 * @author yang
 * @date 2017/2/20
 * 版本更新
 */

public class UpdateVersionBean {
    private String version_code;//版本号
    private String platform;//客户端平台（android、ios）
    private String code;//返回码
    private String message;//返回结果描述
    private String version_name;//版本名称
    private String download_path;//下载地址
    private String version_desc;//版本更新描述
    private String update_time;//版本更新时间
    private String token;//

    private String upload_time;
    private String md5_code;


    public String getVersion_code() {
        return version_code;
    }

    public void setVersion_code(String version_code) {
        this.version_code = version_code;
    }


    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getVersion_name() {
        return version_name;
    }

    public void setVersion_name(String version_name) {
        this.version_name = version_name;
    }

    public String getDownload_path() {
        return download_path;
    }

    public void setDownload_path(String download_path) {
        this.download_path = download_path;
    }

    public String getVersion_desc() {
        return version_desc;
    }

    public void setVersion_desc(String version_desc) {
        this.version_desc = version_desc;
    }

    public String getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(String update_time) {
        this.update_time = update_time;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUpload_time() {
        return upload_time;
    }

    public void setUpload_time(String upload_time) {
        this.upload_time = upload_time;
    }

    public String getMd5_code() {
        return md5_code;
    }

    public void setMd5_code(String md5_code) {
        this.md5_code = md5_code;
    }
}
