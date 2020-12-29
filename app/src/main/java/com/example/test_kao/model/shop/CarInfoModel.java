package com.example.test_kao.model.shop;

import com.example.test_kao.base.BaseModel;
import com.example.test_kao.bean.shop.AddressBean;
import com.example.test_kao.bean.shop.CheckCarBean;
import com.example.test_kao.bean.shop.ProvinceBean;
import com.example.test_kao.interfaces.Callback;
import com.example.test_kao.interfaces.shop.ICarInfo;
import com.example.test_kao.net.CommonSubscriber;
import com.example.test_kao.net.HttpManger;
import com.example.test_kao.utils.RxUtils;

public class CarInfoModel extends BaseModel implements ICarInfo.Model {
    @Override
    public void getCheckCarList(int addId, int coupId, Callback callback) {
        addDisposable(HttpManger.getInstance().getApiService().getCheckCarList(addId,coupId)
        .compose(RxUtils.rxScheduler())
        .subscribeWith(new CommonSubscriber<CheckCarBean>(callback) {
            @Override
            public void onNext(CheckCarBean checkCarBean) {
                callback.Scuess(checkCarBean);
            }
        }));
    }

    @Override
    public void getAddress(Callback callback) {
        addDisposable(HttpManger.getInstance().getApiService().getAddress()
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<AddressBean>(callback) {
                    @Override
                    public void onNext(AddressBean addressBean) {
                        callback.Scuess(addressBean);
                    }
                }));
    }

    @Override
    public void getProvince(int parentId, Callback callback) {
        addDisposable(HttpManger.getInstance().getApiService().getProvince(parentId)
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<ProvinceBean>(callback) {
                    @Override
                    public void onNext(ProvinceBean provinceBean) {
                        callback.Scuess(provinceBean);
                    }
                }));
    }
}
