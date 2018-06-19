package com.base;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;


import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;

/**
 * 版权：艺魔方公司 版权所有
 * @author yang
 * 版本：1.0
 * 创建日期：2018/5/2
 * 描述：BaseFragment
 *
 */

public class BaseFragment extends Fragment implements OnSubscriber {


    public MySub sub;

    public MySub getSubscriber(int what) {
        return sub = new MySub(what);
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onCompleted(int what) {

    }

    @Override
    public void onError(Throwable e, int what) {

    }

    @Override
    public void onNext(Object object, int what) {

    }

    private class MySub extends Subscriber {

        private int what = -1;

        public MySub() {
        }

        public MySub(int what) {
            this.what = what;
        }

        @Override
        public void onCompleted() {
            Log.e("basefragment", "----------completed");
            BaseFragment.this.onCompleted(what);
        }

        @Override
        public void onError(Throwable e) {
            BaseFragment.this.onError(e, what);
            if (what == 50) {
                return;
            }
            if (e instanceof HttpException) {
                Log.e("eeeeeeeeeee", "" + ((HttpException) e).response().code());
                if (((HttpException) e).response().code() == 401) {
                }
            } else {//服务器返回的错误
//                ToastUtils.showShort("服务器错误");
            }
        }

        @Override
        public void onNext(Object object) {
            BaseFragment.this.onNext(object, what);
        }
    }

    /**
     * 获取用户token
     *
     * @return
     */
    public String getToken() {
      
//        return Remember.getString(RememerConstants.USER_TOKEN, "");
        return "";
    }

    /**
     * 用户
     *
     * @return
     */
    public String getAccount() {

        return "";
    }


    /**
     * 用户id
     *
     * @return
     */
    public String getUid() {
        return "";
    }

}