package com.example.administrator.oper.ui.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

/**
 * Created by yangpf
 * date:2018/1/18
 * des:艺魔方拨打电话工具类
 */

public class CallPhoneUtils {

    /**
     * Android直接拨打电话
     */

    public static void actionCall(Context context, String phoneNumber) {

        Intent dialIntent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phoneNumber));//直接拨打电话
        context.startActivity(dialIntent);
    }

    /**
     * Android跳转到拨号界面
     */

    public static void actionCallButton(Context context, String phoneNumber) {

        Intent dialIntent = new Intent(Intent.ACTION_CALL_BUTTON);//跳转到拨号界面
        context.startActivity(dialIntent);
    }

    /**
     * 跳转到拨号界面，同时传递电话号码
     */

    public static void actionDial(Context context, String phoneNumber) {


        Intent dialIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phoneNumber));//

        context.startActivity(dialIntent);
    }


}
