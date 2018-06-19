package com.androidframework.manager;


import android.support.annotation.NonNull;

import com.facebook.stetho.okhttp3.StethoInterceptor;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.functions.Action1;


/**
 * @author yang
 * @date 2016/3/23.
 */
public class NetServiceUtils<C> {

    public static final String CONNECTION_URI = "http://demo2.artmofang.com/";

    /**
     * 获取Observable<T></>
     *
     * @param serviceCls service class</>
     * @param <T>        Service实体-
     * @return
     */
    public static <T> T getService(@NonNull final Class<T> serviceCls) {
        return getRetrofit().create(serviceCls);
    }

    /**
     * 请求URL带token
     *
     * @param serviceCls
     * @param <T>
     * @return
     */
    public static <T> T getServiceToken(@NonNull final Class<T> serviceCls) {
        return getTokenRetrofit().create(serviceCls);
    }

    public void invoke(@NonNull final Observable<C> observable, @NonNull final Action1<C> dataHandlers, @NonNull final Subscriber<C> subscribe) {
        observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                //数据二次加工处理
                .doOnNext((Consumer<? super C>) dataHandlers)
                //请求回调
                .subscribe((Observer<? super C>) subscribe);
    }


    /**
     * 配置Retrofit并获取Retrofit对象
     *
     * @return
     */
    public static Retrofit getRetrofit() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
//                .addNetworkInterceptor(new StethoInterceptor())
                .build();
        return new Retrofit.Builder().client(okHttpClient).baseUrl(CONNECTION_URI)
                //解析器
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create()).build();
    }

    public static Retrofit getRetrofit(@NonNull String Url) {
        return new Retrofit.Builder().baseUrl(Url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create()).build();
    }

    /**
     * 配置Retrofit并获取Retrofit对象
     *
     * @return
     */
    public static Retrofit getTokenRetrofit() {

        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build();
        return new Retrofit.Builder().client(okHttpClient).baseUrl(CONNECTION_URI)
                //解析器
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create()).build();
    }
}
