package com.example.administrator.oper.ui.activity.me;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.aspsine.swipetoloadlayout.OnLoadMoreListener;
import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.administrator.oper.R;
import com.example.administrator.oper.adapter.me.SettingManagementAdapter;
import com.example.administrator.oper.bean.me.SettingManagementBean;
import com.view.MultiStateView;

import java.util.ArrayList;
import java.util.List;

/**
 * 设置管理
 *
 * @author Administrator
 */
public class SettingManagementActivity extends AppCompatActivity implements View.OnClickListener {

    protected TextView mToolbarTitle;
    protected Toolbar mToolbar;
    protected RecyclerView mSwipeTarget;
    protected SwipeToLoadLayout mSwipeToLoad;
    protected MultiStateView mMultiStateView;
    protected LinearLayout llAddRoles;
    protected TextView tvTextRight;
    private int nextPage = 0;
    private int maxPage = 10;
    private boolean isFirse = true;
    private SettingManagementAdapter mSettingManagementAdapter;
    private List<SettingManagementBean> listData = new ArrayList<>();
    private int mEditMode = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_setting_management);
        for (int i = 0; i < 10; i++) {
            listData.add(new SettingManagementBean(""));
        }
        initView();

        mSettingManagementAdapter.setOnItemLongClickListener(new BaseQuickAdapter.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(BaseQuickAdapter adapter, View view, int position) {
                AlertDialog.Builder builder = new AlertDialog.Builder(SettingManagementActivity.this);
                builder.setTitle("数据")
                        .setMessage("确定删除此数据么？")
                        .setPositiveButton(getString(R.string.confirm), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                listData.remove(position);
                                mSettingManagementAdapter.notifyDataSetChanged();

                            }
                        })
                        .setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .show();
                return false;
            }
        });


        mSettingManagementAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter1, View view, int position) {
                SettingManagementBean myLive = listData.get(position);
                boolean isSelect = myLive.isSelect();
                if (!isSelect) {
                    myLive.setSelect(true);
                } else {
                    myLive.setSelect(false);
                }
                mSettingManagementAdapter.notifyDataSetChanged();
            }
        });
    }

    /**
     * 初始化控件
     */
    private void initView() {
        mToolbarTitle = (TextView) findViewById(R.id.toolbar_title);
        mToolbarTitle.setText("权限列表");
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setNavigationIcon(R.mipmap.left_back);
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        llAddRoles = (LinearLayout) findViewById(R.id.ll_addRoles);
        llAddRoles.setOnClickListener(SettingManagementActivity.this);
        mSwipeTarget = (RecyclerView) findViewById(R.id.swipe_target);
        mSwipeToLoad = (SwipeToLoadLayout) findViewById(R.id.swipe_to_load);
        mMultiStateView = (MultiStateView) findViewById(R.id.multiStateView);


        //话题
        mSettingManagementAdapter = new SettingManagementAdapter(R.layout.activity_setting_management_pattern, listData, this);
        mSwipeTarget.setAdapter(mSettingManagementAdapter);
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
        tvTextRight = (TextView) findViewById(R.id.tv_text_right);
        tvTextRight.setText("编辑");
        tvTextRight.setOnClickListener(SettingManagementActivity.this);

    }

    /**
     * 请求数据
     */
    private void postData() {
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent();
        if (view.getId() == R.id.ll_addRoles) {
            intent.setClass(this, AddRolesActivity.class);
            startActivity(intent);
        } else if (view.getId() == R.id.tv_text_right) {

            if (mEditMode == 1) {
                mSettingManagementAdapter.setEditMode(mEditMode);
                mEditMode--;
            } else {
                mSettingManagementAdapter.setEditMode(mEditMode);
                mEditMode++;
            }
        }
    }
}
