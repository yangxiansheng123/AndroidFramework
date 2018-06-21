package com.example.administrator.oper.ui.fragment.daily;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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
 * 版权：艺魔方公司 版权所有
 *
 * @author yang
 * 版本：1.0
 * 创建日期：2018/5/24
 * 描述：CourseCIAuditioningStuFragment 课程签到试听学员
 */
public class CourseCIAuditioningStuFragment extends Fragment {


    private View rootView;
    protected RecyclerView mSwipeTarget;
    protected SwipeToLoadLayout mSwipeToLoad;
    protected MultiStateView mMultiStateView;
    private CourseCIReadingStuAdapter mCIReadingStuAdapter;
    private List<ParentalDistributionBean> listData = new ArrayList<>();
    private int nextPage = 0;
    private int maxPage = 10;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_course_ciauditioning_stu, container, false);
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
