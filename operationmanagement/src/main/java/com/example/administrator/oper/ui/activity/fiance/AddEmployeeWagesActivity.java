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
 * 创建日期：2018/5/10
 * 描述：AddEmployeeWagesActivity 添加员工工资
 */
public class AddEmployeeWagesActivity extends BaseActivity implements View.OnClickListener {

    protected TextView mToolbarTitle;
    protected Toolbar mToolbar;
    protected TextView mTvTextRight;
    protected TextView tvEmployeeName;
    protected EditText tvFixedSalary;
    private String employeeName, fixedSalary;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_add_employee_wages);
        initView();
    }

    /**
     * 完成
     *
     * @param view
     */
    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.tv_text_right) {
            if (TextUtils.isEmpty(employeeName)) {
                T.show(AddEmployeeWagesActivity.this, "请选择员工", 100);
                return;
            }
            if (TextUtils.isEmpty(fixedSalary)) {
                T.show(AddEmployeeWagesActivity.this, "请输入固定工资", 100);
                return;
            }
            finish();
        }
    }

    private void initView() {
        mToolbarTitle = (TextView) findViewById(R.id.toolbar_title);
        mToolbarTitle.setText(getString(R.string.title_activity_add_employee_wages));
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setNavigationIcon(R.mipmap.left_back);
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        mTvTextRight = (TextView) findViewById(R.id.tv_text_right);
        mTvTextRight.setOnClickListener(AddEmployeeWagesActivity.this);
        tvEmployeeName = (TextView) findViewById(R.id.tv_employeeName);
        tvFixedSalary = (EditText) findViewById(R.id.tv_fixedSalary);
        employeeName = tvEmployeeName.getText().toString();
        fixedSalary = tvFixedSalary.getText().toString();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        T.hideToast();
    }
}
