package com.example.administrator.oper.ui.activity.fiance;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import com.aspsine.swipetoloadlayout.OnLoadMoreListener;
import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.base.BaseToolbarActivity;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.administrator.oper.R;
import com.example.administrator.oper.adapter.finance.PayrollsListAdapter;
import com.example.administrator.oper.bean.MyHomeworkBean;
import com.utils.ToolbarHelper;
import com.view.MultiStateView;

import java.util.ArrayList;
import java.util.List;

/**
 * 版权：艺魔方公司 版权所有
 *
 * @author yang
 * 版本：1.0
 * 创建日期：2018/5/3
 * 描述：PayrollsActivity
 */
public class PayrollsActivity extends BaseToolbarActivity implements View.OnClickListener {


    protected RecyclerView mSwipeTarget;
    protected SwipeToLoadLayout mSwipeToLoad;
    protected MultiStateView mMultiStateView;
    private int nextPage = 0;
    private int maxPage = 10;
    private boolean isFirse = true;
    private PayrollsListAdapter mListAdapter;
    private List<MyHomeworkBean> listData = new ArrayList<>();

    @Override
    protected int getContentViewId() {
        return R.layout.activity_payrolls;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_payrolls);

        for (int i = 0; i < 10; i++) {
            listData.add(new MyHomeworkBean("gs"));
        }
        initView();
    }


    @Override
    protected void initToolbar(ToolbarHelper toolbarHelper) {
        toolbarHelper.setTitle(getString(R.string.title_activity_payrolls));
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
            return true;
        } else if (i == R.id.toolbar_action2) {// do something
            Intent intent = new Intent();
            intent.setClass(PayrollsActivity.this, AddEmployeeWagesActivity.class);
            startActivity(intent);
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        menu.findItem(R.id.toolbar_action1).setVisible(false);
        return super.onPrepareOptionsMenu(menu);
    }


    /**
     * 初始化控件
     */
    private void initView() {
        mSwipeTarget = (RecyclerView) findViewById(R.id.swipe_target);
        mSwipeToLoad = (SwipeToLoadLayout) findViewById(R.id.swipe_to_load);
        mMultiStateView = (MultiStateView) findViewById(R.id.multiStateView);

        //话题
        mListAdapter = new PayrollsListAdapter(R.layout.activity_payrolls_pattern, listData, this);
        mSwipeTarget.setAdapter(mListAdapter);
        mSwipeToLoad.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        nextPage = 0;
                        listData.clear();
                        isFirse = true;
                        postPayrollsData();
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
                        postPayrollsData();
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
        mListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

                Intent intent = new Intent();
                intent.setClass(PayrollsActivity.this, PersonalPayrollActivity.class);
                startActivity(intent);
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
//        if (view.getId() == R.id.textView16) {
//            intent.setClass(PayrollsActivity.this, PersonalPayrollActivity.class);
//            startActivity(intent);
//        }
    }


    /**
     * 请求数据
     */
    private void postPayrollsData() {
    }

    @Override
    protected void onDestroy() {
        if (mListAdapter != null) {
            mListAdapter = null;
        }
        super.onDestroy();
    }
}
