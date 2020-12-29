package com.example.test.api;

import com.example.test.bean.InfoBean;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    String BASE_URL = "http://cdwan.cn:7000/";

    @GET("tongpao/list.json")
    Call<InfoBean>getData();
}
