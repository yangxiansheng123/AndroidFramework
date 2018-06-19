package com.example.administrator.oper.ui.activity.edu;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.text.Layout;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.base.BaseToolbarActivity;
import com.example.administrator.oper.R;
import com.example.administrator.oper.fragmentfactorys.FragmentClassDetailsFactory;
import com.example.administrator.oper.ui.widget.TabPageIndicator;
import com.utils.DisplayUtil;
import com.utils.SpanUtils;
import com.utils.ToolbarHelper;

/**
 * 版权：艺魔方公司 版权所有
 *
 * @author yang
 * 版本：1.0
 * 创建日期：2018/5/14
 * 描述：ClassDetailsActivity 班级管理-->班级详情
 */
public class ClassDetailsActivity extends BaseToolbarActivity implements View.OnClickListener {

    protected TextView mToolbarTitle;
    protected Toolbar mToolbar;
    protected TabPageIndicator indicatorBookkeeping;
    protected ViewPager vpBookkeeping;
    protected TextView tvClassTimetable;
    protected TextView tvClassType;
    protected TextView tvClassCourse;
    protected TextView tvClassTeacher;
    protected TextView tvClassCategory;
    protected TextView tvClassStudents;
    protected LinearLayout llSeeCourseArrangement;
    protected LinearLayout llAddOneToOneStu;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_class_details;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_class_details);
        initView();
        BasePagerAdapter adapter = new BasePagerAdapter(this.getSupportFragmentManager());
        vpBookkeeping.setAdapter(adapter);
        indicatorBookkeeping.setViewPager(vpBookkeeping);
        setTabPagerIndicator();
    }


    @Override
    protected void initToolbar(ToolbarHelper toolbarHelper) {
        toolbarHelper.setTitle(getString(R.string.title_activity_class_details));
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

    private void initView() {
        mToolbarTitle = (TextView) findViewById(R.id.toolbar_title);
        mToolbarTitle.setText(getString(R.string.title_activity_class_details));
        indicatorBookkeeping = (TabPageIndicator) findViewById(R.id.indicator_bookkeeping);
        vpBookkeeping = (ViewPager) findViewById(R.id.vp_bookkeeping);
        tvClassTimetable = (TextView) findViewById(R.id.tv_classTimetable);
        tvClassType = (TextView) findViewById(R.id.tv_classType);
        tvClassCourse = (TextView) findViewById(R.id.tv_classCourse);
        tvClassTeacher = (TextView) findViewById(R.id.tv_classTeacher);
        tvClassCategory = (TextView) findViewById(R.id.tv_classCategory);
        tvClassStudents = (TextView) findViewById(R.id.tv_classStudents);
        initData();
        llSeeCourseArrangement = (LinearLayout) findViewById(R.id.ll_seeCourseArrangement);
        llSeeCourseArrangement.setOnClickListener(ClassDetailsActivity.this);
        llAddOneToOneStu = (LinearLayout) findViewById(R.id.ll_addOneToOneStu);
        llAddOneToOneStu.setOnClickListener(ClassDetailsActivity.this);
    }

    /**
     * 初始化数据
     */
    private void initData() {
        tvClassTimetable.setText("周二 12:00-12:45 / 周三 12:00...");
        tvClassType.setText("钢琴一班");
        tvClassCourse.setText("钢琴初级课程");
        tvClassTeacher.setText(new SpanUtils()
                .appendSpace(30)
                .append("●")
                .setForegroundColor(Color.parseColor("#3ed548"))
                .setFontSize(32)
                .appendSpace(20)
                .append("老师：苏亚")
                .setAlign(Layout.Alignment.ALIGN_CENTER)
                .setForegroundColor(Color.parseColor("#a5a5a5"))
                .setFontSize(40)
                .create());

        tvClassCategory.setText("钢琴");

        tvClassStudents.setText(new SpanUtils()
                .appendSpace(30)
                .append("●")
                .setForegroundColor(Color.parseColor("#f39019"))
                .setFontSize(32)
                .appendSpace(20)
                .append("学生：23")
                .setAlign(Layout.Alignment.ALIGN_CENTER)
                .setForegroundColor(Color.parseColor("#a5a5a5"))
                .setFontSize(40)
                .create());
    }


    //导航栏设置
    private void setTabPagerIndicator() {
        indicatorBookkeeping.setIndicatorMode(TabPageIndicator.IndicatorMode.MODE_WEIGHT_NOEXPAND_SAME);
        // 设置模式，一定要先设置模式
//        indicator.setDividerColor(Color.parseColor("#00bbcf"));
// 设置分割线的颜色
//        indicator.setDividerPadding(DisplayUtil.dip2px(this, 0));
        // 设置底部导航线的颜色
        indicatorBookkeeping.setIndicatorColor(Color.parseColor("#fbd415"));
        // 设置底部导航线的高度
        indicatorBookkeeping.setIndicatorHeight(DisplayUtil.dip2px(this, 2));
        // 设置tab标题选中的颜色
        indicatorBookkeeping.setTextColorSelected(Color.parseColor("#333333"));
        // 设置tab标题未被选中的颜色
        indicatorBookkeeping.setTextColor(Color.parseColor("#a5a5a5"));

        // 设置字体大小
        indicatorBookkeeping.setTextSize((int) DisplayUtil.sp2px(this, 18));
        // 设置标题栏和viewPager的分割线
        indicatorBookkeeping.setUnderlineHeight(DisplayUtil.dip2px(this, 0));
    }

    /**
     * 处理点击事件
     *
     * @param view
     */
    @Override
    public void onClick(View view) {
        Intent intent = new Intent();
        if (view.getId() == R.id.ll_seeCourseArrangement) {
            intent.setClass(ClassDetailsActivity.this, CourseArrangementListActivity.class);
            startActivity(intent);
        } else if (view.getId() == R.id.ll_addOneToOneStu) {
            intent.setClass(ClassDetailsActivity.this, AddOneToOneStudentsActivity.class);
            startActivity(intent);

        }
    }

    //适配器 管理fragment
    class BasePagerAdapter extends FragmentPagerAdapter {
        String[] titles;

        public BasePagerAdapter(FragmentManager fm) {
            super(fm);
            this.titles = getResources().getStringArray(R.array.classdetails);

        }

        @Override
        public Fragment getItem(int position) {
            return FragmentClassDetailsFactory.createForNoExpand(position);
        }

        @Override
        public int getCount() {
            return titles.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }
    }


}
