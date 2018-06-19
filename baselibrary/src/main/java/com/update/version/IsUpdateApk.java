package com.update.version;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.v7.app.AlertDialog;

import com.utils.AppUtils;

/**
 *
 * @author yang
 * @date 2017/3/23
 */

public class IsUpdateApk {

    private Context context;

    private int curVersionCode;

    public  IsUpdateApk(Context context){
        this.context = context;
    }

    /**
     * 版本更新
     * @param NewVersionCode  服务器最新版本号
     * @param down_url  下载地址
     * @param title  通知栏标题
     * @param img   通知栏图标
     */
    public void isupdate(int NewVersionCode, final String down_url, final String title, final int img){
        getCurrentVersion();
        if(curVersionCode< NewVersionCode){
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("软件版本更新");
            builder.setMessage("点击确定可立即更新");
            builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    Intent intents = new Intent();
                    ApkDownload okhttpDownload = new ApkDownload(context,intents,img,title,title);
                    okhttpDownload.StartDownLoadFile(down_url,"fenji.apk");


                }
            });
            builder.setNegativeButton("以后再说", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            builder.create().show();
        }else{
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("系统提示");
            builder.setMessage("当前已经为最新版本");
            builder.show();
        }

    }

    /**
     * 获取当前客户端版本信息
     */
    private void getCurrentVersion() {
        curVersionCode = AppUtils.getAppVersionCode(context);
    }
}
