package com.example.test.api;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class HttpManger {
    private static HttpManger instance;
    public static HttpManger getInstance(){
        if(instance == null){
            synchronized (HttpManger.class){
                if(instance == null){
                    instance = new HttpManger();
                }
            }
        }
        return instance;
    }

    private Retrofit getRetroit(){
        Retrofit retrofit = new Retrofit.Builder().baseUrl(ApiService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return retrofit;
    }

    private ApiService apiService;
    public ApiService getApiService(){
        if(apiService == null){
            apiService = getRetroit().create(ApiService.class);
        }
        return apiService;
    }
}
