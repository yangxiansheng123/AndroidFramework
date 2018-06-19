package com.example.administrator.oper.ui.activity.edu;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.*;
import com.base.BaseActivity;
import com.example.administrator.oper.R;
import com.example.administrator.oper.adapter.edu.ClassTimeSettingListAdapter;
import com.utils.T;
import org.wangchenlong.datescroller.widget.DateScrollerDialog;
import org.wangchenlong.datescroller.widget.data.Type;
import org.wangchenlong.datescroller.widget.listener.OnDateSetListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * 版权：艺魔方公司 版权所有
 *
 * @author yang
 * 版本：1.0
 * 创建日期：2018/5/16
 * 描述：ClassTimeListActivity 上课时间列表
 */
public class ClassTimeListActivity extends BaseActivity implements View.OnClickListener {


    private static final long HUNDRED_YEARS = 100L * 365 * 1000 * 60 * 60 * 24L; // 100年
    protected TextView tvConfirm;
    protected LinearLayout llPrompt;
    protected TextView tvBg;
    private SimpleDateFormat sf = new SimpleDateFormat("HH:mm", Locale.ENGLISH);
    private long mLastTime = System.currentTimeMillis(); // 上次设置的时间
    private String birthday;
    private String istinguishdTime;
    protected TextView mToolbarTitle;
    protected Toolbar mToolbar;
    protected ImageView imgClosePrompt;
    protected TextView tvStartTime;
    protected TextView tvEndTime;
    protected ListView lvTimeList;
    private ClassTimeSettingListAdapter mClassTimeSettingListAdapter;
    private List<String> timesList = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_class_time_list);
        initView();
        setBackground();

        /**
         * 长按删除
         */
        lvTimeList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ClassTimeListActivity.this);
                builder.setTitle("时间")
                        .setMessage("确定删除此时间么？")
                        .setPositiveButton(getString(R.string.confirm), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                timesList.remove(i);
                                mClassTimeSettingListAdapter.notifyDataSetChanged();
                            }
                        })
                        .setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .show();
                return false;
            }
        });

        lvTimeList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent();
                intent.putExtra("times", timesList.get(i));
                setResult(1220, intent);
                finish();
            }
        });
    }

    private void setBackground() {
        if (timesList.size() > 0) {
            tvBg.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.solid_shape_white_top_left_right));
        } else {
            tvBg.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.solid_shape_white));
        }
    }


    /**
     * 初始化控件
     */
    private void initView() {
        mClassTimeSettingListAdapter = new ClassTimeSettingListAdapter(this);
        mToolbarTitle = (TextView) findViewById(R.id.toolbar_title);
        mToolbarTitle.setText(getString(R.string.title_activity_class_time_list));
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setNavigationIcon(R.mipmap.left_back);
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        imgClosePrompt = (ImageView) findViewById(R.id.img_closePrompt);
        imgClosePrompt.setOnClickListener(ClassTimeListActivity.this);
        tvStartTime = (TextView) findViewById(R.id.tv_startTime);
        tvStartTime.setOnClickListener(ClassTimeListActivity.this);
        tvEndTime = (TextView) findViewById(R.id.tv_endTime);
        tvEndTime.setOnClickListener(ClassTimeListActivity.this);
        lvTimeList = (ListView) findViewById(R.id.lv_timeList);
        tvConfirm = (TextView) findViewById(R.id.tv_confirm);
        tvConfirm.setOnClickListener(ClassTimeListActivity.this);

        mClassTimeSettingListAdapter.setListData(timesList);
        lvTimeList.setAdapter(mClassTimeSettingListAdapter);
        llPrompt = (LinearLayout) findViewById(R.id.ll_prompt);
        tvBg = (TextView) findViewById(R.id.tv_bg);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.img_closePrompt) {
            llPrompt.setVisibility(View.GONE);

        } else if (view.getId() == R.id.tv_startTime) {
            showDate();
            istinguishdTime = "start";

        } else if (view.getId() == R.id.tv_endTime) {
            showDate();
            istinguishdTime = "end";

        } else if (view.getId() == R.id.tv_confirm) {
            if (TextUtils.isEmpty(startTime)) {
                T.show(this, "请选择开始时间", 100);
                return;
            }
            if (TextUtils.isEmpty(endTime)) {
                T.show(this, "请选择结束时间", 100);
                return;
            }
            timesList.add(startTime + "-" + endTime);
            mClassTimeSettingListAdapter.notifyDataSetChanged();
            setBackground();
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
                .setType(Type.HOURS_MINS)
                .setTitleStringId("请选择上课时间")
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

    private String startTime, endTime;
    // 数据的回调
    private OnDateSetListener mOnDateSetListener = new OnDateSetListener() {
        @Override
        public void onDateSet(DateScrollerDialog timePickerView, long milliseconds) {
            mLastTime = milliseconds;
            //回调选中的数据
            birthday = getDateToString(milliseconds);
            if (istinguishdTime.equals("start")) {
                startTime = birthday;
                tvStartTime.setText(startTime);
                tvStartTime.setTextColor(Color.parseColor("#333333"));
            } else {
                endTime = birthday;
                tvEndTime.setText(endTime);
                tvConfirm.setTextColor(Color.WHITE);
                tvEndTime.setTextColor(Color.parseColor("#333333"));
            }
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
