package com.example.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

/**
 * @author :created by ${yangpf}
 * 时间:2018/4/26 15
 * 邮箱：xxx@.qq.com
 * alertDialog弹窗
 */
public class AlertDialogUtils {
    private static DialogCommit mDialogCommit;

    public void setDialogCommit(DialogCommit dialogCommit) {;
    }

    public static void showDialog(Context context, String title
            , String content, String commit, String cancel,DialogCommit dialogCommit) {
        mDialogCommit = dialogCommit;

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title)
                .setMessage(content)
                .setPositiveButton(commit, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mDialogCommit.commit();
                    }
                })
                .setNegativeButton(cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .show();
    }

    public interface DialogCommit{
        public void commit();
    }
}
