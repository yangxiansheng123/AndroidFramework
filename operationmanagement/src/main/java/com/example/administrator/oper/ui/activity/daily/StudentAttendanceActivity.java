package com.example.administrator.oper.ui.activity.daily;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import com.aspsine.swipetoloadlayout.OnLoadMoreListener;
import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.base.BaseActivity;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.administrator.oper.R;
import com.example.administrator.oper.adapter.daily.ParentalDistributionAdapter;
import com.example.administrator.oper.adapter.daily.StudentAttendanceAdapter;
import com.example.administrator.oper.bean.daily.ParentalDistributionBean;
import com.example.administrator.oper.bean.daily.StudentAttendanceBean;
import com.view.MultiStateView;

import java.util.ArrayList;
import java.util.List;

/**
 * 版权：艺魔方公司 版权所有
 *
 * @author yang
 * 版本：1.0
 * 创建日期：2018/5/3
 * 描述：StudentAttendanceActivity
 */
public class StudentAttendanceActivity extends BaseActivity implements View.OnClickListener {

    protected TextView mToolbarTitle;
    protected Toolbar mToolbar;
    protected TextView mTvTextRight;
    protected RecyclerView mSwipeTarget;
    protected SwipeToLoadLayout mSwipeToLoad;
    protected MultiStateView mMultiStateView;
    private StudentAttendanceAdapter mClassManagementAdapter;
    private int nextPage = 0;
    private int maxPage = 10;
    private boolean isFirse = true;
    private List<StudentAttendanceBean> listData = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_student_attendance);
        for (int i = 0; i < 10; i++) {
            listData.add(new StudentAttendanceBean(""));
        }
        initView();
        mClassManagementAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent();
                intent.setClass(StudentAttendanceActivity.this, CourseCheckInActivity.class);
                startActivity(intent);
            }
        });
    }

    /**
     * 初始化控件
     */
    private void initView() {
        mToolbarTitle = (TextView) findViewById(R.id.toolbar_title);
        mToolbarTitle.setText(getString(R.string.title_activity_student_attendance));
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setNavigationIcon(R.mipmap.left_back);
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        mTvTextRight = (TextView) findViewById(R.id.tv_text_right);
        mTvTextRight.setOnClickListener(StudentAttendanceActivity.this);
        mTvTextRight.setText("");
        mSwipeTarget = (RecyclerView) findViewById(R.id.swipe_target);
        mSwipeToLoad = (SwipeToLoadLayout) findViewById(R.id.swipe_to_load);
        mMultiStateView = (MultiStateView) findViewById(R.id.multiStateView);
        //话题
        mClassManagementAdapter = new StudentAttendanceAdapter(R.layout.activity_student_attendance_pattern, listData, this);
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
                        postData();
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
                        postData();
                        mSwipeToLoad.setLoadingMore(false);
                    }
                }, 1000);
            }
        });
        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mSwipeTarget.addItemDecoration(new DividerItemDecoration(this, 0));
        mSwipeTarget.setLayoutManager(manager);
    }

    /**
     * 请求数据
     */
    private void postData() {
    }


    /**
     * 处理点击事件
     *
     * @param view
     */
    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.tv_text_right) {

        }
    }
}
