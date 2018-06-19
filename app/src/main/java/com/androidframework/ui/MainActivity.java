package com.androidframework.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;
import com.androidframework.R;
import com.base.BaseActivity;
import com.example.administrator.oper.MainOperActivity;
import com.utils.T;


/**
 * @author yang
 * @date 2018/4/12
 */
public class MainActivity extends BaseActivity implements View.OnClickListener {


    protected TextView tvOperation;
    protected TextView tvBusiness;
    protected TextView tvBusinessschool;
    private long firstTime = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_main);
        initView();
    }


    /**
     * 监听手机物理键（返回）
     */
    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            long secondTime = System.currentTimeMillis();
            //如果两次按键时间间隔大于2秒，则不退出
            if (secondTime - firstTime > 2000) {
                T.showShort(MainActivity.this, "再按一次退出程序");
                //更新firstTime
                firstTime = secondTime;
                return true;
            } else {
                finish();
                System.exit(0);
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * 检查版本更新
     */
    private void updateVersion() {
//        mProgressDialog = ProgressDialog.show(this, null, getString(R.string.dialog));
//        mProgressDialog.setCanceledOnTouchOutside(true);

//        UpdateVersionBean data = new UpdateVersionBean();
//        data.setVersion_code(AppUtils.getAppVersionCode(this) + "");
//        data.setPlatform("android");
//        data.setToken("token");
//        Gson gson = new Gson();
//        String dataUpdate = gson.toJson(data);
//        MYManager.getInstance().updateVersion(dataUpdate, getSubscriber(Constants.UPDATEVERSION));
//        Log.e("tttttttttttttttt", token);

//        IsUpdateApk isUpdateApk = new IsUpdateApk(MainOperActivity.this);
//        isUpdateApk.isupdate(Integer.parseInt("updateVeersion.getVersion_code()"),
//                "updateVeersion.getDownload_path()",
//                "版本更新",
//                R.mipmap.ic_launcher);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent();
        if (view.getId() == R.id.tv_operation) {
            intent.setClass(MainActivity.this, MainOperActivity.class);
            startActivity(intent);

        } else if (view.getId() == R.id.tv_business) {

            T.show(this, "开发中", 100);
        } else if (view.getId() == R.id.tv_businessschool) {

            T.show(this, "开发中", 100);
        }
    }
    private void initView() {
        tvOperation = (TextView) findViewById(R.id.tv_operation);
        tvOperation.setOnClickListener(MainActivity.this);
        tvBusiness = (TextView) findViewById(R.id.tv_business);
        tvBusiness.setOnClickListener(MainActivity.this);
        tvBusinessschool = (TextView) findViewById(R.id.tv_businessschool);
        tvBusinessschool.setOnClickListener(MainActivity.this);
    }
}
