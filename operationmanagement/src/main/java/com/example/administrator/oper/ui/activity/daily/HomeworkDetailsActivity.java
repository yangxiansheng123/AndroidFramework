package com.example.administrator.oper.ui.activity.daily;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.aspsine.swipetoloadlayout.OnLoadMoreListener;
import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.base.BaseActivity;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.administrator.oper.R;
import com.example.administrator.oper.adapter.daily.HomeworkDetailsAdapter;
import com.example.administrator.oper.bean.daily.HomeworkDetailsBean;
import com.utils.SpanUtils;
import com.view.MultiStateView;

import java.util.ArrayList;
import java.util.List;

/**
 * 版权：艺魔方公司 版权所有
 *
 * @author yang
 * 版本：1.0
 * 创建日期：2018/5/5
 * 描述：HomeworkDetailsActivity 作业详情界面
 */
public class HomeworkDetailsActivity extends BaseActivity implements View.OnClickListener {

    protected TextView mToolbarTitle;
    protected Toolbar mToolbar;
    protected ImageView mImgRight;
    protected TextView tvHomeworkTitle;
    protected TextView tvHomeworkState;
    protected TextView tvHomewordDesc;
    protected TextView tvHomeworkByTime;
    protected TextView tvTotalCount;
    protected TextView tvUnpaid;
    protected TextView tvNotChanged;
    protected TextView tvHaveChanged;
    private LayoutInflater mInflater;
    protected RecyclerView mSwipeTarget;
    protected SwipeToLoadLayout mSwipeToLoad;
    protected MultiStateView mMultiStateView;
    private int nextPage = 0;
    private int maxPage = 10;
    private boolean isFirse = true;
    private HomeworkDetailsAdapter mHomeworkAdapter;
    private List<HomeworkDetailsBean> listData = new ArrayList<>();
    private View headview;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_homework_details);
        for (int i = 0; i < 5; i++) {
            listData.add(new HomeworkDetailsBean("gs"));
        }
        initView();

        mHomeworkAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

                Intent intent = new Intent();
                intent.setClass(HomeworkDetailsActivity.this, ClassroomAssignmentsActivity.class);
                startActivity(intent);
            }
        });

    }

    private void initData() {

        tvHomeworkTitle.setText("第三节课堂作业");

        tvHomeworkState.setText(new SpanUtils()
                .append("进行中")
                .setForegroundColor(Color.parseColor("#00cf3a"))
                .setFontSize(48)
                .create());

        tvHomewordDesc.setText("用自己拿手的程序语言编写程序用自己拿手的程序语言编写程序用自己拿手的程序语言编写");

        tvHomeworkByTime.setText(new SpanUtils()
                .append("截止于")
                .setForegroundColor(Color.parseColor("#0090ff"))
                .setFontSize(36)
                .append(" 2018-03-23")
                .setForegroundColor(Color.parseColor("#a5a5a5"))
                .setFontSize(36)
                .create());

        tvTotalCount.setText("总数:30人  ");
        tvUnpaid.setText("未交:10人 ");
        tvNotChanged.setText("未改:10人");
        tvHaveChanged.setText("已改:10人");
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent();
        if (view.getId() == R.id.img_right) {

        }
    }

    private void initView() {
        mInflater = LayoutInflater.from(this);
        headview = mInflater.inflate(R.layout.activity_homework_details_headview, null);
        mToolbarTitle = (TextView) findViewById(R.id.toolbar_title);
        mToolbarTitle.setText("xx课堂作业");
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
        mImgRight.setOnClickListener(HomeworkDetailsActivity.this);
        mImgRight.setImageDrawable(ContextCompat.getDrawable(this, R.mipmap.add_right));
        /**
         * headview
         */
        tvHomeworkTitle = (TextView) headview.findViewById(R.id.tv_homeworkTitle);
        tvHomeworkState = (TextView) headview.findViewById(R.id.tv_homeworkState);
        tvHomewordDesc = (TextView) headview.findViewById(R.id.tv_homewordDesc);
        tvHomeworkByTime = (TextView) headview.findViewById(R.id.tv_homeworkByTime);
        tvTotalCount = (TextView) headview.findViewById(R.id.tv_totalCount);
        tvUnpaid = (TextView) headview.findViewById(R.id.tv_Unpaid);
        tvNotChanged = (TextView) headview.findViewById(R.id.tv_notChanged);
        tvHaveChanged = (TextView) headview.findViewById(R.id.tv_haveChanged);
        mSwipeTarget = (RecyclerView) findViewById(R.id.swipe_target);
        mSwipeToLoad = (SwipeToLoadLayout) findViewById(R.id.swipe_to_load);
        mMultiStateView = (MultiStateView) findViewById(R.id.multiStateView);


        //话题
        mHomeworkAdapter = new HomeworkDetailsAdapter(R.layout.activity_homework_details_pattern, listData, this);
        mHomeworkAdapter.addHeaderView(headview);
        mSwipeTarget.setAdapter(mHomeworkAdapter);
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
//        mSwipeTarget.addItemDecoration(new DividerItemDecoration(this, 0));
        mSwipeTarget.setLayoutManager(manager);

        initData();
    }

    /**
     * 请求数据
     */
    private void postData() {
    }

    @Override
    public void onCompleted(int what) {
        super.onCompleted(what);
    }

    @Override
    public void onError(Throwable e, int what) {
        super.onError(e, what);
    }

    @Override
    public void onNext(Object object, int what) {
        super.onNext(object, what);
    }
}
