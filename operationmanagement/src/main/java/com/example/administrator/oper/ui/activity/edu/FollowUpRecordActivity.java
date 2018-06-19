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
import android.widget.ImageView;
import android.widget.TextView;
import com.aspsine.swipetoloadlayout.OnLoadMoreListener;
import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.base.BaseActivity;
import com.example.administrator.oper.R;
import com.example.administrator.oper.adapter.edu.FollowUpRecordAdapter;
import com.example.administrator.oper.bean.edu.ClassManagementDetailsBean;
import com.example.administrator.oper.bean.edu.FollowUpRecordBean;
import com.view.MultiStateView;

import java.util.ArrayList;
import java.util.List;

/**
 * 版权：艺魔方公司 版权所有
 *
 * @author yang
 * 版本：1.0
 * 创建日期：2018/5/15
 * 描述：FollowUpRecordActivity 跟进记录
 */
public class FollowUpRecordActivity extends BaseActivity implements View.OnClickListener {

    protected TextView mToolbarTitle;
    protected Toolbar mToolbar;
    protected ImageView mImgRight;
    protected RecyclerView mSwipeTarget;
    protected SwipeToLoadLayout mSwipeToLoad;
    protected MultiStateView mMultiStateView;
    private FollowUpRecordAdapter mFollowUpRecordAdapter;
    private int nextPage = 0;
    private int maxPage = 10;
    private boolean isFirse = true;
    private List<FollowUpRecordBean> listData = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_follow_up_record);
        for (int i=0;i<10;i++){
            listData.add(new FollowUpRecordBean("s"));
        }
        initView();
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent();
        if (view.getId() == R.id.img_right) {
            intent.setClass(this, AddARecordActivity.class);
            startActivity(intent);
        }
    }

    /**
     * 初始化控件
     */
    private void initView() {
        mToolbarTitle = (TextView) findViewById(R.id.toolbar_title);
        mToolbarTitle.setText(getString(R.string.title_activity_follow_up_record));
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
        mImgRight.setOnClickListener(FollowUpRecordActivity.this);
        mImgRight.setImageDrawable(ContextCompat.getDrawable(this, R.mipmap.add1));
        mSwipeTarget = (RecyclerView) findViewById(R.id.swipe_target);
        mSwipeToLoad = (SwipeToLoadLayout) findViewById(R.id.swipe_to_load);
        mMultiStateView = (MultiStateView) findViewById(R.id.multiStateView);

        //话题
        mFollowUpRecordAdapter = new FollowUpRecordAdapter(R.layout.activity_follow_up_record_pattern, listData, this);
        mSwipeTarget.setAdapter(mFollowUpRecordAdapter);
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

}
