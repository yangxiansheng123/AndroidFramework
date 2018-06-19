package com.example.administrator.oper.ui.activity.daily;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import com.aspsine.swipetoloadlayout.OnLoadMoreListener;
import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.example.administrator.oper.R;
import com.example.administrator.oper.adapter.daily.RenewRemindingAdapter;
import com.example.administrator.oper.bean.daily.RenewRemindingBean;
import com.view.MultiStateView;
import com.zaaach.toprightmenu.MenuItem;
import com.zaaach.toprightmenu.TopRightMenu;

import java.util.ArrayList;
import java.util.List;


/**
 * 版权：艺魔方公司 版权所有
 *
 * @author yang
 * 版本：1.0
 * 创建日期：2018/5/3
 * 描述：RenewalRemindingActivity
 */
public class RenewalRemindingActivity extends AppCompatActivity implements View.OnClickListener ,RenewRemindingAdapter.ICheckData {

    protected TextView mToolbarTitle;
    protected Toolbar mToolbar;
    protected ImageView mImgRight;
    protected TextView tvDistribution;
    protected RecyclerView mSwipeTarget;
    protected SwipeToLoadLayout mSwipeToLoad;
    protected MultiStateView mMultiStateView;
    private TopRightMenu mTopRightMenu;
    private int width, height;
    private RenewRemindingAdapter mClassManagementAdapter;
    private int nextPage = 0;
    private int maxPage = 10;
    private boolean isFirse = true;
    private LayoutInflater mInflater;
    /**
     * 列表数据
     */
    private List<RenewRemindingBean> listData = new ArrayList<>();
    /**
     * 选中后的数据
     */
    private List<RenewRemindingBean> checkListData = new ArrayList<>();
    private View viewFoot;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_renewal_reminding);
        for (int i = 0; i < 10; i++) {
            listData.add(new RenewRemindingBean("gs"+i));
        }
        initView();
        calculationScreen();
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
            topRightDialog();
        } else if (view.getId() == R.id.tv_distribution) {
            intent.setClass(RenewalRemindingActivity.this, SelectParentalDistributionActivity.class);
            startActivity(intent);
        }
    }

    /**
     * 初始化控件
     */
    private void initView() {
        mInflater = LayoutInflater.from(this);
        viewFoot = mInflater.inflate(R.layout.activity_head_footer_view, null);

        mToolbarTitle = (TextView) findViewById(R.id.toolbar_title);
        mToolbarTitle.setText(getString(R.string.title_activity_renewal_reminding));
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
        mImgRight.setOnClickListener(RenewalRemindingActivity.this);
        mImgRight.setImageDrawable(getResources().getDrawable(R.mipmap.top_right_menu));
        tvDistribution = (TextView) findViewById(R.id.tv_distribution);
        tvDistribution.setOnClickListener(RenewalRemindingActivity.this);
        mSwipeTarget = (RecyclerView) findViewById(R.id.swipe_target);
        mSwipeToLoad = (SwipeToLoadLayout) findViewById(R.id.swipe_to_load);
        mMultiStateView = (MultiStateView) findViewById(R.id.multiStateView);

        //话题
        mClassManagementAdapter = new RenewRemindingAdapter(R.layout.activity_renewal_reminding_pattern, listData, this,this);
       mClassManagementAdapter.addHeaderView(viewFoot);
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
     * 右上角弹窗
     */
    private void topRightDialog() {
        mTopRightMenu = new TopRightMenu(RenewalRemindingActivity.this);
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
                .addMenuItem(new MenuItem(R.mipmap.remind_setting, "提醒设置"))
                .addMenuItem(new MenuItem(R.mipmap.record, "操作记录"))
                .setOnMenuItemClickListener(new TopRightMenu.OnMenuItemClickListener() {
                    @Override
                    public void onMenuItemClick(int position) {
                        Intent intent = new Intent();
                        if (position == 0) {
                            intent.setClass(RenewalRemindingActivity.this, ReminderSettingsActivity.class);
                            startActivity(intent);
                        }
                    }
                })
                .showAsDropDown(mImgRight, -width / 4 - width / 6, height / 32);
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
     * 选中的item
     * @param data
     */
    @Override
    public void checkItem(RenewRemindingBean data) {

    }
}
