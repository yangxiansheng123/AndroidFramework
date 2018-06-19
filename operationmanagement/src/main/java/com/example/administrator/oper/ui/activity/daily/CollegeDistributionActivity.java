package com.example.administrator.oper.ui.activity.daily;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.aspsine.swipetoloadlayout.OnLoadMoreListener;
import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.base.BaseActivity;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.administrator.oper.R;
import com.example.administrator.oper.adapter.daily.CollegeDistributionAdapter;
import com.example.administrator.oper.bean.edu.StudentManagementBean;
import com.view.MultiStateView;

import java.util.ArrayList;
import java.util.List;

/**
 * 版权：艺魔方公司 版权所有
 *
 * @author yang
 * 版本：1.0
 * 创建日期：2018/5/3
 * 描述：CollegeDistributionActivity
 */
public class CollegeDistributionActivity extends BaseActivity implements View.OnClickListener, CollegeDistributionAdapter.ICollegeDistribution {

    protected TextView mToolbarTitle;
    protected Toolbar mToolbar;
    protected TextView tvSalesConsultant;
    protected LinearLayout llSalesConsultant;
    protected RecyclerView mSwipeTarget;
    protected SwipeToLoadLayout mSwipeToLoad;
    protected MultiStateView mMultiStateView;
    protected TextView tvComplete;
    private int nextPage = 0;
    private int maxPage = 10;
    private boolean isFirse = true;
    private List<StudentManagementBean> listData = new ArrayList<>();
    private CollegeDistributionAdapter mCollegeDistributionAdapter;
    private LayoutInflater mInflater;
    private View viewFoot;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_college_distribution);
        for (int i = 0; i < 10; i++) {
            listData.add(new StudentManagementBean("gs"));
        }
        initView();

        mCollegeDistributionAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                StudentManagementBean myLive = listData.get(position);
                boolean isSelect = myLive.isSelect();
                if (!isSelect) {
                    myLive.setSelect(true);
                } else {
                    myLive.setSelect(false);
                }
                adapter.notifyDataSetChanged();
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
        if (view.getId() == R.id.ll_salesConsultant) {
            intent.setClass(CollegeDistributionActivity.this, SelectParentalDistributionActivity.class);
            startActivity(intent);

        } else if (view.getId() == R.id.tv_complete) {

            for (int i = 0; i < listData.size(); i++) {
                if (listData.get(i).isSelect) {
                    Log.e("选中：", " - " + i);
                }
            }
        }
    }

    /**
     * 初始化控件
     */
    private void initView() {
        mInflater = LayoutInflater.from(this);
        viewFoot = mInflater.inflate(R.layout.activity_head_footer_view, null);
        mToolbarTitle = (TextView) findViewById(R.id.toolbar_title);
        mToolbarTitle.setText(getString(R.string.title_activity_college_distribution));
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setNavigationIcon(R.mipmap.left_back);
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        tvSalesConsultant = (TextView) findViewById(R.id.tv_salesConsultant);
        llSalesConsultant = (LinearLayout) findViewById(R.id.ll_salesConsultant);
        llSalesConsultant.setOnClickListener(CollegeDistributionActivity.this);
        mSwipeTarget = (RecyclerView) findViewById(R.id.swipe_target);
        mSwipeToLoad = (SwipeToLoadLayout) findViewById(R.id.swipe_to_load);
        mMultiStateView = (MultiStateView) findViewById(R.id.multiStateView);


        //话题
        mCollegeDistributionAdapter = new CollegeDistributionAdapter(R.layout.activity_college_distribution_pattern, listData, this, this);
        mCollegeDistributionAdapter.addFooterView(viewFoot);
        mSwipeTarget.setAdapter(mCollegeDistributionAdapter);
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
        tvComplete = (TextView) findViewById(R.id.tv_complete);
        tvComplete.setOnClickListener(CollegeDistributionActivity.this);
    }

    /**
     * 请求数据
     */
    private void postData() {
    }

    @Override
    public void chexbBoxView(ImageView chexb_browseCourse) {

    }
}
