package com.example.administrator.oper.ui.activity.edu;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.aspsine.swipetoloadlayout.OnLoadMoreListener;
import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.base.BaseActivity;
import com.base.BaseToolbarActivity;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.administrator.oper.R;
import com.example.administrator.oper.adapter.edu.OneToOneDetailsAdapter;
import com.example.administrator.oper.bean.edu.ClassManagementDetailsBean;
import com.utils.SpanUtils;
import com.utils.ToolbarHelper;
import com.view.MultiStateView;

import java.util.ArrayList;
import java.util.List;

/**
 * 版权：艺魔方公司 版权所有
 *
 * @author yang
 * 版本：1.0
 * 创建日期：2018/5/5
 * 描述：OneToOneDetailsActivity 班级管理详情
 */
public class OneToOneDetailsActivity extends BaseToolbarActivity implements View.OnClickListener {

    protected TextView mToolbarTitle;
    protected Toolbar mToolbar;
    protected LinearLayout mLlAddOneToOneStu;
    protected RecyclerView mSwipeTarget;
    protected SwipeToLoadLayout mSwipeToLoad;
    protected MultiStateView mMultiStateView;
    private LayoutInflater mInflater;
    private View headview;
    private int nextPage = 0;
    private int maxPage = 10;
    private boolean isFirse = true;
    private OneToOneDetailsAdapter mClassManagementAdapter;
    private List<ClassManagementDetailsBean> listData = new ArrayList<>();
    private TextView mTimtable, mCategory, mTeacherName, mStudentCount;
    private LinearLayout mllSeeCourseArrangement;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_one_to_one_details;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        super.setContentView(R.layout.activity_one_to_one_details);
        for (int i = 0; i < 10; i++) {
            listData.add(new ClassManagementDetailsBean("s"));
        }
        initView();
        initData();
    }


    @Override
    protected void initToolbar(ToolbarHelper toolbarHelper) {

        toolbarHelper.setTitle(getString(R.string.title_activity_one_to_one_details));
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
        Intent intent = new Intent();
        if (view.getId() == R.id.ll_addOneToOneStu) {

            intent.setClass(this, AddOneToOneStudentsActivity.class);
            startActivity(intent);
        }
    }

    /**
     * 初始化控件
     */
    private void initView() {
        mInflater = LayoutInflater.from(this);
        mToolbarTitle = (TextView) findViewById(R.id.toolbar_title);
        mToolbarTitle.setText(getString(R.string.title_activity_one_to_one_details));

        headview = mInflater.inflate(R.layout.activity_one_to_one_details_headview, null);
        mTimtable = (TextView) headview.findViewById(R.id.tv_timetable);
        mCategory = (TextView) headview.findViewById(R.id.tv_category);
        mTeacherName = (TextView) headview.findViewById(R.id.tv_teacherName);
        mStudentCount = (TextView) headview.findViewById(R.id.tv_studentCount);
        mllSeeCourseArrangement = (LinearLayout) headview.findViewById(R.id.ll_seeCourseArrangement);

        mLlAddOneToOneStu = (LinearLayout) findViewById(R.id.ll_addOneToOneStu);
        mLlAddOneToOneStu.setOnClickListener(OneToOneDetailsActivity.this);
        mSwipeTarget = (RecyclerView) findViewById(R.id.swipe_target);
        mSwipeToLoad = (SwipeToLoadLayout) findViewById(R.id.swipe_to_load);
        mMultiStateView = (MultiStateView) findViewById(R.id.multiStateView);


        //话题
        mClassManagementAdapter = new OneToOneDetailsAdapter(R.layout.activity_one_to_one_details_pattern, listData, this);
        mClassManagementAdapter.addHeaderView(headview);
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
                        postClassMangDetailsData();
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
                        postClassMangDetailsData();
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
                intent.setClass(OneToOneDetailsActivity.this, OneToOneDetailsActivity.class);
                startActivity(intent);
            }
        });

        /**
         * 查看课程安排
         */
        mllSeeCourseArrangement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(OneToOneDetailsActivity.this, CourseArrangementListActivity.class);
                startActivity(intent);
            }
        });
    }

    /**
     * 请求数据
     */
    private void postClassMangDetailsData() {
    }

    /**
     * 初始化数据
     */
    private void initData() {
        mTimtable.setText("周二 12:00-12:45 / 周三 12:00...");
        mCategory.setText("钢琴类别");
        mTeacherName.setText(new SpanUtils()
                .append("●")
                .setForegroundColor(Color.parseColor("#3ed548"))
                .setFontSize(32)
                .appendSpace(20)
                .append("老师：")
                .setForegroundColor(Color.parseColor("#a5a5a5"))
                .setFontSize(48)
                .appendSpace(20)
                .append("苏亚")
                .setForegroundColor(Color.parseColor("#333333"))
                .setFontSize(48)
                .create());
        mStudentCount.setText(new SpanUtils()
                .append("●")
                .setForegroundColor(Color.parseColor("#f39019"))
                .setFontSize(32)
                .appendSpace(20)
                .append("学生：")
                .setForegroundColor(Color.parseColor("#a5a5a5"))
                .setFontSize(48)
                .appendSpace(20)
                .append("66")
                .setForegroundColor(Color.parseColor("#333333"))
                .setFontSize(48)
                .create());
    }
}
