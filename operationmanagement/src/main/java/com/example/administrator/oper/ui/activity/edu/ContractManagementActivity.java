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
import com.base.BaseToolbarActivity;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.administrator.oper.R;
import com.example.administrator.oper.adapter.edu.ClassManagementAdapter;
import com.example.administrator.oper.adapter.edu.ContractManagementAdapter;
import com.example.administrator.oper.bean.edu.ContractManagementBean;
import com.utils.ToolbarHelper;
import com.view.MultiStateView;

import java.util.ArrayList;
import java.util.List;

/**
 * 版权：艺魔方公司 版权所有
 *
 * @author yang
 * 版本：1.0
 * 创建日期：2018/5/2
 * 描述：ContractManagementActivity
 */
public class ContractManagementActivity extends BaseToolbarActivity implements View.OnClickListener {

    protected TextView mToolbarTitle;
    protected Toolbar mToolbar;
    protected ImageView mTmgRight;
    protected RecyclerView mSwipeTarget;
    protected SwipeToLoadLayout mSwipeToLoad;
    protected MultiStateView mMultiStateView;
    private ContractManagementAdapter mContractManagementAdapter;
    private int nextPage = 0;
    private int maxPage = 10;
    private boolean isFirse = true;
    private List<ContractManagementBean> listData = new ArrayList<>();


    @Override
    protected int getContentViewId() {
        return R.layout.activity_contract_management;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        for (int i = 0; i < 10; i++) {
            listData.add(new ContractManagementBean("gs"));
        }
        initView();
    }


    @Override
    protected void initToolbar(ToolbarHelper toolbarHelper) {

    }

    /**
     * 初始化控件
     */
    private void initView() {
        mToolbarTitle = (TextView) findViewById(R.id.toolbar_title);
        mToolbarTitle.setText(getString(R.string.title_activity_contract_management));
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setNavigationIcon(R.mipmap.left_back);
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        mTmgRight = (ImageView) findViewById(R.id.img_right);
        mTmgRight.setOnClickListener(ContractManagementActivity.this);
        mTmgRight.setImageDrawable(ContextCompat.getDrawable(this, R.mipmap.add1));
        mSwipeTarget = (RecyclerView) findViewById(R.id.swipe_target);
        mSwipeToLoad = (SwipeToLoadLayout) findViewById(R.id.swipe_to_load);
        mMultiStateView = (MultiStateView) findViewById(R.id.multiStateView);

        //话题
        mContractManagementAdapter = new ContractManagementAdapter(R.layout.activity_contract_management_pattern, listData, this);
        mSwipeTarget.setAdapter(mContractManagementAdapter);
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
        mContractManagementAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

//                Intent intent = new Intent();
//                intent.setClass(ContractManagementActivity.this, OneToOneDetailsActivity.class);
//                startActivity(intent);
            }
        });
    }


    /**
     * 请求数据
     */
    private void postClassMangData() {
    }


    /**
     * 处理点击事件
     *
     * @param view
     */
    @Override
    public void onClick(View view) {
        Intent intent = new Intent();
        if (view.getId() == R.id.img_right) {
            intent.setClass(this, AddMealActivity.class);
            startActivity(intent);
        }
    }
}
