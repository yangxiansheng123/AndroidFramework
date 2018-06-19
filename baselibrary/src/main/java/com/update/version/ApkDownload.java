package com.update.version;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.util.Log;


import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.FileCallBack;

import java.io.File;

import okhttp3.Call;

/**
 * @author yang
 * @date 2017/3/23
 */

public class ApkDownload {
    private HasFinish hasFinish;
    /**
     * 文件下载进度
     */
    private int currentProgress = 0;

    private NotificationManager manager;

    private Context context;
    private Intent intent;
    private int img;
    private String contenTitle;

    private String Ticker;


    /**
     * 构造
     *
     * @param context
     * @param intent
     * @param img         通知栏图片
     * @param contenTitle 通知栏标题
     * @param Ticker
     */
    public ApkDownload(Context context, Intent intent, int img, String contenTitle, String Ticker) {
        this.context = context;
        this.intent = intent;
        this.img = img;
        this.contenTitle = contenTitle;
        this.Ticker = Ticker;
    }

    public void setHashFish(HasFinish hasFinish) {
        this.hasFinish = hasFinish;
    }


    private NotificationManager getManager() {
        if (manager == null) {
            manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        }
        return manager;
    }

    ;


    /**
     * 显示通知栏显示当前进度
     *
     * @param progress 进度 0 - 100
     */
    private void showNotificationProgress(int progress) {
        Notification notification = getNotifiction(progress);
        getManager().notify(0, notification);
    }

    /**
     * 获取带进度条的通知栏
     *
     * @param progress 当前进度
     * @return
     */
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private Notification getNotifiction(int progress) {
        String message = "当前下载进度: " + progress + "%";
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        Notification notification = new Notification.Builder(context)
                //App小的图标
                .setSmallIcon(img)
                //设置通知的信息
                .setContentTitle(contenTitle)
                .setContentIntent(pendingIntent)
                .setWhen(System.currentTimeMillis())
                .setContentText(message)
                //用户点击后自动删除
                .setAutoCancel(true)
                //设置优先级
                .setPriority(Notification.PRIORITY_MAX)
                .setOngoing(true)
                //AllProgress最大进度 //progress 当前进度
                .setProgress(100, progress, false)
                .setTicker(Ticker)
                .build();
        notification.flags = Notification.FLAG_AUTO_CANCEL;
        return notification;
    }

    ;

    /**
     * 通过文件地址下载单个文件
     *
     * @param url      下载地址
     * @param fileName 保存的文件名
     */
    public void StartDownLoadFile(final String url, final String fileName) {
        OkHttpUtils
                .get()
                .url(url)
                .build()
                // Environment.getExternalStorageDirectory().getAbsolutePath() 存储路径
                .execute(new FileCallBack(Environment.getExternalStorageDirectory().getAbsolutePath(), fileName) {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("s", "onError :" + e.getMessage());
                    }

                    @Override
                    public void onResponse(File response, int id) {
                        //Log.e(TAG, "onResponse() 当前线程 == " + Thread.currentThread().getName());
                        installApk(fileName);
                        //杀掉进程中的通知栏
                        getManager().cancel(0);
                        hasFinish.finish();
                    }

                    @Override
                    public void inProgress(final float progress, long total, int id) {
                        super.inProgress(progress, total, id);
                        //Log.e(TAG, "inProgress() 当前线程 == " + Thread.currentThread().getName());
                        int pro = (int) (100 * progress);
                        //解决pro进度重复传递 progress的问题 这里解决UI界面卡顿问题
                        if (currentProgress < pro && pro <= 100) {
                            currentProgress = pro;
                            showNotificationProgress(currentProgress);
                        }
                    }
                });
    }

    /**
     * 安装apk
     */
    private void installApk(String apk_name) {
        //Environment.getExternalStorageDirectory() 保存的路径
        Log.e("d", "installApk运行了");

        File fileLocation = new File(Environment.getExternalStorageDirectory(), apk_name);
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.addCategory("android.intent.category.DEFAULT");
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setDataAndType(Uri.fromFile(fileLocation), "application/vnd.android.package-archive");
        context.startActivity(intent);


    }

    public interface HasFinish {
        /**
         * 扩展接口
         */
        public void finish();
    }
}
