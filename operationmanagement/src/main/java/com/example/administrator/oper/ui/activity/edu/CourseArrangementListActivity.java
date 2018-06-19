package com.example.administrator.oper.ui.activity.edu;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import com.aspsine.swipetoloadlayout.OnLoadMoreListener;
import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.base.BaseActivity;
import com.example.administrator.oper.R;
import com.example.administrator.oper.adapter.edu.ClassDetailsAdapter;
import com.example.administrator.oper.adapter.edu.CourseArrangementListAdapter;
import com.example.administrator.oper.bean.edu.ClassManagementDetailsBean;
import com.view.MultiStateView;
import com.zaaach.toprightmenu.TopRightMenu;

import java.util.ArrayList;
import java.util.List;

/**
 * 版权：艺魔方公司 版权所有
 *
 * @author yang
 * 版本：1.0
 * 创建日期：2018/5/15
 * 描述：CourseArrangementListActivity 查看课程-->课日程列表
 */
public class CourseArrangementListActivity extends BaseActivity implements View.OnClickListener {

    protected TextView mToolbarTitle;
    protected Toolbar mToolbar;
    protected ImageView mImgRight;
    protected RecyclerView mSwipeTarget;
    protected SwipeToLoadLayout mSwipeToLoad;
    protected MultiStateView mMultiStateView;
    private int nextPage = 0;
    private int maxPage = 10;
    private boolean isFirse = true;
    private int width, height;
    private CourseArrangementListAdapter mCourseArrangementListAdapter;
    private List<ClassManagementDetailsBean> listData = new ArrayList<>();
    private TopRightMenu mTopRightMenu;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_course_arrangement_list);
        calculationScreen();
        for (int i = 0; i < 10; i++) {
            listData.add(new ClassManagementDetailsBean("s"));
        }
        initView();
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
     * 初始化控件
     */
    private void initView() {
        mToolbarTitle = (TextView) findViewById(R.id.toolbar_title);
        mToolbarTitle.setText(getString(R.string.title_activity_course_arrangement_list));
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
        mImgRight.setOnClickListener(CourseArrangementListActivity.this);
        mImgRight.setImageDrawable(ContextCompat.getDrawable(this, R.mipmap.screen));
        mSwipeTarget = (RecyclerView) findViewById(R.id.swipe_target);
        mSwipeToLoad = (SwipeToLoadLayout) findViewById(R.id.swipe_to_load);
        mMultiStateView = (MultiStateView) findViewById(R.id.multiStateView);
        //话题
        mCourseArrangementListAdapter = new CourseArrangementListAdapter(R.layout.activity_course_arrangement_list_pattern, listData, this);
        mSwipeTarget.setAdapter(mCourseArrangementListAdapter);
        mSwipeToLoad.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        nextPage = 0;
                        listData.clear();
                        isFirse = true;
                        postClassDetailsData();
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
                        postClassDetailsData();
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
    private void postClassDetailsData() {
    }


    /**
     * 右上角弹窗
     *
     * @param img
     */
    private void topRightDialog(View img) {
        mTopRightMenu = new TopRightMenu(CourseArrangementListActivity.this);
        mTopRightMenu
                //默认高度480
                .setHeight(height / 3)
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
                .addMenuItem(new com.zaaach.toprightmenu.MenuItem(R.mipmap.all_icon, "全部(95）"))
                .addMenuItem(new com.zaaach.toprightmenu.MenuItem(R.mipmap.end_icon, "已结束(25）"))
                .addMenuItem(new com.zaaach.toprightmenu.MenuItem(R.mipmap.start_icon, "上课中(1）"))
                .addMenuItem(new com.zaaach.toprightmenu.MenuItem(R.mipmap.nots_icon, "未开始(69）"))
                .setOnMenuItemClickListener(new TopRightMenu.OnMenuItemClickListener() {
                    @Override
                    public void onMenuItemClick(int position) {
                        if (position == 0) {
                        } else if (position == 1) {
                        } else if (position == 2) {
                        } else if (position == 3) {
                        }
                    }
                })
                .showAsDropDown(img, -width / 4 - width / 6, height / 40);
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
