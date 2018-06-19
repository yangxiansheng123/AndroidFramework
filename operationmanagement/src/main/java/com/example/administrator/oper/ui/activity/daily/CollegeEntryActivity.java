package com.example.administrator.oper.ui.activity.daily;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.base.BaseToolbarActivity;
import com.example.administrator.oper.R;
import com.example.administrator.oper.ui.activity.edu.DetailsOfParentsActivity;
import com.example.administrator.oper.ui.activity.me.PersonalInfoActivity;
import com.example.administrator.oper.ui.utils.SetTextColor;
import com.utils.RxRegTool;
import com.utils.T;
import com.utils.ToolbarHelper;
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
 * 创建日期：2018/5/3
 * 描述：CollegeEntryActivity
 */
public class CollegeEntryActivity extends BaseToolbarActivity implements View.OnClickListener {


    private static final long HUNDRED_YEARS = 100L * 365 * 1000 * 60 * 60 * 24L; // 100年
    protected TextView tvAddress;
    protected LinearLayout llSelectAddress;
    protected EditText etIsMobile;
    private SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
    private long mLastTime = System.currentTimeMillis(); // 上次设置的时间
    private String birthday;

    protected LinearLayout llStudentDistribution;
    protected TextView tvComplete;
    protected TextView tvStudetSource;
    protected LinearLayout llStudetSource;
    protected TextView tvSelectParent;
    protected LinearLayout llSelectSex;
    protected LinearLayout llCustmerEvaluation;
    protected TextView tvSelectSex;
    protected TextView tvCustmerEvaluation;
    protected TextView tvIntentionalCourse;
    protected LinearLayout llIntentionalCourse;
    protected TextView tvBirthday;
    protected LinearLayout llBirthday;
    private String phone;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_college_entry;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        super.setContentView(R.layout.activity_college_entry);
        initView();
    }


    @Override
    protected void initToolbar(ToolbarHelper toolbarHelper) {

        toolbarHelper.setTitle(getString(R.string.title_activity_college_entry));
        Toolbar toolbar = toolbarHelper.getToolbar();
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
     * 处理点击事件
     *
     * @param view
     */
    @Override
    public void onClick(View view) {
        phone = etIsMobile.getText().toString();
        Intent intent = new Intent();
        if (view.getId() == R.id.ll_studentDistribution) {
            intent.setClass(CollegeEntryActivity.this, SelectParentalDistributionActivity.class);
            startActivity(intent);
        } else if (view.getId() == R.id.tv_complete) {
            if (RxRegTool.isMobile(phone)) {
            } else {
                T.show(CollegeEntryActivity.this, "请输入正确的手机号码", 1000);
                etIsMobile.setText("");
                return;
            }
            intent.setClass(CollegeEntryActivity.this, DetailsOfParentsActivity.class);
            startActivity(intent);
        } else if (view.getId() == R.id.ll_studetSource) {
            intent.setClass(CollegeEntryActivity.this, AddStudentsSelectTypeActivity.class);
            intent.putExtra("title", "学员来源");
            startActivityForResult(intent, 2000);

        } else if (view.getId() == R.id.tv_selectParent) {
            intent.setClass(CollegeEntryActivity.this, AddStudentsSelectTypeActivity.class);
            intent.putExtra("title", "选择家长");
            startActivityForResult(intent, 2001);

        } else if (view.getId() == R.id.ll_selectSex) {
            intent.setClass(CollegeEntryActivity.this, AddStudentsSelectTypeActivity.class);
            intent.putExtra("title", "选择性别");
            startActivityForResult(intent, 2002);

        } else if (view.getId() == R.id.ll_custmerEvaluation) {
            intent.setClass(CollegeEntryActivity.this, AddStudentsSelectTypeActivity.class);
            intent.putExtra("title", "客户评定");
            startActivityForResult(intent, 2003);

        } else if (view.getId() == R.id.ll_intentionalCourse) {
            intent.setClass(CollegeEntryActivity.this, IntentionalCourseActivity.class);
            intent.putExtra("title", "客户评定");
            startActivityForResult(intent, 2003);
        } else if (view.getId() == R.id.ll_birthday) {
            showDate();
        } else if (view.getId() == R.id.ll_selectAddress) {
            intent.setClass(CollegeEntryActivity.this, SelectAddressActivity.class);
            startActivityForResult(intent, 6000);
        }
    }

    private void initView() {
        llStudentDistribution = (LinearLayout) findViewById(R.id.ll_studentDistribution);
        llStudentDistribution.setOnClickListener(CollegeEntryActivity.this);
        tvComplete = (TextView) findViewById(R.id.tv_complete);
        tvComplete.setOnClickListener(CollegeEntryActivity.this);
        tvStudetSource = (TextView) findViewById(R.id.tv_studetSource);
        llStudetSource = (LinearLayout) findViewById(R.id.ll_studetSource);
        llStudetSource.setOnClickListener(CollegeEntryActivity.this);
        tvSelectParent = (TextView) findViewById(R.id.tv_selectParent);
        tvSelectParent.setOnClickListener(CollegeEntryActivity.this);
        llSelectSex = (LinearLayout) findViewById(R.id.ll_selectSex);
        llSelectSex.setOnClickListener(CollegeEntryActivity.this);
        llCustmerEvaluation = (LinearLayout) findViewById(R.id.ll_custmerEvaluation);
        llCustmerEvaluation.setOnClickListener(CollegeEntryActivity.this);
        tvSelectSex = (TextView) findViewById(R.id.tv_selectSex);
        tvCustmerEvaluation = (TextView) findViewById(R.id.tv_custmerEvaluation);
        tvIntentionalCourse = (TextView) findViewById(R.id.tv_intentionalCourse);
        llIntentionalCourse = (LinearLayout) findViewById(R.id.ll_intentionalCourse);
        llIntentionalCourse.setOnClickListener(CollegeEntryActivity.this);
        tvBirthday = (TextView) findViewById(R.id.tv_birthday);
        llBirthday = (LinearLayout) findViewById(R.id.ll_birthday);
        llBirthday.setOnClickListener(CollegeEntryActivity.this);
        tvAddress = (TextView) findViewById(R.id.tv_address);
        llSelectAddress = (LinearLayout) findViewById(R.id.ll_selectAddress);
        llSelectAddress.setOnClickListener(CollegeEntryActivity.this);
        etIsMobile = (EditText) findViewById(R.id.et_isMobile);
    }

    /**
     * 回调
     *
     * @param requestCode 请求码2000
     * @param resultCode  结果码20001
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 2000) {
            if (resultCode == 2100) {
                String type = data.getStringExtra("type");
                //来源
                tvStudetSource.setText(type);
                tvStudetSource.setTextColor(SetTextColor.color);
            }
        } else if (requestCode == 2001) {
            if (resultCode == 2100) {
                String type = data.getStringExtra("type");
                //来源
                tvSelectParent.setText(type);
                tvSelectParent.setTextColor(SetTextColor.color);
            }
        } else if (requestCode == 2002) {
            if (resultCode == 2100) {
                String type = data.getStringExtra("type");
                //来源
                tvSelectSex.setText(type);
                tvSelectSex.setTextColor(SetTextColor.color);
            }
        } else if (requestCode == 2003) {
            if (resultCode == 2100) {
                String type = data.getStringExtra("type");
                //来源
                tvCustmerEvaluation.setText(type);
                tvCustmerEvaluation.setTextColor(SetTextColor.color);
            }
        } else if (requestCode == 6000) {
            if (resultCode == 2100) {
                String PMAAddress = data.getStringExtra("PMAAddress");
                String detailsAddress = data.getStringExtra("detailsAddress");
                //来源
                tvAddress.setText(PMAAddress + " " + detailsAddress);
                tvAddress.setTextColor(SetTextColor.color);
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
            tvBirthday.setText(birthday);//回调选中的数据
            tvBirthday.setTextColor(SetTextColor.color);
        }
    };

    public String getDateToString(long time) {
        Date d = new Date(time);
        return sf.format(d);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        T.hideToast();
    }
}
