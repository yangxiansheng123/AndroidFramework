package com.example.administrator.oper.ui.activity.edu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.*;
import com.base.BaseActivity;
import com.example.administrator.oper.R;
import com.example.administrator.oper.adapter.edu.TimeTablesAdapter;
import com.example.administrator.oper.bean.edu.TimetablesBean;
import com.haibin.calendarview.Calendar;
import com.haibin.calendarview.CalendarLayout;
import com.haibin.calendarview.CalendarView;
import com.zaaach.toprightmenu.MenuItem;
import com.zaaach.toprightmenu.TopRightMenu;

import java.util.ArrayList;
import java.util.List;

/**
 * 版权：艺魔方公司 版权所有
 *
 * @author yang
 * 版本：1.0
 * 创建日期：2018/5/2
 * 描述：TimetablesActivity
 */
public class TimetablesActivity extends BaseActivity implements View.OnClickListener,
        CalendarView.OnDateSelectedListener,
        CalendarView.OnYearChangeListener {

    protected TextView mToolbarTitle;
    protected Toolbar mToolbar;
    protected ImageView mImgRight;
    protected TextView mTvMonthDay;
    protected TextView mTvYear;
    protected TextView mTvCurrentDay;
    protected CalendarView mCalendarView;
    protected ProgressBar progressbar;
    protected TextView tvRefresh;
    protected CalendarLayout mCalendarLayout;
    protected RecyclerView mRecyclerView;
    private TimeTablesAdapter mTimeTablesAdapter;
    private int width, height;
    private TopRightMenu mTopRightMenu;
    private LayoutInflater mInflater;

    private List<TimetablesBean> listData = new ArrayList<>();

    private int mYear;
    private View headView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timetables);
        for (int i = 0; i < 10; i++) {
            listData.add(new TimetablesBean("gs"));
        }
        initView();
        initData();
        calculationScreen();

    }

    /**
     * 初始化控件
     */
    private void initView() {
        mInflater = LayoutInflater.from(this);
        mTimeTablesAdapter =new TimeTablesAdapter(TimetablesActivity.this);
        mToolbarTitle = (TextView) findViewById(R.id.toolbar_title);
        mToolbarTitle.setText(getString(R.string.title_activity_timetables));
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setNavigationIcon(R.mipmap.left_back);
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        mImgRight = (ImageView) findViewById(R.id.img_right);
        mImgRight.setOnClickListener(TimetablesActivity.this);
        mImgRight.setImageDrawable(getResources().getDrawable(R.mipmap.add1));
        mTvMonthDay = (TextView) findViewById(R.id.tv_month_day);

        mTvMonthDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCalendarView.scrollToCurrent();
            }
        });
        mTvYear = (TextView) findViewById(R.id.tv_year);
        mTvCurrentDay = (TextView) findViewById(R.id.tv_current_day);
        mTvCurrentDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mCalendarLayout.isExpand()) {
                    mCalendarView.showYearSelectLayout(mYear);
                    return;
                }
                mCalendarView.showYearSelectLayout(mYear);
                mTvYear.setVisibility(View.VISIBLE);
                mTvMonthDay.setText(String.valueOf(mCalendarView.getCurMonth())+"月");
            }
        });
        mCalendarView = (CalendarView) findViewById(R.id.calendarView);
        progressbar = (ProgressBar) findViewById(R.id.progressbar);
        tvRefresh = (TextView) findViewById(R.id.tvRefresh);
        mCalendarLayout = (CalendarLayout) findViewById(R.id.calendarLayout);
        mCalendarView.setOnDateSelectedListener(this);
        mCalendarView.setOnYearChangeListener(this);
        mTvYear.setText(String.valueOf(mCalendarView.getCurYear()));
        mYear = mCalendarView.getCurYear();
//        mTvMonthDay.setText(mCalendarView.getCurMonth() + "月" + mCalendarView.getCurDay() + "日");
        mTvMonthDay.setText(mCalendarView.getCurMonth() + "月" );
