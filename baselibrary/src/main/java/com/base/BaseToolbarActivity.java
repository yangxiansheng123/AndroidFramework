package com.base;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;


import com.utils.LoadingDialogUtils;
import com.utils.ToolbarHelper;
import com.zxl.dropdownmenue.R;

import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;

/**
 * 版权：艺魔方公司 版权所有
 * @author yang
 * 版本：1.0
 * 创建日期：2018/5/3
 * 描述：BaseToolbarActivity 初始化toolbar
 *
 */
public abstract class BaseToolbarActivity extends AppCompatActivity implements OnSubscriber {

    public MySub sub;
    Dialog dialogUtils;

    public MySub getSubscriber(int what) {
        return sub = new MySub(what);
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(getContentViewId());
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            // 默认不显示原生标题
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            initToolbar(new ToolbarHelper(toolbar));
        }
    }

    protected abstract int getContentViewId();

    protected abstract void initToolbar(ToolbarHelper toolbarHelper);

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
            BaseToolbarActivity.this.onCompleted(what);
        }

        @Override
        public void onError(Throwable e) {
            BaseToolbarActivity.this.onError(e, what);
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
            BaseToolbarActivity.this.onNext(object, what);
        }
    }

    /**
     * 加载动画
     */
    public void showDialog() {
        dialogUtils = LoadingDialogUtils.createLoadingDialog(BaseToolbarActivity.this, getResources().getString(R.string.base_loading));

    }

    /**
     * 关闭动画
     */
    public void closDialog() {
        LoadingDialogUtils.closeDialog(dialogUtils);
    }


}
