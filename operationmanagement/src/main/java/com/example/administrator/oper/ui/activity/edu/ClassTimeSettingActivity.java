package com.example.administrator.oper.ui.activity.edu;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.base.BaseActivity;
import com.example.administrator.oper.R;
import com.example.administrator.oper.adapter.edu.ClassTimeSettingAdapter;
import com.example.administrator.oper.db.ClassTimeSettingBean;
import com.example.administrator.oper.db.ClassTimeSettingListBean;
import com.example.administrator.oper.ui.activity.daily.AddStudentsSelectTypeActivity;
import com.google.gson.Gson;
import com.utils.TimeUtil;
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
 * 创建日期：2018/5/10
 * 描述：ClassTimeSettingActivity上课时间设置
 */
public class ClassTimeSettingActivity extends BaseActivity implements View.OnClickListener, ClassTimeSettingAdapter.DeleteSettingTime {


    private static final long HUNDRED_YEARS = 100L * 365 * 1000 * 60 * 60 * 24L; // 100年
    protected LinearLayout llHeadbg;
    private SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
    private long mLastTime = System.currentTimeMillis(); // 上次设置的时间
    private String birthday;

    protected TextView mToolbarTitle;
    protected Toolbar mToolbar;
    protected ImageView mImgAddClassTime;
    protected ListView lvShowTime;
    protected TextView tvComplete;
    private ClassTimeSettingAdapter mClassTimeSettingAdapter;
    private Gson gson = new Gson();
    private LayoutInflater mInflater;

    /**
     * 存储时间
     */
    private ClassTimeSettingListBean mClassTimeSettingBeanList;

