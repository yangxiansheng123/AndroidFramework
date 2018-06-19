package com.androidframework.bean;

/**
 * Created by yangpf
 * date:2018/4/11
 * des:艺魔方
 * 登录
 */

public class LoginBean {
    private String code;
    private String message;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * person : {"uid":"761","openid":"okIK0wbz9mk8QABDCE6OTamEhBZI","web_openid":"","union_id":"oFWsU0_zspKamp5k8nKnZwVyhHdQ","phone":"18840303813","pwd":"e10adc3949ba59abbe56e057f20f883e","nickname":"18840303813","sex":"0","province":"陕西","city":"延安","avatar":"http://oss.artmofang.com/upload/issue/000/000/761/ym_20171204205011_1512391810404.png","qq":"","add_time":"1510797947","add_ip":"607101510","last_time":"1511430388","last_ip":"607101378","last_weixin_time":"0","score_count":"0","now_money":"0.00","is_check_phone":"0","is_follow":"0","status":"1","truename":"嗷嗷嗷","birthday":"0000-00-00","occupation":"0","message":"","weidian_sessid":"","email":"","importid":"0","level":"0","youaddress":"","user_name":null,"user_photo":null,"user_img":"http://oss.artmofang.com/upload/issue/000/000/761/ym_20171204205011_1512391810404.png","fans":"","look":null,"now_fixed_money":"0"}
         * token : 428a8310cd442757ae699df5d894f051
         */

        private PersonBean person;
        private String token;

        public PersonBean getPerson() {
            return person;
        }

