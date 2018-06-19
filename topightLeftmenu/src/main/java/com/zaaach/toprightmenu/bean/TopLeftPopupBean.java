package com.zaaach.toprightmenu.bean;

/**
 * Created by Administrator on 2017/11/2.
 */

public class TopLeftPopupBean {
    private String locationCity;//定位城市
    private String city;//城市

    public TopLeftPopupBean(String locationCity, String city) {
        this.locationCity = locationCity;
        this.city = city;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getLocationCity() {
        return locationCity;
    }

    public void setLocationCity(String locationCity) {
        this.locationCity = locationCity;
    }
}
