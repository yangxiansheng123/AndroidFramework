package com.androidframework.bean;

import java.util.List;

/**
 *
 * @author Administrator
 * @date 2017/9/15
 */

public class ExampleBean {


    /**
     * code : 100102
     * message : 获取离我最近OK
     * data : [{"juli":"0.01km","store_id":"1095","mer_id":"429","name":"测试帐号","adress":"科技路48号创业广场B座2004","phone":"02936512363","pic_info":"http://oss.artmofang.com/upload/store/000/000/429/ym_20171117141444.jpeg","paycash":"1","feature":"儿童画,水粉画,芭蕾舞","price":"12.00","num":"1"},{"juli":"0.14km","store_id":"991","mer_id":"326","name":"婵静瑜伽-中国总部","adress":"科技路与高新四路十字世纪颐园商铺","phone":"13119102973","pic_info":"http://oss.artmofang.com/upload/store/000/000/326/ym_20170824103443.jpeg","paycash":"0","feature":"瑜伽","price":"19.90","num":"2"},{"juli":"0.15km","store_id":"942","mer_id":"276","name":"成人钢琴吧（高新店）","adress":"高新区科技路与高新四路十字,世纪颐园C座2306室（公交车玫瑰大楼站下车）","phone":"14783923520","pic_info":"http://oss.artmofang.com/upload/store/000/000/276/ym_20170825124410.jpeg","paycash":"0","feature":"钢琴","price":"28.00","num":"3"},{"juli":"0.74km","store_id":"1311","mer_id":"665","name":"弈学园（高新校区）","adress":"高新区科技路海星广场B座6层  ","phone":"13309299343","pic_info":"http://oss.artmofang.com/upload/store/000/000/665/ym_20171117190116.jpeg","paycash":"0","feature":"围棋","price":"9.90","num":"2"},{"juli":"0.76km","store_id":"1016","mer_id":"351","name":"斑斓绘本美术（高新区）","adress":"莲湖路207号","phone":"18092916236","pic_info":"http://oss.artmofang.com/upload/store/000/000/351/ym_20170824105631.jpeg","paycash":"0","feature":"国画,油画,水粉画,动漫,水彩画,色彩画,手工DIY","price":0,"num":0},{"juli":"0.79km","store_id":"988","mer_id":"322","name":"西安妙卡音乐","adress":"西安市高新区金鹰国际海星城市广场B座901","phone":"13325382211","pic_info":"http://oss.artmofang.com/upload/store/000/000/322/ym_20170818172553.jpeg","paycash":"0","feature":"小提琴,钢琴,古筝","price":0,"num":0},{"juli":"0.84km","store_id":"963","mer_id":"296","name":"非凡美术教育","adress":"太白星座0605","phone":"15829299728","pic_info":"","paycash":"0","feature":"","price":0,"num":0},{"juli":"0.84km","store_id":"873","mer_id":"205","name":"西安国际美术城兰台美术教育中心","adress":"大寨路与沣惠南路十字西南角","phone":"13060353050","pic_info":"http://oss.artmofang.com/upload/store/000/000/205/ym_20170817020603.jpeg","paycash":"0","feature":"国画,油画,素描,儿童画,水粉画,动漫,水彩画,色彩画,手工DIY","price":"19.90","num":"2"},{"juli":"1.05km","store_id":"993","mer_id":"329","name":"飞洋舞蹈工作室","adress":"南二环西段九座花园西区609","phone":"18220550851","pic_info":"http://oss.artmofang.com/upload/store/000/000/329/ym_20170817143224.jpeg","paycash":"0","feature":"拉丁舞","price":0,"num":0},{"juli":"1.08km","store_id":"798","mer_id":"129","name":"天天向上音乐培训中心","adress":"高新区中华世纪城延长石油小区8号楼1单元1601室","phone":"18295292221","pic_info":"http://oss.artmofang.com/upload/store/000/000/129/ym_20171109135017.jpeg","paycash":"0","feature":"小提琴,钢琴,架子鼓,吉他,尤克里里,古筝,爵士鼓,智能电鼓,声乐,乐理","price":"19.90","num":"2"}]
     */

    private String code;
    private String message;
    private List<DataBean> data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * juli : 0.01km
         * store_id : 1095
         * mer_id : 429
         * name : 测试帐号
         * adress : 科技路48号创业广场B座2004
         * phone : 02936512363
         * pic_info : http://oss.artmofang.com/upload/store/000/000/429/ym_20171117141444.jpeg
         * paycash : 1
         * feature : 儿童画,水粉画,芭蕾舞
         * price : 12.00
         * num : 1
         */

        private String juli;
        private String store_id;
        private String mer_id;
        private String name;
        private String adress;
        private String phone;
        private String pic_info;
        private String paycash;
        private String feature;
        private String price;
        private String num;

        public String getJuli() {
            return juli;
        }

        public void setJuli(String juli) {
            this.juli = juli;
        }

        public String getStore_id() {
            return store_id;
        }

        public void setStore_id(String store_id) {
            this.store_id = store_id;
        }

        public String getMer_id() {
            return mer_id;
        }

        public void setMer_id(String mer_id) {
            this.mer_id = mer_id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAdress() {
            return adress;
        }

        public void setAdress(String adress) {
            this.adress = adress;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getPic_info() {
            return pic_info;
        }

        public void setPic_info(String pic_info) {
            this.pic_info = pic_info;
        }

        public String getPaycash() {
            return paycash;
        }

        public void setPaycash(String paycash) {
            this.paycash = paycash;
        }

        public String getFeature() {
            return feature;
        }

        public void setFeature(String feature) {
            this.feature = feature;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getNum() {
            return num;
        }

        public void setNum(String num) {
            this.num = num;
        }
    }
}
