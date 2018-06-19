package com.example.administrator.oper.ui.activity.edu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
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
 * 创建日期：2018/5/15
 * 描述：ContractPurchaseCourseActivity 签约购课
 */
public class ContractPurchaseCourseActivity extends BaseActivity implements View.OnClickListener {

    protected TextView mToolbarTitle;
    protected Toolbar mToolbar;
    protected TextView tvContractMember;
    protected TextView tvProductContract;
    protected LinearLayout llProductContract;
    protected TextView tvComplete;
    private String contractCategory;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_contract_purchase_course);
        initView();
    }

    private void initView() {
        mToolbarTitle = (TextView) findViewById(R.id.toolbar_title);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbarTitle.setText(getString(R.string.title_activity_contract_purchase_course));
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setNavigationIcon(R.mipmap.left_back);
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        tvContractMember = (TextView) findViewById(R.id.tv_contractMember);
        tvProductContract = (TextView) findViewById(R.id.tv_productContract);
        llProductContract = (LinearLayout) findViewById(R.id.ll_productContract);
        llProductContract.setOnClickListener(ContractPurchaseCourseActivity.this);
        tvComplete = (TextView) findViewById(R.id.tv_complete);
        tvComplete.setOnClickListener(ContractPurchaseCourseActivity.this);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent();
        if (view.getId() == R.id.ll_productContract) {
            intent.setClass(this, CotractPackageSelectCategoryActivity.class);
            startActivityForResult(intent,7000);
        } else if (view.getId() == R.id.tv_complete) {

            if (TextUtils.isEmpty(contractCategory)){
                T.show(this,"请选择合同",100);
                return;
            }
            finish();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 7000) {
            if (resultCode == 5100) {
                contractCategory = data.getStringExtra("type");
                //
                tvProductContract.setText(contractCategory);
            }
        }
    }
}
