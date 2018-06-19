package com.example.administrator.oper.api;


import com.example.administrator.oper.bean.edu.ClassManagementBean;
import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by yang
 */

public interface ApiStores {
    public static String COMMEN = "app.php?g=App&c=Home_app&a=";

    /**
     *班级管理列表
     * @param data
     * @return
     */
    @POST(COMMEN + "App_is_login")
    Observable<ClassManagementBean> userLogin(@Body RequestBody data);

    @POST("app.php?g=App&c=Meal_app&a=" + "App_middle_footer")
    Observable<ClassManagementBean> nearbyInstitution(@Body RequestBody data);

}
