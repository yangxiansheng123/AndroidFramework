package com.example.administrator.oper.ui.activity.fiance;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.administrator.oper.R;
import com.example.administrator.oper.adapter.finance.PersonalRefundAdapter;
import com.example.administrator.oper.bean.finance.RefundRecordBean;
import com.example.administrator.oper.ui.activity.daily.ChoiceFollowUpMethodActivity;
import com.example.administrator.oper.ui.widget.MyListView;
import com.utils.SpanUtils;
import com.utils.T;

import java.util.ArrayList;
import java.util.List;

/**
 * 版权：艺魔方公司 版权所有
 *
 * @author yang
 * 版本：1.0
 * 创建日期：2018/5/9
 * 描述：PersonaRefundActivity 个人退款详情
 */
public class PersonaRefundActivity extends AppCompatActivity implements View.OnClickListener, PersonalRefundAdapter.RefundRecordEvent {

    protected TextView mToolbarTitle;
    protected Toolbar mToolbar;
    protected TextView tvRefundName;
    protected EditText etRefundReason;
    protected TextView tvRefundContract;
    protected TextView tvRefundIncome;
    protected TextView tvRefundSpareTime;
    protected TextView tvRefundAmount;
    protected TextView tvRefundGiveClass;
    protected TextView tvRefundGiveAmount;
    protected EditText etClassRefund;
    protected EditText etRefundDeductions;
    protected EditText etAmountBreachOfContract;
    protected TextView tvTotalAmount;
    protected TextView tvSubmitRefund;
    protected TextView tvShortcutInput;
    protected ImageView mImgLiabilityDeduction;
    protected MyListView mLvLiabilityDeduction;
    private String refundReason;
    private PersonalRefundAdapter mRefundAdapter;
    private List<RefundRecordBean> listData = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_persona_refund);
        initView();
        initData();
    }


    private void initView() {
        mRefundAdapter = new PersonalRefundAdapter(this, this);
        mToolbarTitle = (TextView) findViewById(R.id.toolbar_title);
        mToolbarTitle.setText(getString(R.string.title_activity_persona_refund));
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setNavigationIcon(R.mipmap.left_back);
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        tvRefundName = (TextView) findViewById(R.id.tv_refundName);
        tvShortcutInput = (TextView) findViewById(R.id.tv_shortcutInput);
        tvShortcutInput.setOnClickListener(PersonaRefundActivity.this);
        etRefundReason = (EditText) findViewById(R.id.et_refundReason);
        tvRefundContract = (TextView) findViewById(R.id.tv_refundContract);
        tvRefundIncome = (TextView) findViewById(R.id.tv_refundIncome);
        tvRefundSpareTime = (TextView) findViewById(R.id.tv_refundSpareTime);
        tvRefundAmount = (TextView) findViewById(R.id.tv_refundAmount);
        tvRefundGiveClass = (TextView) findViewById(R.id.tv_refundGiveClass);
        tvRefundGiveAmount = (TextView) findViewById(R.id.tv_refundGiveAmount);
        etClassRefund = (EditText) findViewById(R.id.et_classRefund);
        etRefundDeductions = (EditText) findViewById(R.id.et_refundDeductions);
        etAmountBreachOfContract = (EditText) findViewById(R.id.et_amountBreachOfContract);
        tvTotalAmount = (TextView) findViewById(R.id.tv_totalAmount);
        tvSubmitRefund = (TextView) findViewById(R.id.tv_submitRefund);
        tvSubmitRefund.setOnClickListener(PersonaRefundActivity.this);
        mImgLiabilityDeduction = (ImageView) findViewById(R.id.img_liabilityDeduction);
        mImgLiabilityDeduction.setOnClickListener(PersonaRefundActivity.this);
        mLvLiabilityDeduction = (MyListView) findViewById(R.id.lv_liabilityDeduction);
//
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent();
        refundReason = etRefundReason.getText().toString();
        if (view.getId() == R.id.tv_submitRefund) {

            if (TextUtils.isEmpty(refundReason)) {
                T.show(this, "请输入退款原因", 100);
                return;
            }
        } else if (view.getId() == R.id.tv_shortcutInput) {
            intent.setClass(PersonaRefundActivity.this, ChoiceFollowUpMethodActivity.class);
            intent.putExtra("title", "选择跟进方式");
            startActivityForResult(intent, 2002);

        } else if (view.getId() == R.id.img_liabilityDeduction) {
            listData.add(new RefundRecordBean("请选择", "300", "扣费"));
            mRefundAdapter.setListData(listData);
            mLvLiabilityDeduction.setAdapter(mRefundAdapter);
            mRefundAdapter.notifyDataSetChanged();


        }
    }

    /**
     * 初始化数据
     */
    private void initData() {
        tvRefundName.setText("张晓涵");
        tvRefundContract.setText("音乐初级96节");
        tvRefundIncome.setText("6600元");
        tvRefundSpareTime.setText("剩余课时：30节/3000元");
        tvRefundAmount.setText(new SpanUtils()
                .append("可退")
                .setForegroundColor(Color.parseColor("#333333"))
                .setFontSize(48)
                .appendSpace(30)
                .append("0")
                .setForegroundColor(Color.parseColor("#a5a5a5"))
                .setFontSize(48)
                .appendSpace(30)
                .append("元")
                .setForegroundColor(Color.parseColor("#333333"))
                .setFontSize(48)
                .create());
        tvRefundGiveClass.setText("赠送课时：0节/0元");
        tvRefundGiveAmount.setText(new SpanUtils()
                .append("可退")
                .setForegroundColor(Color.parseColor("#333333"))
                .setFontSize(48)
                .appendSpace(30)
                .append("123")
                .setForegroundColor(Color.parseColor("#a5a5a5"))
                .setFontSize(48)
                .appendSpace(30)
                .append("元")
                .setForegroundColor(Color.parseColor("#333333"))
                .setFontSize(48)
                .create());
        etClassRefund.setText("3000.00");
        etRefundDeductions.setText("30300.00");
        etAmountBreachOfContract.setText("400.00");
        tvTotalAmount.setText("2600.00");
    }


    @Override
    public void deleteItem(List<RefundRecordBean> listData, int position) {

        listData.remove(position);
        mRefundAdapter.notifyDataSetChanged();
    }

    @Override
    public void selectPerson(List<RefundRecordBean> listData, int position) {

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 2002) {
            if (resultCode == 2100) {
                String type = data.getStringExtra("type");
                //来源
                refundReason = type;
                etRefundReason.setText(refundReason);
            }
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        T.hideToast();
    }
}