        public void setPerson(PersonBean person) {
            this.person = person;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public static class PersonBean {
            /**
             * uid : 761
             * openid : okIK0wbz9mk8QABDCE6OTamEhBZI
             * web_openid :
             * union_id : oFWsU0_zspKamp5k8nKnZwVyhHdQ
             * phone : 18840303813
             * pwd : e10adc3949ba59abbe56e057f20f883e
             * nickname : 18840303813
             * sex : 0
             * province : 陕西
             * city : 延安
             * avatar : http://oss.artmofang.com/upload/issue/000/000/761/ym_20171204205011_1512391810404.png
             * qq :
             * add_time : 1510797947
             * add_ip : 607101510
             * last_time : 1511430388
             * last_ip : 607101378
             * last_weixin_time : 0
             * score_count : 0
             * now_money : 0.00
             * is_check_phone : 0
             * is_follow : 0
             * status : 1
             * truename : 嗷嗷嗷
             * birthday : 0000-00-00
             * occupation : 0
             * message :
             * weidian_sessid :
             * email :
             * importid : 0
             * level : 0
             * youaddress :
             * user_name : null
             * user_photo : null
             * user_img : http://oss.artmofang.com/upload/issue/000/000/761/ym_20171204205011_1512391810404.png
             * fans :
             * look : null
             * now_fixed_money : 0
             */

            private String uid;
            private String openid;
            private String web_openid;
            private String union_id;
            private String phone;
            private String pwd;
            private String nickname;
            private String sex;
            private String province;
            private String city;
            private String avatar;
            private String qq;
            private String add_time;
            private String add_ip;
            private String last_time;
            private String last_ip;
            private String last_weixin_time;
            private String score_count;
            private String now_money;
            private String is_check_phone;
            private String is_follow;
            private String status;
            private String truename;
            private String birthday;
            private String occupation;
            private String message;
            private String weidian_sessid;
            private String email;
            private String importid;
            private String level;
            private String youaddress;
            private String user_name;
            private String user_photo;
            private String user_img;
            private String fans;
            private String look;
            private String now_fixed_money;

            public String getUid() {
                return uid;
            }

            public void setUid(String uid) {
                this.uid = uid;
            }

            public String getOpenid() {
                return openid;
            }

            public void setOpenid(String openid) {
                this.openid = openid;
            }

            public String getWeb_openid() {
                return web_openid;
            }

            public void setWeb_openid(String web_openid) {
                this.web_openid = web_openid;
            }

            public String getUnion_id() {
                return union_id;
            }

            public void setUnion_id(String union_id) {
                this.union_id = union_id;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public String getPwd() {
                return pwd;
            }

            public void setPwd(String pwd) {
                this.pwd = pwd;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public String getSex() {
                return sex;
            }

            public void setSex(String sex) {
                this.sex = sex;
            }

            public String getProvince() {
                return province;
            }

            public void setProvince(String province) {
                this.province = province;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public String getAvatar() {
                return avatar;
            }

            public void setAvatar(String avatar) {
                this.avatar = avatar;
            }

            public String getQq() {
                return qq;
            }

            public void setQq(String qq) {
                this.qq = qq;
            }

            public String getAdd_time() {
                return add_time;
            }

            public void setAdd_time(String add_time) {
                this.add_time = add_time;
            }

            public String getAdd_ip() {
                return add_ip;
            }

            public void setAdd_ip(String add_ip) {
                this.add_ip = add_ip;
            }

            public String getLast_time() {
                return last_time;
            }

            public void setLast_time(String last_time) {
                this.last_time = last_time;
            }

            public String getLast_ip() {
                return last_ip;
            }

            public void setLast_ip(String last_ip) {
                this.last_ip = last_ip;
            }

            public String getLast_weixin_time() {
                return last_weixin_time;
            }

            public void setLast_weixin_time(String last_weixin_time) {
                this.last_weixin_time = last_weixin_time;
            }

            public String getScore_count() {
                return score_count;
            }

            public void setScore_count(String score_count) {
                this.score_count = score_count;
            }

            public String getNow_money() {
                return now_money;
            }

            public void setNow_money(String now_money) {
                this.now_money = now_money;
            }

            public String getIs_check_phone() {
                return is_check_phone;
            }

            public void setIs_check_phone(String is_check_phone) {
                this.is_check_phone = is_check_phone;
            }

            public String getIs_follow() {
                return is_follow;
            }

            public void setIs_follow(String is_follow) {
                this.is_follow = is_follow;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getTruename() {
                return truename;
            }

            public void setTruename(String truename) {
                this.truename = truename;
            }

            public String getBirthday() {
                return birthday;
            }

            public void setBirthday(String birthday) {
                this.birthday = birthday;
            }

            public String getOccupation() {
                return occupation;
            }

            public void setOccupation(String occupation) {
                this.occupation = occupation;
            }

            public String getMessage() {
                return message;
            }

            public void setMessage(String message) {
                this.message = message;
            }

            public String getWeidian_sessid() {
                return weidian_sessid;
            }

            public void setWeidian_sessid(String weidian_sessid) {
                this.weidian_sessid = weidian_sessid;
            }

            public String getEmail() {
                return email;
            }

            public void setEmail(String email) {
                this.email = email;
            }

            public String getImportid() {
                return importid;
            }

            public void setImportid(String importid) {
                this.importid = importid;
            }

            public String getLevel() {
                return level;
            }

            public void setLevel(String level) {
                this.level = level;
            }

            public String getYouaddress() {
                return youaddress;
            }

            public void setYouaddress(String youaddress) {
                this.youaddress = youaddress;
            }


            public String getUser_img() {
                return user_img;
            }

            public void setUser_img(String user_img) {
                this.user_img = user_img;
            }

            public String getFans() {
                return fans;
            }

            public void setFans(String fans) {
                this.fans = fans;
            }

            public String getUser_name() {
                return user_name;
            }

            public void setUser_name(String user_name) {
                this.user_name = user_name;
            }

            public String getUser_photo() {
                return user_photo;
            }

            public void setUser_photo(String user_photo) {
                this.user_photo = user_photo;
            }

            public String getLook() {
                return look;
            }

            public void setLook(String look) {
                this.look = look;
            }

            public String getNow_fixed_money() {
                return now_fixed_money;
            }

            public void setNow_fixed_money(String now_fixed_money) {
                this.now_fixed_money = now_fixed_money;
            }
        }
    }
}
