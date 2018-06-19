package com.example.administrator.oper.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import com.example.administrator.oper.R;
import com.example.dialog.AlertDialogUtils;
import com.example.dialog.BottomDialog;
import com.example.dialog.EditextInputCenterDialog;

/**
 * 所有功能实例测试界面
 *
 * @author yang
 */
public class ExampleActivity extends AppCompatActivity {

    protected TextView textView;
    protected TextView textView2;
    protected TextView textView3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_example);
        initView();

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditextInputCenterDialog dialog = new EditextInputCenterDialog.Builder(ExampleActivity.this)
                        .mTitle("参与者个人信息")
                        .mSubTitle("*获奖后客服人员可及时和您取得联系")
                        .mForumActivitiesEvent(new EditextInputCenterDialog.ButtonClickedEvent() {
                            @Override
                            public void btnNextClicked(String name, String phnoe) {

                            }
                        })
                        .create();
                dialog.show();
            }
        });

        textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BottomDialog.create(getSupportFragmentManager())
                        .setViewListener(new BottomDialog.ViewListener() {
                            @Override
                            public void bindView(View v) {
                                // // You can do any of the necessary the operation with the view
                                v.findViewById(R.id.img_closeDialog).setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        BottomDialog.create(getSupportFragmentManager()).cancel();
                                    }
                                });
                            }
                        })
                        .setLayoutRes(R.layout.payment_bottom_dialog)
                        .setDimAmount(0.1f)            // Dialog window dim amount(can change window background color）, range：0 to 1，default is : 0.2f
                        .setCancelOutside(false)     // click the external area whether is closed, default is : true
                        .setTag("BottomDialog")     // setting the DialogFragment tag
                        .show();

            }
        });

        textView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialogUtils.showDialog(ExampleActivity.this, "标题", "内容", "确定", "取消", new AlertDialogUtils.DialogCommit() {
                    @Override
                    public void commit() {
                        Log.e("","");
                    }
                });
            }
        });
    }

    /**
     * 初始化控件
     */
    private void initView() {
        textView = (TextView) findViewById(R.id.textView);
        textView2 = (TextView) findViewById(R.id.textView2);
        textView3 = (TextView) findViewById(R.id.textView3);
    }
}
