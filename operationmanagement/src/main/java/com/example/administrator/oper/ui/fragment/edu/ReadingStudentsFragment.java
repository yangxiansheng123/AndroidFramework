package com.example.administrator.oper.ui.fragment.edu;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.aspsine.swipetoloadlayout.OnLoadMoreListener;
import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.base.BaseFragment;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.administrator.oper.R;
import com.example.administrator.oper.adapter.edu.ClassDetailsAdapter;
import com.example.administrator.oper.bean.edu.ClassManagementDetailsBean;
import com.example.administrator.oper.ui.activity.edu.OneToOneDetailsActivity;
import com.view.MultiStateView;

import java.util.ArrayList;
import java.util.List;

/**
 * 版权：艺魔方公司 版权所有
 *
 * @author yang
 * 版本：1.0
 * 创建日期：2018/5/14
 * 描述：ReadingStudentsFragment 在读学员
 */
public class ReadingStudentsFragment extends BaseFragment {

    protected View rootView;
    protected RecyclerView mSwipeTarget;
    protected SwipeToLoadLayout mSwipeToLoad;
    protected MultiStateView mMultiStateView;
    private ClassDetailsAdapter mClassDetailsAdapter;
    private int nextPage = 0;
    private int maxPage = 10;
    private boolean isFirse = true;
    private List<ClassManagementDetailsBean> listData = new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_reading_students, container, false);

        for (int i = 0; i < 10; i++) {
            listData.add(new ClassManagementDetailsBean("s"));
        }
        initView(rootView);
        return rootView;
    }

    private void initView(View rootView) {
        mSwipeTarget = (RecyclerView) rootView.findViewById(R.id.swipe_target);
        mSwipeToLoad = (SwipeToLoadLayout) rootView.findViewById(R.id.swipe_to_load);
        mMultiStateView = (MultiStateView) rootView.findViewById(R.id.multiStateView);

        //话题
        mClassDetailsAdapter = new ClassDetailsAdapter(R.layout.activity_one_to_one_details_pattern, listData, getActivity());
        mSwipeTarget.setAdapter(mClassDetailsAdapter);

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
        LinearLayoutManager manager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        mSwipeTarget.addItemDecoration(new DividerItemDecoration(getActivity(), 0));
        mSwipeTarget.setLayoutManager(manager);
    }

    /**
     * 请求数据*
     */
    private void postClassDetailsData() {
    }

}
