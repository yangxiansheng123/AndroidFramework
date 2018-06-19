package com.example.administrator.oper.ui.activity.fiance;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.base.BaseActivity;
import com.example.administrator.oper.R;
import com.example.administrator.oper.adapter.finance.ChangeRecordIndexAdapter;
import com.example.administrator.oper.ui.widget.MyListView;

import java.util.ArrayList;
import java.util.List;

/**
 * 版权：艺魔方公司 版权所有
 *
 * @author yang
 * 版本：1.0
 * 创建日期：2018/5/3
 * 描述：PersonalPayrollActivity
 */
public class PersonalPayrollActivity extends BaseActivity implements View.OnClickListener {

    protected MyListView lvChangRecord;
    protected TextView tvName;
    protected TextView tvPhone;
    protected TextView tvEditInfo;
    protected TextView tvMonthSalary;
    protected TextView tvSalary;
    protected TextView tvFixedSalary;
    protected TextView tvExtract;
    protected TextView tvResponsibility;
    protected TextView toolbarTitle;
    protected Toolbar toolbar;
    protected TextView tvTextRight;
    protected TextView tvChangeRecord;
    private ChangeRecordIndexAdapter mRecordIndexAdapter;
    private List<String> listData = new ArrayList<>();
    private LayoutInflater mInflater;
    private View footerView;
    private LinearLayout ll_selectMore;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_payroll);
        for (int i = 0; i < 5; i++) {
            listData.add("绩效");
        }
        initView();
        initData();
    }



    /**
     * 初始化控件
     */
    private void initView() {
        mInflater = LayoutInflater.from(this);
        footerView = mInflater.inflate(R.layout.footview_selectall, null);
        ll_selectMore = (LinearLayout) footerView.findViewById(R.id.ll_selectMore);
        mRecordIndexAdapter = new ChangeRecordIndexAdapter(this);
        lvChangRecord = (MyListView) findViewById(R.id.lv_changRecord);
//        lvChangRecord.addFooterView(footerView);
        mRecordIndexAdapter.setListData(listData);
        lvChangRecord.setAdapter(mRecordIndexAdapter);
        mRecordIndexAdapter.notifyDataSetChanged();

        /**
         * 查看更多变更记录
         */
        ll_selectMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PersonalPayrollActivity.this, HistoryWageRecordActivity.class));
            }
        });
        tvName = (TextView) findViewById(R.id.tv_name);
        tvName.setFocusable(true);
        tvName.setFocusableInTouchMode(true);
        tvPhone = (TextView) findViewById(R.id.tv_phone);
        tvEditInfo = (TextView) findViewById(R.id.tv_editInfo);
        tvMonthSalary = (TextView) findViewById(R.id.tv_monthSalary);
        tvSalary = (TextView) findViewById(R.id.tv_salary);
        tvFixedSalary = (TextView) findViewById(R.id.tv_fixedSalary);
        tvExtract = (TextView) findViewById(R.id.tv_extract);
        tvResponsibility = (TextView) findViewById(R.id.tv_responsibility);
        toolbarTitle = (TextView) findViewById(R.id.toolbar_title);
        toolbarTitle.setText(getString(R.string.title_activity_personal_payroll));
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        tvTextRight = (TextView) findViewById(R.id.tv_text_right);
        tvTextRight.setOnClickListener(PersonalPayrollActivity.this);
        tvTextRight.setText("历史工资");
        tvChangeRecord = (TextView) findViewById(R.id.tv_changeRecord);
        toolbar.setTitle(getString(R.string.title_activity_personal_payroll));
        // 显示导航按钮
        toolbar.setNavigationIcon(R.mipmap.left_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }


    /**
     * 初始化数据
     */
    private void initData() {
        tvName.setText("苏大牙");
        tvPhone.setText("13264685458");
        tvEditInfo.setText("编辑");
        tvMonthSalary.setText("5月工资");
        tvSalary.setText("￥50000元");
        tvFixedSalary.setText("￥50000元");
        tvExtract.setText("￥50000元");
        tvResponsibility.setText("￥50000元");
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.tv_text_right) {
            startActivity(new Intent(PersonalPayrollActivity.this, HistoryWageRecordActivity.class));

        }
    }
}
