package com.example.administrator.oper.manager;

import android.support.annotation.NonNull;
import android.util.Log;
import com.alibaba.fastjson.JSON;
import com.example.administrator.oper.api.ApiStores;
import com.example.administrator.oper.bean.edu.ClassManagementBean;
import io.reactivex.Observable;
import okhttp3.FormBody;
import okhttp3.RequestBody;
import rx.Subscriber;
import rx.functions.Action1;

import java.util.Map;

/**
 * Created by yangpf
 * date:2018/4/11
 * des:艺魔方
 */

public class Manager {
    private static Manager instance;

    public static Manager getInstance() {
        if (null == instance) {
            instance = new Manager();
        }
        return instance;
    }

    private Manager() {

    }


    /**
     *班级管理列表
     */
    public Subscriber<ClassManagementBean> userLogin(@NonNull Map<String, String> map, @NonNull Subscriber<ClassManagementBean> subscribe) {
        FormBody.Builder builder = new FormBody.Builder();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            builder.add(entry.getKey(), entry.getValue());
        }
        RequestBody requestBody = builder.build();
        Observable<ClassManagementBean> observable = NetServiceUtils.getService(ApiStores.class).userLogin(requestBody);
        new NetServiceUtils<ClassManagementBean>().invoke(observable, userLogin, subscribe);
        return subscribe;
    }

    /**
     *班级管理列表
     */
    private Action1<ClassManagementBean> userLogin = ClassManagementBean -> {
        Log.e("========== ", "" + JSON.toJSONString(ClassManagementBean));
    };


}
