package com.example.administrator.oper.ui.activity.edu;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.*;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.aspsine.swipetoloadlayout.OnLoadMoreListener;
import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.base.BaseToolbarActivity;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.administrator.oper.R;
import com.example.administrator.oper.adapter.edu.StudentManagementAdapter;
import com.example.administrator.oper.bean.edu.StudentManagementBean;
import com.example.administrator.oper.ui.activity.daily.CollegeEntryActivity;
import com.utils.ToolbarHelper;
import com.view.MultiStateView;
import com.zaaach.toprightmenu.TopRightMenu;
import org.wangchenlong.datescroller.widget.DateScrollerDialog;
import org.wangchenlong.datescroller.widget.data.Type;
import org.wangchenlong.datescroller.widget.listener.OnDateSetListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;


/**
 * @author Administrator
 * 学员管理
 */
public class StudentManagementActivity extends BaseToolbarActivity implements View.OnClickListener {


    private static final long HUNDRED_YEARS = 100L * 365 * 1000 * 60 * 60 * 24L; // 100年
    protected LinearLayout llPrompt;
    protected TextView tvReset;
    protected TextView tvEnsure;
    private SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
    private long mLastTime = System.currentTimeMillis(); // 上次设置的时间
    private String birthday;
    private String istinguishdTime;
    protected TextView toolbarTitle;
    protected TextView toolbarMenuTitle;
    protected Toolbar toolbar;
    protected TextView mTvStartTime;
    protected TextView mTvEndTime;
    private LayoutInflater mInflater;
    protected LinearLayout llDrawerContent;
    protected DrawerLayout mDrawerLayout;
    protected RecyclerView mSwipeTarget;
    protected SwipeToLoadLayout mSwipeToLoad;
    protected MultiStateView mMultiStateView;
    private TopRightMenu mTopRightMenu;
    private int width, height;
    private StudentManagementAdapter mClassManagementAdapter;
    private int nextPage = 0;
    private int maxPage = 10;
    private boolean isFirse = true;
    private List<StudentManagementBean> listData = new ArrayList<>();
    private View headView;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_student_management;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        for (int i = 0; i < 10; i++) {
            listData.add(new StudentManagementBean("gs"));
        }
        initView();
        calculationScreen();

    }


    @Override
    protected void initToolbar(ToolbarHelper toolbarHelper) {
        toolbarHelper.setTitle("学员管理");
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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toolbar_demo, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int i = item.getItemId();
        if (i == R.id.toolbar_action1) {
            mDrawerLayout.openDrawer(llDrawerContent);
            return true;
        } else if (i == R.id.toolbar_action2) {// do something
            View img = this.findViewById(R.id.toolbar_action2);
            topRightDialog(img);
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    /**
     * 处理点击事件
     *
     * @param view
     */
    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.tv_startTime) {
            showDate();
            istinguishdTime = "start";
        } else if (view.getId() == R.id.tv_endTime) {
            showDate();
            istinguishdTime = "end";
        } else if (view.getId() == R.id.tv_reset) {
            mDrawerLayout.closeDrawers();
        } else if (view.getId() == R.id.tv_ensure) {

        }
    }

    /**
     * 初始化控件
     */
    private void initView() {
        mInflater = LayoutInflater.from(this);
        headView = mInflater.inflate(R.layout.student_management_headview, null);
        toolbarTitle = (TextView) findViewById(R.id.toolbar_title);
        toolbarMenuTitle = (TextView) findViewById(R.id.toolbar_menu_title);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        llDrawerContent = (LinearLayout) findViewById(R.id.ll_drawerContent);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        mSwipeTarget = (RecyclerView) findViewById(R.id.swipe_target);
        mSwipeToLoad = (SwipeToLoadLayout) findViewById(R.id.swipe_to_load);
        mMultiStateView = (MultiStateView) findViewById(R.id.multiStateView);


        //话题
        mClassManagementAdapter = new StudentManagementAdapter(R.layout.activity_student_management_pattern, listData, this);
        mClassManagementAdapter.addHeaderView(headView);
        mSwipeTarget.setAdapter(mClassManagementAdapter);
        mSwipeToLoad.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        nextPage = 0;
                        listData.clear();
                        isFirse = true;
                        postClassMangData();
                        mSwipeToLoad.setRefreshing(false);
                    }
                }, 1000);
            }
        });
        mSwipeToLoad.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        nextPage = nextPage + maxPage;
                        postClassMangData();
                        mSwipeToLoad.setLoadingMore(false);
                    }
                }, 1000);
            }
        });
        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mSwipeTarget.addItemDecoration(new DividerItemDecoration(this, 0));
        mSwipeTarget.setLayoutManager(manager);

        /**
         * item点击事件
         */
        mClassManagementAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

                Intent intent = new Intent();
//                intent.setClass(StudentManagementActivity.this, OneToOneDetailsActivity.class);
//                startActivity(intent);
            }
        });
        mTvStartTime = (TextView) findViewById(R.id.tv_startTime);
        mTvStartTime.setOnClickListener(StudentManagementActivity.this);
        mTvEndTime = (TextView) findViewById(R.id.tv_endTime);
        mTvEndTime.setOnClickListener(StudentManagementActivity.this);
        tvReset = (TextView) findViewById(R.id.tv_reset);
        tvReset.setOnClickListener(StudentManagementActivity.this);
        tvEnsure = (TextView) findViewById(R.id.tv_ensure);
        tvEnsure.setOnClickListener(StudentManagementActivity.this);
    }

    /**
     * 请求数据
     */
    private void postClassMangData() {
    }

    /**
     * 右上角弹窗
     *
     * @param img
     */
    private void topRightDialog(View img) {
        mTopRightMenu = new TopRightMenu(StudentManagementActivity.this);
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
                .addMenuItem(new com.zaaach.toprightmenu.MenuItem(R.mipmap.add, "添加客户"))
                .addMenuItem(new com.zaaach.toprightmenu.MenuItem(R.mipmap.batch, "批量导入"))
                .setOnMenuItemClickListener(new TopRightMenu.OnMenuItemClickListener() {
                    @Override
                    public void onMenuItemClick(int position) {
                        Intent intent = new Intent();
                        if (position == 0) {
                            intent.setClass(StudentManagementActivity.this, CollegeEntryActivity.class);
                            startActivity(intent);
                        } else if (position == 1) {
                        }
                    }
                })
                .showAsDropDown(img, -width / 4 - width / 7, height / 40);
    }

    /**
     * 计算屏幕宽高
     */
    private void calculationScreen() {
        WindowManager wm = this.getWindowManager();
        width = wm.getDefaultDisplay().getWidth();
        height = wm.getDefaultDisplay().getHeight();
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
                .setTitleStringId("请选择时间")
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
                mTvStartTime.setText(startTime);
                mTvStartTime.setTextColor(Color.parseColor("#333333"));
            } else {
                endTime = birthday;
                mTvEndTime.setText(endTime);
                mTvEndTime.setTextColor(Color.parseColor("#333333"));
            }
        }
    };

    public String getDateToString(long time) {
        Date d = new Date(time);
        return sf.format(d);
    }
}
