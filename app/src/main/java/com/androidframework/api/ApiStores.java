package com.androidframework.api;


import com.androidframework.bean.ExampleBean;
import com.androidframework.bean.LoginBean;

import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by Administrator on 2017/9/13.
 */

public interface ApiStores {
    public static String COMMEN = "app.php?g=App&c=Home_app&a=";

    /**
     * 登录
     * @param data
     * @return
     */
    @POST(COMMEN + "App_is_login")
    io.reactivex.Observable<LoginBean> userLogin(@Body RequestBody data);

    @retrofit2.http.POST("app.php?g=App&c=Meal_app&a=" + "App_middle_footer")
    io.reactivex.Observable<ExampleBean> nearbyInstitution(@Body RequestBody data);

}
