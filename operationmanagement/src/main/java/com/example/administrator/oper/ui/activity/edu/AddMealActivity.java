package com.example.administrator.oper.ui.activity.edu;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.base.BaseActivity;
import com.example.administrator.oper.R;
import com.utils.T;

/**
 * 版权：艺魔方公司 版权所有
 *
 * @author yang
 * 版本：1.0
 * 创建日期：2018/5/5
 * 描述：AddMealActivity
 */
public class AddMealActivity extends BaseActivity implements View.OnClickListener {

    protected TextView mToolbarTitle;
    protected Toolbar mToolbar;
    protected EditText etContractName;
    protected EditText etContractClassHour;
    protected TextView tvSelectCategory;
    protected LinearLayout llSelectCategory;
    protected EditText etContractIncreasCh;
    protected EditText etContractPackagePrice;
    protected TextView tvComplete;
    private String contractName, contractClassHour, contractIncreasCh, contractPackagePrice;
    private String contractCategory;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_add_meal);
        initView();
    }

    /**
     * 初始化控件
     */
    private void initView() {
        mToolbarTitle = (TextView) findViewById(R.id.toolbar_title);
        mToolbarTitle.setText(getString(R.string.title_activity_add_meal));
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setNavigationIcon(R.mipmap.left_back);
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        etContractName = (EditText) findViewById(R.id.et_contractName);
        etContractClassHour = (EditText) findViewById(R.id.et_contractClassHour);
        tvSelectCategory = (TextView) findViewById(R.id.tv_selectCategory);
        llSelectCategory = (LinearLayout) findViewById(R.id.ll_selectCategory);
        llSelectCategory.setOnClickListener(AddMealActivity.this);
        etContractIncreasCh = (EditText) findViewById(R.id.et_contractIncreasCh);
        etContractPackagePrice = (EditText) findViewById(R.id.et_contractPackagePrice);
        tvComplete = (TextView) findViewById(R.id.tv_complete);
        tvComplete.setOnClickListener(AddMealActivity.this);

        contractName = etContractName.getText().toString();
        contractClassHour = etContractClassHour.getText().toString();
        contractIncreasCh = etContractIncreasCh.getText().toString();
        contractPackagePrice = etContractPackagePrice.getText().toString();
    }

    /**
     * 处理点击事件
     *
     * @param view
     */
    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.ll_selectCategory) {
            Intent intent = new Intent();
            intent.setClass(this, CotractPackageSelectCategoryActivity.class);
            startActivityForResult(intent, 5000);
        } else if (view.getId() == R.id.tv_complete) {
            if (TextUtils.isEmpty(contractName)) {
                T.show(this, "请输入合同名称", 100);
                return;
            }
            if (TextUtils.isEmpty(contractClassHour)) {
                T.show(this, "请输入合同课时", 100);
                return;
            }
            if (TextUtils.isEmpty(contractCategory)) {
                T.show(this, "请选择合同分类", 100);
                return;
            }
            if (TextUtils.isEmpty(contractIncreasCh)) {
                T.show(this, "请输入增送课时", 100);
                return;
            }
            if (TextUtils.isEmpty(contractPackagePrice)) {
                T.show(this, "请输入套餐价格", 100);
                return;
            }

            finish();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 5000) {
            if (resultCode == 5100) {
                contractCategory = data.getStringExtra("type");
                //
                tvSelectCategory.setText(contractCategory);
                tvSelectCategory.setTextColor(Color.parseColor("#333333"));
            }
        }
    }

    @Override
    public void onCompleted(int what) {
        super.onCompleted(what);
    }

    @Override
    public void onError(Throwable e, int what) {
        super.onError(e, what);
    }

    @Override
    public void onNext(Object object, int what) {
        super.onNext(object, what);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        T.hideToast();
    }
}
