package com.example.administrator.oper.ui.fragment.daily;

import android.content.Context;
import android.net.Uri;
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
import com.example.administrator.oper.R;
import com.example.administrator.oper.adapter.daily.CourseCIReadingStuAdapter;
import com.example.administrator.oper.bean.daily.ParentalDistributionBean;
import com.view.MultiStateView;

import java.util.ArrayList;
import java.util.List;

/**
 课程签到  在读学员
 */
public class CourseCIReadingStuFragment extends Fragment {


    private View rootView;
    protected RecyclerView mSwipeTarget;
    protected SwipeToLoadLayout mSwipeToLoad;
    protected MultiStateView mMultiStateView;
    private CourseCIReadingStuAdapter mCIReadingStuAdapter;
    private List<ParentalDistributionBean> listData = new ArrayList<>();
    private int nextPage = 0;
    private int maxPage = 10;
    private boolean isFirse = true;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_course_cireading_stu, container, false);

        for (int i = 0; i < 10; i++) {
            listData.add(new ParentalDistributionBean(""));
        }
        initView(rootView);
        return rootView;
    }

    private void initView(View rootView) {
        mCIReadingStuAdapter= new CourseCIReadingStuAdapter(R.layout.fragment_course_cireading_stu_pattern, listData, getActivity());
        mSwipeTarget = (RecyclerView) rootView.findViewById(R.id.swipe_target);
        mSwipeToLoad = (SwipeToLoadLayout) rootView.findViewById(R.id.swipe_to_load);
        mMultiStateView = (MultiStateView) rootView.findViewById(R.id.multiStateView);

        mSwipeTarget.setAdapter(mCIReadingStuAdapter);
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
        LinearLayoutManager manager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        mSwipeTarget.addItemDecoration(new DividerItemDecoration(getActivity(), 0));
        mSwipeTarget.setLayoutManager(manager);
    }

    /**
     * 请求数据
     */
    private void postData() {
    }
}