//        mTvCurrentDay.setText(String.valueOf(mCalendarView.getCurDay()));

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        headView = mInflater.inflate(R.layout.activity_timetables_headview,null);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mTimeTablesAdapter.setHeaderView(headView);
        mTimeTablesAdapter.setListData(listData);
        mRecyclerView.setAdapter(mTimeTablesAdapter);
        mTimeTablesAdapter.notifyDataSetChanged();
    }


    private void initData() {
        List<Calendar> schemes = new ArrayList<>();
        int year = mCalendarView.getCurYear();
        int month = mCalendarView.getCurMonth();

        schemes.add(getSchemeCalendar(year, month, 3, 0xFF40db25, "假"));
        schemes.add(getSchemeCalendar(year, month, 6, 0xFFe69138, "事"));
        schemes.add(getSchemeCalendar(year, month, 10, 0xFFdf1356, "议"));
        schemes.add(getSchemeCalendar(year, month, 11, 0xFFedc56d, "记"));
        schemes.add(getSchemeCalendar(year, month, 14, 0xFFedc56d, "记"));
        schemes.add(getSchemeCalendar(year, month, 15, 0xFFaacc44, "假"));
        schemes.add(getSchemeCalendar(year, month, 18, 0xFFbc13f0, "记"));
        schemes.add(getSchemeCalendar(year, month, 25, 0xFF13acf0, "假"));
        schemes.add(getSchemeCalendar(year, month, 27, 0xFF13acf0, "多"));
        mCalendarView.setSchemeDate(schemes);

    }


    /**
     * 处理点击事件
     *
     * @param view
     */
    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.img_right) {
            topRightDialog(mImgRight);
        }
    }


    /**
     * 右上角弹窗
     *
     * @param img
     */
    private void topRightDialog(View img) {
        mTopRightMenu = new TopRightMenu(TimetablesActivity.this);
        mTopRightMenu
                //默认高度480
                .setHeight(height / 4)
                //默认宽度wrap_content
                .setWidth(width / 2)
                //显示菜单图标，默认为true
                .showIcon(true)
                //背景变暗，默认为true
                .dimBackground(true)
                //显示动画，默认为true
                .needAnimationStyle(true)
                //默认为R.style.TRM_ANIM_STYLE
                .setAnimationStyle(R.style.TRM_ANIM_STYLE)
                .addMenuItem(new MenuItem(R.mipmap.add_class_hour, "添加班级"))
                .addMenuItem(new MenuItem(R.mipmap.lecture, "全天周课"))
                .addMenuItem(new MenuItem(R.mipmap.stop_class, "全天停课"))
                .setOnMenuItemClickListener(new TopRightMenu.OnMenuItemClickListener() {
                    @Override
                    public void onMenuItemClick(int position) {
                        Intent intent = new Intent();
                        if (position == 0) {
                            intent.setClass(TimetablesActivity.this, AddCassActivity.class);
                            startActivity(intent);
                        } else if (position == 1) {
                        }
                    }
                })
                .showAsDropDown(img, -width / 4 - width / 6, height / 40);
    }


    @Override
    public void onYearChange(int year) {
        mTvYear.setText(String.valueOf(year));

    }

    @Override
    public void onDateSelected(Calendar calendar, boolean isClick) {

//        mTvLunar.setVisibility(View.VISIBLE);
        mTvYear.setVisibility(View.VISIBLE);
//        mTvMonthDay.setText(calendar.getMonth() + "月" + calendar.getDay() + "日");
        mTvMonthDay.setText(calendar.getMonth() + "月");
        mTvYear.setText(String.valueOf(calendar.getYear()));
//        mTvLunar.setText(calendar.getLunar());
        mYear = calendar.getYear();
    }


    private Calendar getSchemeCalendar(int year, int month, int day, int color, String text) {
        Calendar calendar = new Calendar();
        calendar.setYear(year);
        calendar.setMonth(month);
        calendar.setDay(day);
        calendar.setSchemeColor(color);//如果单独标记颜色、则会使用这个颜色
        calendar.setScheme(text);
        calendar.addScheme(new Calendar.Scheme());
        calendar.addScheme(0xFF008800, "假");
        calendar.addScheme(0xFF008800, "节");
        return calendar;
    }


    /**
     * 计算屏幕宽高
     */
    private void calculationScreen() {
        WindowManager wm = this.getWindowManager();
        width = wm.getDefaultDisplay().getWidth();
        height = wm.getDefaultDisplay().getHeight();
    }
}
