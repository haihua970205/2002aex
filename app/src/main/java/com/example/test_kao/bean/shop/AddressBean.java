package com.example.test_kao.bean.shop;

import java.util.List;

public class AddressBean {

    /**
     * errno : 0
     * errmsg :
     * data : [{"id":5,"name":"","user_id":5,"country_id":0,"province_id":0,"city_id":0,"district_id":0,"address":"","mobile":"","is_default":0,"full_region":null}]
     */

    private int errno;
    private String errmsg;
    private List<DataBean> data;

    public int getErrno() {
        return errno;
    }

    public void setErrno(int errno) {
        this.errno = errno;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 5
         * name :
         * user_id : 5
         * country_id : 0
         * province_id : 0
         * city_id : 0
         * district_id : 0
         * address :
         * mobile :
         * is_default : 0
         * full_region : null
         */

        private int id;
        private String name;
        private int user_id;
        private int country_id;
        private int province_id;
        private int city_id;
        private int district_id;
        private String address;
        private String mobile;
        private int is_default;
        private Object full_region;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public int getCountry_id() {
            return country_id;
        }

        public void setCountry_id(int country_id) {
            this.country_id = country_id;
        }

        public int getProvince_id() {
            return province_id;
        }

        public void setProvince_id(int province_id) {
            this.province_id = province_id;
        }

        public int getCity_id() {
            return city_id;
        }

        public void setCity_id(int city_id) {
            this.city_id = city_id;
        }

        public int getDistrict_id() {
            return district_id;
        }

        public void setDistrict_id(int district_id) {
            this.district_id = district_id;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public int getIs_default() {
            return is_default;
        }

        public void setIs_default(int is_default) {
            this.is_default = is_default;
        }

        public Object getFull_region() {
            return full_region;
        }

        public void setFull_region(Object full_region) {
            this.full_region = full_region;
        }
    }
}
