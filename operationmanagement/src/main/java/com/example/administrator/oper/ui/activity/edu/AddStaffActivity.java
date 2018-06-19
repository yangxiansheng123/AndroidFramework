package com.example.administrator.oper.ui.activity.edu;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import com.base.BaseActivity;
import com.example.administrator.oper.R;
import com.example.administrator.oper.ui.activity.daily.AddStudentsSelectTypeActivity;
import com.example.administrator.oper.ui.activity.daily.CollegeEntryActivity;
import com.utils.T;
import org.wangchenlong.datescroller.widget.DateScrollerDialog;
import org.wangchenlong.datescroller.widget.data.Type;
import org.wangchenlong.datescroller.widget.listener.OnDateSetListener;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * 版权：艺魔方公司 版权所有
 *
 * @author yang
 * 版本：1.0
 * 创建日期：2018/5/8
 * 描述：AddStaffActivity
 */
public class AddStaffActivity extends BaseActivity implements View.OnClickListener {


    private static final long HUNDRED_YEARS = 100L * 365 * 1000 * 60 * 60 * 24L; // 100年
    private SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
    private long mLastTime = System.currentTimeMillis(); // 上次设置的时间
    private String birthday;

    protected TextView mToolbarTitle;
    protected Toolbar mToolbar;
    protected EditText etStaffName;
    protected EditText etStaffPhone;
    protected TextView tvStaffSex;
    protected TextView tvStaffEntryTime;
    protected TextView tvStaffArriveTime;
    protected TextView tvStaffRole;
    protected TextView tvStaffJurisdiction;
    protected TextView tvStaffState;
    protected TextView tvStaffComplete;
    private String staffName, staffPhone;
    private String istinguishdTime;
    private String entryTime,arriveTime;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_add_staff);
        initView();
    }

    /**
     * 初始化控件
     */
    private void initView() {
        mToolbarTitle = (TextView) findViewById(R.id.toolbar_title);
        mToolbarTitle.setText(getString(R.string.title_activity_add_staff));
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setNavigationIcon(R.mipmap.left_back);
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        etStaffName = (EditText) findViewById(R.id.et_staffName);
        etStaffPhone = (EditText) findViewById(R.id.et_staffPhone);
        tvStaffSex = (TextView) findViewById(R.id.tv_staffSex);
        tvStaffSex.setOnClickListener(AddStaffActivity.this);
        tvStaffEntryTime = (TextView) findViewById(R.id.tv_staffEntryTime);
        tvStaffEntryTime.setOnClickListener(AddStaffActivity.this);
        tvStaffArriveTime = (TextView) findViewById(R.id.tv_staffArriveTime);
        tvStaffArriveTime.setOnClickListener(AddStaffActivity.this);
        tvStaffRole = (TextView) findViewById(R.id.tv_staffRole);
        tvStaffRole.setOnClickListener(AddStaffActivity.this);
        tvStaffJurisdiction = (TextView) findViewById(R.id.tv_staffJurisdiction);
        tvStaffJurisdiction.setOnClickListener(AddStaffActivity.this);
        tvStaffState = (TextView) findViewById(R.id.tv_staffState);
        tvStaffState.setOnClickListener(AddStaffActivity.this);
        tvStaffComplete = (TextView) findViewById(R.id.tv_staffComplete);
        tvStaffComplete.setOnClickListener(AddStaffActivity.this);
    }

    /**
     * 处理点击事件
     *
     * @param view
     */
    @Override
    public void onClick(View view) {
        Intent intent = new Intent();
        staffName = etStaffName.getText().toString();
        staffPhone = etStaffPhone.getText().toString();
        if (view.getId() == R.id.tv_staffSex) {
            intent.setClass(AddStaffActivity.this, AddStudentsSelectTypeActivity.class);
            intent.putExtra("title", "选择性别");
            startActivityForResult(intent, 6000);

        } else if (view.getId() == R.id.tv_staffEntryTime) {
            istinguishdTime = "entry";
            showDate();

        } else if (view.getId() == R.id.tv_staffArriveTime) {
            istinguishdTime = "arrive";
            showDate();
        } else if (view.getId() == R.id.tv_staffRole) {

        } else if (view.getId() == R.id.tv_staffJurisdiction) {

        } else if (view.getId() == R.id.tv_staffState) {

        } else if (view.getId() == R.id.tv_staffComplete) {
            if (TextUtils.isEmpty(staffName)) {
                T.show(this, "请输入姓名", 100);
                return;
            }
            if (TextUtils.isEmpty(staffPhone)) {
                T.show(this, "请输入手机号", 100);
                return;
            }
            intent.setClass(AddStaffActivity.this, DetailsOfParentsActivity.class);
            startActivity(intent);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 6000) {
            if (resultCode == 2100) {
                String type = data.getStringExtra("type");
                //来源
                tvStaffSex.setText(type);
                tvStaffSex.setTextColor(Color.parseColor("#333333"));
            }
        }
    }

    /**
     * 显示日期
     *
     * @param
     */
    public void showDate() {
        // 出生日期
        DateScrollerDialog dialog = new DateScrollerDialog.Builder()
                .setType(Type.YEAR_MONTH_DAY)
                .setTitleStringId("请选择日期")
                .setMinMilliseconds(System.currentTimeMillis() - HUNDRED_YEARS)
                .setMaxMilliseconds(System.currentTimeMillis())
                .setCurMilliseconds(mLastTime)
                .setCallback(mOnDateSetListener)
                .build();

        if (dialog != null) {
            if (!dialog.isAdded()) {
                dialog.show(getSupportFragmentManager(), "year_month_day");
            }
        }
    }

    // 数据的回调
    private OnDateSetListener mOnDateSetListener = new OnDateSetListener() {
        @Override
        public void onDateSet(DateScrollerDialog timePickerView, long milliseconds) {
            mLastTime = milliseconds;
            birthday = getDateToString(milliseconds);
            if (istinguishdTime.equals("entry")) {
                entryTime = birthday;
                tvStaffEntryTime.setText(entryTime);//回调选中的数据
                tvStaffEntryTime.setTextColor(Color.parseColor("#333333"));
            }else {
                arriveTime = birthday;
                tvStaffArriveTime.setText(arriveTime);//回调选中的数据
                tvStaffArriveTime.setTextColor(Color.parseColor("#333333"));
            }
        }
    };

    public String getDateToString(long time) {
        Date d = new Date(time);
        return sf.format(d);
    }
}
