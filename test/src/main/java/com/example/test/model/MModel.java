package com.example.test.model;

import com.example.test.api.Callback;
import com.example.test.api.HttpManger;
import com.example.test.bean.InfoBean;
import com.example.test.interfaces.IMain;

import retrofit2.Call;
import retrofit2.Response;

public class MModel implements IMain.Model {
    @Override
    public void getData(final Callback callback) {
        HttpManger.getInstance().getApiService().getData()
                .enqueue(new retrofit2.Callback<InfoBean>() {
                    @Override
                    public void onResponse(Call<InfoBean> call, Response<InfoBean> response) {
                        callback.Scuess(response.body());
                    }

                    @Override
                    public void onFailure(Call<InfoBean> call, Throwable t) {
                        callback.Faile(t.toString());
                    }
                });
    }
}
