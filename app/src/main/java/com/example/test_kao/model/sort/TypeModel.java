package com.example.test_kao.model.sort;

import com.example.test_kao.base.BaseModel;
import com.example.test_kao.bean.sort.SortBean;
import com.example.test_kao.bean.sort.VTypeBean;
import com.example.test_kao.interfaces.Callback;
import com.example.test_kao.interfaces.sort.IType;
import com.example.test_kao.net.CommonSubscriber;
import com.example.test_kao.net.HttpManger;
import com.example.test_kao.utils.RxUtils;

public class TypeModel extends BaseModel implements IType.Model {
    @Override
    public void getVType(Callback callback) {
        addDisposable(HttpManger.getInstance().getApiService().getVType()
        .compose(RxUtils.rxScheduler())
        .subscribeWith(new CommonSubscriber<VTypeBean>(callback) {
            @Override
            public void onNext(VTypeBean vTypeBean) {
                callback.Scuess(vTypeBean);
            }
        }));
    }

    @Override
    public void getSort(int id, Callback callback) {
        addDisposable(HttpManger.getInstance().getApiService().getSort(id)
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<SortBean>(callback) {
                    @Override
                    public void onNext(SortBean sortBean) {
                        callback.Scuess(sortBean);
                    }
                }));
    }
}
