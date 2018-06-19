package com.androidframework.ui.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.androidframework.R;
import com.androidframework.bean.LoginBean;
import com.androidframework.manager.Constants;
import com.androidframework.manager.Manager;
import com.banner.BannerLayoutView;
import com.base.BaseFragment;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.utils.SpanUtils;
import com.view.MultiStateView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 *
 */
public class BlankFragment1 extends BaseFragment {


    TextView mTextView;
    BannerLayoutView mBannerHotActivities;
    MultiStateView mMultiStateView;
    Button mButton;
    private List<String> advBannerUrl = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_blank1, container, false);

        initView(view);
        bannerActivities();
        return view;
    }

    private void initView(View view) {
        mTextView = (TextView) view.findViewById(R.id.textView);
        mBannerHotActivities = (BannerLayoutView) view.findViewById(R.id.banner_hotActivities);
        mMultiStateView = (MultiStateView) view.findViewById(R.id.multiStateView);
        mButton = (Button) view.findViewById(R.id.button);

        mTextView.setText(new SpanUtils().append("哈哈").setForegroundColor(Color.parseColor("#ff4e00")).setFontSize(68)
                .append("skmfvlksdkmv").setForegroundColor(Color.parseColor("#ffd409")).setFontSize(48)
                .append("你晒开发哪里哦那").setBackgroundColor(Color.parseColor("#69559e")).setFontSize(48)
                .create());
    }


    /**
     * 登录
     */
    public void login(View view) {
        switch (view.getId()){
            case R.id.button:
                HashMap<String, String> map = new HashMap<>();
                map.put("phone", "13636545878");
                map.put("password", "123456");
                Manager.getInstance().userLogin(map, getSubscriber(Constants.USERLOGIN));
                break;

            case R.id.button2:
                isEmptyData(view);
                break;
        }
    }

    /**
     * banner轮播图
     * 活动
     */
    private void bannerActivities() {

        for (int i = 0; i < 5; i++) {
            advBannerUrl.add("http://imgsrc.baidu.com/image/c0%3Dshijue1%2C0%2C0%2C294%2C40/sign=062e552c3e9b033b3885f4997da75ca6/5ab5c9ea15ce36d3073dcb1830f33a87e950b1d9.jpg");
        }
        mBannerHotActivities.setImageLoader(ImageLoader.getInstance());
        mBannerHotActivities.setViewUrls(advBannerUrl);
        mBannerHotActivities.setOnBannerItemClickListener(new BannerLayoutView.OnBannerItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(getActivity(), "" + position, Toast.LENGTH_LONG).show();
            }
        });
//        mBannerHotActivities.setSliderTransformDuration(3000);
//        mBannerHotActivities.setAutoPlay(false);
    }

    /**
     * 是否为空数据
     */
    public void isEmptyData(View view) {
        mMultiStateView.setViewState(MultiStateView.VIEW_STATE_EMPTY);
        mMultiStateView.setViewState(MultiStateView.VIEW_STATE_ERROR);
        mMultiStateView.getView(MultiStateView.VIEW_STATE_ERROR).findViewById(R.id.retry).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
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
                LoginBean loginRes = (LoginBean) object;
                if (loginRes.getCode().equals("100002")) {
//                    Remember.putString(RememerConstants.USER_TOKEN, loginRes.getData().getToken());
//                    Remember.putString(RememerConstants.ACCOUNT, "13636545878");
//                    Remember.putString(RememerConstants.UID, loginRes.getData().getPerson().getUid());
//                    finish();
                    mButton.setText(loginRes.getMessage());
                } else {
                }

                break;

        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
