package com.androidframework.ui.activity;

import android.os.Handler;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.androidframework.R;
import com.androidframework.bean.ExampleBean;
import com.androidframework.manager.Constants;
import com.androidframework.manager.Manager;
import com.androidframework.ui.adapter.ExampleAdapter;
import com.aspsine.swipetoloadlayout.OnLoadMoreListener;
import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.base.BaseActivity;
import com.base.BaseToolbarActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 列表样例activity
 */
public class ListToolbarActivity extends BaseActivity {
    private List<ExampleBean.DataBean> listData;
    private ExampleAdapter adapter;
    private SwipeToLoadLayout swipeToLoad;
    private RecyclerView swipeTarget;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        initView();
        postData("juli");
    }

    private void initView() {
        LinearLayoutManager manager = new LinearLayoutManager(this);
        swipeToLoad = (SwipeToLoadLayout) this.findViewById(R.id.swipe_to_load);
        swipeTarget = (RecyclerView) this.findViewById(R.id.swipe_target);


        listData = new ArrayList<>();
        //为swipeToLoadLayout设置下拉刷新监听者
        adapter = new ExampleAdapter(R.layout.nearby_institutions_lvpattern, listData, this);

        swipeToLoad.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        postData("juli");
                        swipeToLoad.setRefreshing(false);
                    }
                }, 1000);
            }
        });
        //为swipeToLoadLayout设置上拉加载更多监听者
        swipeToLoad.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        postData("juli");
                        swipeToLoad.setLoadingMore(false);
                    }
                }, 1000);
            }
        });

        swipeTarget.setLayoutManager(manager);
        swipeTarget.setAdapter(adapter);
    }

    /**
     * 请求数据
     * 离我最近,人气最高
     */
    private void postData(String type) {

        HashMap<String, String> map = new HashMap<>();
        map.put("type", type);
        map.put("long", String.valueOf(108.900317));
        map.put("lat", String.valueOf(34.24114));
        map.put("page_number", "10");
        map.put("page", String.valueOf(0));
        map.put("type_kw", "");
        Manager.getInstance().example(map, getSubscriber(Constants.USERLOGIN));
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
        switch (what) {
            case Constants.USERLOGIN:
                ExampleBean data = (ExampleBean) object;
                if (data.getCode().equals("100102")) {
                    listData.addAll(data.getData());
                    adapter.notifyDataSetChanged();
                }
                break;
        }
    }
}
