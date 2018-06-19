package com.example.administrator.oper.ui.activity.daily;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.text.Layout;
import android.view.View;
import android.widget.TextView;
import com.base.BaseActivity;
import com.example.administrator.oper.R;
import com.example.administrator.oper.fragmentfactorys.FragmentClassDetailsFactory;
import com.example.administrator.oper.fragmentfactorys.FragmentCourseCheckInFactory;
import com.example.administrator.oper.ui.widget.TabPageIndicator;
import com.utils.DisplayUtil;
import com.utils.SpanUtils;

/**
 * 版权：艺魔方公司 版权所有
 *
 * @author yang
 * 版本：1.0
 * 创建日期：2018/5/22
 * 描述：CourseCheckInActivity 课程签到
 */
public class CourseCheckInActivity extends BaseActivity implements View.OnClickListener {

    protected TextView mToolbarTitle;
    protected Toolbar mToolbar;
    protected TextView tvTextRight;
    protected TextView tvCheckInTitle;
    protected TextView tvCheckInNumbs;
    protected TextView tvCheckInTime;
    protected TextView tvCheckInAbsent;
    protected TextView tvCheckInSigned;
    protected TextView tvCheckInLeave;
    protected CardView cardviewAttendance;
    protected TabPageIndicator indicatorCourseCheckIn;
    protected ViewPager vpCourseCheckIn;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_course_check_in);
        initView();
        BasePagerAdapter adapter = new BasePagerAdapter(this.getSupportFragmentManager());
        vpCourseCheckIn.setAdapter(adapter);
        indicatorCourseCheckIn.setViewPager(vpCourseCheckIn);
        setTabPagerIndicator();
        initData();
    }


    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.tv_text_right) {

        } else if (view.getId() == R.id.cardviewAttendance) {

        }
    }

    private void initView() {
        mToolbarTitle = (TextView) findViewById(R.id.toolbar_title);
        mToolbarTitle.setText(getString(R.string.title_activity_course_check_in));
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setNavigationIcon(R.mipmap.left_back);
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        tvTextRight = (TextView) findViewById(R.id.tv_text_right);
        tvTextRight.setText("一键签到");
        tvTextRight.setOnClickListener(CourseCheckInActivity.this);
        tvCheckInTitle = (TextView) findViewById(R.id.tv_checkInTitle);
        tvCheckInNumbs = (TextView) findViewById(R.id.tv_checkInNumbs);
        tvCheckInTime = (TextView) findViewById(R.id.tv_checkInTime);
        tvCheckInAbsent = (TextView) findViewById(R.id.tv_checkInAbsent);
        tvCheckInSigned = (TextView) findViewById(R.id.tv_checkInSigned);
        tvCheckInLeave = (TextView) findViewById(R.id.tv_checkInLeave);
        cardviewAttendance = (CardView) findViewById(R.id.cardviewAttendance);
        cardviewAttendance.setOnClickListener(CourseCheckInActivity.this);
        indicatorCourseCheckIn = (TabPageIndicator) findViewById(R.id.indicator_courseCheckIn);
        vpCourseCheckIn = (ViewPager) findViewById(R.id.vp_courseCheckIn);
    }


    //导航栏设置
    private void setTabPagerIndicator() {
        indicatorCourseCheckIn.setIndicatorMode(TabPageIndicator.IndicatorMode.MODE_WEIGHT_NOEXPAND_SAME);
        // 设置模式，一定要先设置模式
//        indicator.setDividerColor(Color.parseColor("#00bbcf"));
// 设置分割线的颜色
//        indicator.setDividerPadding(DisplayUtil.dip2px(this, 0));
        // 设置底部导航线的颜色
        indicatorCourseCheckIn.setIndicatorColor(Color.parseColor("#fbd415"));
        // 设置底部导航线的高度
        indicatorCourseCheckIn.setIndicatorHeight(DisplayUtil.dip2px(this, 2));
        // 设置tab标题选中的颜色
        indicatorCourseCheckIn.setTextColorSelected(Color.parseColor("#333333"));
        // 设置tab标题未被选中的颜色
        indicatorCourseCheckIn.setTextColor(Color.parseColor("#a5a5a5"));

        // 设置字体大小
        indicatorCourseCheckIn.setTextSize((int) DisplayUtil.sp2px(this, 18));
        // 设置标题栏和viewPager的分割线
        indicatorCourseCheckIn.setUnderlineHeight(DisplayUtil.dip2px(this, 0));
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
            return FragmentCourseCheckInFactory.createForNoExpand(position);
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

    /**
     * 初始化数据
     */
    private void initData() {
        tvCheckInTitle.setText("初级钢琴体验课程");


        tvCheckInNumbs.setText(new SpanUtils()
                .append("学员人数 ")
                .setForegroundColor(Color.parseColor("#a5a5a5"))
                .setFontSize(48)
                .appendSpace(10)
                .append("10人")
                .setForegroundColor(Color.parseColor("#333333"))
                .setFontSize(48)
                .create());

        tvCheckInTime.setText(new SpanUtils()
                .append("时间")
                .setForegroundColor(Color.parseColor("#a5a5a5"))
                .setFontSize(48)
                .appendSpace(10)
                .append(" 2018-3-30 ")
                .setForegroundColor(Color.parseColor("#333333"))
                .setFontSize(48)
                .create());

        tvCheckInAbsent.setText(new SpanUtils()
                .append("●")
                .setForegroundColor(Color.parseColor("#3ed548"))
                .setFontSize(32)
                .appendSpace(20)
                .append("缺席：7人")
                .setAlign(Layout.Alignment.ALIGN_CENTER)
                .setForegroundColor(Color.parseColor("#a5a5a5"))
                .setFontSize(40)
                .create());

        tvCheckInSigned.setText(new SpanUtils()
                .append("●")
                .setForegroundColor(Color.parseColor("#2194ff"))
                .setFontSize(32)
                .appendSpace(20)
                .append("已签到：20人")
                .setAlign(Layout.Alignment.ALIGN_CENTER)
                .setForegroundColor(Color.parseColor("#a5a5a5"))
                .setFontSize(40)
                .create());
        tvCheckInLeave.setText(new SpanUtils()
                .append("●")
                .setForegroundColor(Color.parseColor("#f39019"))
                .setFontSize(32)
                .appendSpace(20)
                .append("请假：1人")
                .setAlign(Layout.Alignment.ALIGN_CENTER)
                .setForegroundColor(Color.parseColor("#a5a5a5"))
                .setFontSize(40)
                .create());
    }
}
