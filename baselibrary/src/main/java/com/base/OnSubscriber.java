package com.base;

/**
 * Created by zhjh on 16/6/1.
 */
public interface OnSubscriber {

    void onCompleted(int what);

    void onError(Throwable e, int what);

    void onNext(Object object, int what);

}