    private ClassTimeSettingBean mClassTimeSettingBean;
    private View footerview;
    private String type, times;
    private int mPosition;
    private TextView tvStartTime, tvEndTime;
    private String istinguishdTime;
    private int ymdPosition;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_time_setting);
        initView();
        initData();
    }


    private void initView() {
        mInflater = LayoutInflater.from(this);
        footerview = mInflater.inflate(R.layout.activity_class_time_setting_footerview, null);

        tvStartTime = footerview.findViewById(R.id.tv_startTime);
        tvEndTime = footerview.findViewById(R.id.tv_endTime);

        mClassTimeSettingAdapter = new ClassTimeSettingAdapter(this, this);
        mClassTimeSettingBean = new ClassTimeSettingBean();
        mClassTimeSettingBeanList = new ClassTimeSettingListBean();

        mToolbarTitle = (TextView) findViewById(R.id.toolbar_title);
        mToolbarTitle.setText(getString(R.string.title_activity_class_time_setting));
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setNavigationIcon(R.mipmap.left_back);
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        mImgAddClassTime = (ImageView) findViewById(R.id.img_addClassTime);
        mImgAddClassTime.setOnClickListener(ClassTimeSettingActivity.this);
        lvShowTime = (ListView) findViewById(R.id.lv_showTime);

        lvShowTime.addFooterView(footerview);
        tvComplete = (TextView) findViewById(R.id.tv_complete);
        tvComplete.setOnClickListener(ClassTimeSettingActivity.this);

        /**
         * 时间选择
         */
        tvStartTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDate();
                istinguishdTime = "start";
            }
        });

        tvEndTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDate();
                istinguishdTime = "end";
            }
        });

        llHeadbg = (LinearLayout) findViewById(R.id.ll_headbg);


    }


    /**
     * 初始化数据
     */
    private void initData() {
        mClassTimeSettingBean.setClassTimeType("单次课");
        mClassTimeSettingBean.setClassTimeTimeYear(TimeUtil.formatTimeYMD(System.currentTimeMillis()));
        mClassTimeSettingBean.setClassTimeTimeSlot(TimeUtil.formatTimeHMS(System.currentTimeMillis()));


        SharedPreferences preferences = getSharedPreferences("settingTime", MODE_PRIVATE);
        String alarmData = preferences.getString("settingTimeData", "");
        ClassTimeSettingListBean classTimeSettingListBean = new ClassTimeSettingListBean();
        if (alarmData.equals("")) {
            mClassTimeSettingBeanList.setAlarm(new ArrayList<ClassTimeSettingBean>());
        } else {
            classTimeSettingListBean = gson.fromJson(alarmData, ClassTimeSettingListBean.class);
            mClassTimeSettingBeanList = classTimeSettingListBean;
        }
    }

    /**
     * 处理点击事件
     *
     * @param view
     */
    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.img_addClassTime) {
            addData();

        } else if (view.getId() == R.id.tv_complete) {
            SharedPreferences preferences = getSharedPreferences("settingTime", MODE_PRIVATE);
            String settingTimeData = preferences.getString("settingTimeData", "");
            Log.e("settingTimeData: ", "" + settingTimeData);
        }
    }

    /**
     * 添加数据
     */
    private void addData() {
        mClassTimeSettingBeanList.getAlarm().add(mClassTimeSettingBean);
        Gson gson = new Gson();
        String alarmData = gson.toJson(mClassTimeSettingBeanList);
        SharedPreferences preferences = getSharedPreferences("settingTime", MODE_PRIVATE);
        SharedPreferences.Editor edit = preferences.edit();
        edit.putString("settingTimeData", alarmData);
        edit.commit();

        attainData();
    }

    /**
     * 获取数据
     */
    private void attainData() {
        SharedPreferences preferences = getSharedPreferences("settingTime", MODE_PRIVATE);
        String settingTimeData = preferences.getString("settingTimeData", "");

        if (settingTimeData.equals("")) {
            return;
        }
        ClassTimeSettingListBean bean = gson.fromJson(settingTimeData, ClassTimeSettingListBean.class);

        changeBackground(bean);
        if (bean.getAlarm() == null) {
            return;
        }
        mClassTimeSettingAdapter.setListData(bean.getAlarm());
        lvShowTime.setAdapter(mClassTimeSettingAdapter);
        mClassTimeSettingAdapter.notifyDataSetChanged();


    }

    @Override
    protected void onResume() {
        super.onResume();
        attainData();
    }

    /**
     * 删除指定的时间设置
     *
     * @param listData
     * @param position
     */
    @Override
    public void deleteTime(List<ClassTimeSettingBean> listData, int position) {
        dialogDelete(listData, position);
    }

    /**
     * 修改类型
     *
     * @param listData
     * @param position
     */
    @Override
    public void modifyCourseType(List<ClassTimeSettingBean> listData, int position) {
        mPosition = position;
        Intent intent = new Intent();
        intent.setClass(ClassTimeSettingActivity.this, AddStudentsSelectTypeActivity.class);
        intent.putExtra("title", "选择上课类型");
        startActivityForResult(intent, 2000);
    }

    /**
     * 修改年月日
     *
     * @param listData
     * @param position
     */
    @Override
    public void modifyCourseYMD(List<ClassTimeSettingBean> listData, int position) {
        showDate();
        ymdPosition = position;
        istinguishdTime = "ymd";
    }

    /**
     * 时分秒
     *
     * @param listData
     * @param position
     */
    @Override
    public void modifyCourseHMS(List<ClassTimeSettingBean> listData, int position) {
        Intent intent = new Intent();
        intent.setClass(this, ClassTimeListActivity.class);
        startActivityForResult(intent, 2000);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 2000) {
            if (resultCode == 2100) {
                type = data.getStringExtra("type");
                SharedPreferences preferences = getSharedPreferences("settingTime", MODE_PRIVATE);
                String alarm = preferences.getString("settingTimeData", "");
                SharedPreferences.Editor edit = preferences.edit();
                mClassTimeSettingBeanList = gson.fromJson(alarm, ClassTimeSettingListBean.class);
                //删除数据
                mClassTimeSettingBeanList.getAlarm().get(mPosition).setClassTimeType(type);
                String alarmData = gson.toJson(mClassTimeSettingBeanList);
                edit.putString("settingTimeData", alarmData);
                edit.commit();
                mClassTimeSettingAdapter.setListData(mClassTimeSettingBeanList.getAlarm());
                String timeData = preferences.getString("settingTimeData", "");
                ClassTimeSettingListBean bean = gson.fromJson(timeData, ClassTimeSettingListBean.class);
                mClassTimeSettingAdapter.setListData(bean.getAlarm());
                setListViewHeightBasedOnChildren(lvShowTime);
                mClassTimeSettingAdapter.notifyDataSetChanged();
            } else if (resultCode == 1220) {
                times = data.getStringExtra("times");
                SharedPreferences preferences = getSharedPreferences("settingTime", MODE_PRIVATE);
                String alarm = preferences.getString("settingTimeData", "");
                SharedPreferences.Editor edit = preferences.edit();

                mClassTimeSettingBeanList = gson.fromJson(alarm, ClassTimeSettingListBean.class);
                //删除数据
                mClassTimeSettingBeanList.getAlarm().get(mPosition).setClassTimeTimeSlot(times);
                String alarmData = gson.toJson(mClassTimeSettingBeanList);
                edit.putString("settingTimeData", alarmData);
                edit.commit();
                mClassTimeSettingAdapter.setListData(mClassTimeSettingBeanList.getAlarm());
                String timeData = preferences.getString("settingTimeData", "");
                ClassTimeSettingListBean bean = gson.fromJson(timeData, ClassTimeSettingListBean.class);
                mClassTimeSettingAdapter.setListData(bean.getAlarm());
                mClassTimeSettingAdapter.notifyDataSetChanged();
            }
        }
    }

    /**
     * 删除数据
     *
     * @param listData
     * @param position
     */
    private void dialogDelete(List<ClassTimeSettingBean> listData, int position) {
        SharedPreferences preferences = getSharedPreferences("settingTime", MODE_PRIVATE);
        String alarm = preferences.getString("settingTimeData", "");
        SharedPreferences.Editor edit = preferences.edit();
//                        Log.e("alarmData",alarm);
        mClassTimeSettingBeanList = gson.fromJson(alarm, ClassTimeSettingListBean.class);
        //删除数据
        mClassTimeSettingBeanList.getAlarm().remove(position);
        String alarmData = gson.toJson(mClassTimeSettingBeanList);
        edit.putString("settingTimeData", alarmData);
        edit.commit();
        mClassTimeSettingAdapter.setListData(mClassTimeSettingBeanList.getAlarm());
        String data = preferences.getString("settingTimeData", "");
        ClassTimeSettingListBean bean = gson.fromJson(data, ClassTimeSettingListBean.class);
        mClassTimeSettingAdapter.setListData(bean.getAlarm());
        mClassTimeSettingAdapter.notifyDataSetChanged();
        changeBackground(bean);
    }


    /**
     * 改变背景
     *
     * @param bean
     */
    private void changeBackground(ClassTimeSettingListBean bean) {
        if (bean.getAlarm().size() > 0) {
            llHeadbg.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.solid_shape_white_top_left_right));
        } else {
            llHeadbg.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.solid_shape_white));
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
                .setTitleStringId("请选择上课日期")
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
    private String ymdTime;
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
                tvStartTime.setText(startTime);
                tvStartTime.setTextColor(Color.parseColor("#333333"));
            } else if (istinguishdTime.equals("ymd")) {
                ymdTime = birthday;
                SharedPreferences preferences = getSharedPreferences("settingTime", MODE_PRIVATE);
                String alarm = preferences.getString("settingTimeData", "");
                SharedPreferences.Editor edit = preferences.edit();

                mClassTimeSettingBeanList = gson.fromJson(alarm, ClassTimeSettingListBean.class);
                //删除数据
                mClassTimeSettingBeanList.getAlarm().get(ymdPosition).setClassTimeTimeYear(ymdTime);
                String alarmData = gson.toJson(mClassTimeSettingBeanList);
                edit.putString("settingTimeData", alarmData);
                edit.commit();
                mClassTimeSettingAdapter.setListData(mClassTimeSettingBeanList.getAlarm());
                String timeData = preferences.getString("settingTimeData", "");
                ClassTimeSettingListBean bean = gson.fromJson(timeData, ClassTimeSettingListBean.class);
                mClassTimeSettingAdapter.setListData(bean.getAlarm());
                mClassTimeSettingAdapter.notifyDataSetChanged();

            } else {
                endTime = birthday;
                tvEndTime.setText(endTime);
                tvEndTime.setTextColor(Color.parseColor("#333333"));
            }
        }
    };

    public String getDateToString(long time) {
        Date d = new Date(time);
        return sf.format(d);
    }


    /**
     * 计算listView高度
     *
     * @param listView
     */
    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
// pre-condition
            return;
        }
        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            listItem.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED), View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
            totalHeight += listItem.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }
}
