package com.example.administrator.oper.ui.activity.fiance;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import com.base.BaseActivity;
import com.example.administrator.oper.R;
import com.utils.T;

/**
 * 版权：艺魔方公司 版权所有
 *
 * @author yang
 * 版本：1.0
 * 创建日期：2018/5/24
 * 描述：RecordOfExpenditureActivity 支出记录
 */
public class RecordOfExpenditureActivity extends BaseActivity implements View.OnClickListener {

    protected TextView mToolbarTitle;
    protected Toolbar mToolbar;
    protected TextView tvFixedCost;
    protected TextView tvElectricityFees;
    protected TextView tvExpienditureAmount;
    protected EditText etExpenditureRemark;
    protected TextView tvRecordTime;
    protected TextView tvComplete;
    private String remark;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_record_of_expenditure);
        initView();
    }

    /**
     * 初始化控件
     */
    private void initView() {
        mToolbarTitle = (TextView) findViewById(R.id.toolbar_title);
        mToolbarTitle.setText(getString(R.string.title_activity_record_of_expenditure));
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setNavigationIcon(R.mipmap.left_back);
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        tvFixedCost = (TextView) findViewById(R.id.tv_fixedCost);
        tvElectricityFees = (TextView) findViewById(R.id.tv_electricityFees);
        tvExpienditureAmount = (TextView) findViewById(R.id.tv_expienditureAmount);
        etExpenditureRemark = (EditText) findViewById(R.id.et_expenditureRemark);
        tvRecordTime = (TextView) findViewById(R.id.tv_recordTime);
        tvComplete = (TextView) findViewById(R.id.tv_complete);
        tvComplete.setOnClickListener(RecordOfExpenditureActivity.this);
    }

    @Override
    public void onClick(View view) {
       remark =  etExpenditureRemark.getText().toString();
        if (view.getId() == R.id.tv_complete) {

            if (TextUtils.isEmpty(remark)){
                T.show(RecordOfExpenditureActivity.this,"请添加备注",100);
                return;
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        T.hideToast();
    }
}
