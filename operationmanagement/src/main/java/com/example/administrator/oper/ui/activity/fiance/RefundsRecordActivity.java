package com.example.administrator.oper.ui.activity.fiance;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import com.base.BaseActivity;
import com.example.administrator.oper.R;
import com.example.administrator.oper.adapter.finance.RefundRecordAdapter;
import com.example.administrator.oper.adapter.finance.RefundRecordAdapter.RefundRecordEvent;
import com.example.administrator.oper.bean.finance.RefundRecordBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 版权：艺魔方公司 版权所有
 *
 * @author yang
 * 版本：1.0
 * 创建日期：2018/5/24
 * 描述：RefundsRecordActivity 退款记录
 */
public class RefundsRecordActivity extends BaseActivity implements RefundRecordEvent, View.OnClickListener {

    protected TextView mToolbarTitle;
    protected Toolbar mToolbar;
    protected ListView mLvResponsibility;
    protected ImageView imgAddClassTime;
    protected TextView tvComplete;
    protected LinearLayout llRefundRecord;
    protected TextView tvMembersName;
    private RefundRecordAdapter mRecordAdapter;
    private List<RefundRecordBean> listData = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_refunds_record);
        initView();
    }

    /**
     * 初始化控件
     */
    private void initView() {
        mRecordAdapter = new RefundRecordAdapter(this, this);
        mToolbarTitle = (TextView) findViewById(R.id.toolbar_title);
        mToolbarTitle.setText(getString(R.string.title_activity_refunds_record));
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setNavigationIcon(R.mipmap.left_back);
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        mLvResponsibility = (ListView) findViewById(R.id.lv_responsibility);
        imgAddClassTime = (ImageView) findViewById(R.id.img_addClassTime);
        imgAddClassTime.setOnClickListener(RefundsRecordActivity.this);
        tvComplete = (TextView) findViewById(R.id.tv_complete);
        tvComplete.setOnClickListener(RefundsRecordActivity.this);
        llRefundRecord = (LinearLayout) findViewById(R.id.ll_refundRecord);

        setBackg();
        tvMembersName = (TextView) findViewById(R.id.tv_membersName);
    }

    @Override
    public void deleteItem(List<RefundRecordBean> listData, int position) {

        listData.remove(position);
        mRecordAdapter.notifyDataSetChanged();

        setBackg();
    }

    @Override
    public void selectPerson(List<RefundRecordBean> listData, int position) {

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.img_addClassTime) {
            listData.add(new RefundRecordBean("请选择", "300", "扣费"));
            mRecordAdapter.setListData(listData);
            mLvResponsibility.setAdapter(mRecordAdapter);
            mRecordAdapter.notifyDataSetChanged();

            setBackg();

        } else if (view.getId() == R.id.tv_complete) {
        }
    }

    private void setBackg() {
        if (listData.size() > 0) {
            llRefundRecord.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.solid_shape_white_top_left_right));
        } else {
            llRefundRecord.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.solid_shape_white));
        }
    }
}
