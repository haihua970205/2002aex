package com.example.test_kao.presenter.shop;

import com.example.test_kao.base.BasePresenter;
import com.example.test_kao.bean.shop.AddressBean;
import com.example.test_kao.bean.shop.CheckCarBean;
import com.example.test_kao.bean.shop.ProvinceBean;
import com.example.test_kao.interfaces.Callback;
import com.example.test_kao.interfaces.shop.ICarInfo;
import com.example.test_kao.model.shop.CarInfoModel;
import com.example.test_kao.ui.shop.CarInfoActivity;

public class CarInfoPresenter extends BasePresenter<ICarInfo.View> implements ICarInfo.Presenter {
    ICarInfo.View view;
     ICarInfo.Model model;
    public CarInfoPresenter(ICarInfo.View view) {
        this.view = view;
        model = new CarInfoModel();
    }

    @Override
    public void getCheckCarList(int addId, int coupId) {
        model.getCheckCarList(addId, coupId, new Callback() {
            @Override
            public void Scuess(Object o) {
                if(view != null){
                    view.getResult((CheckCarBean) o);
                }
            }

            @Override
            public void Faile(String msg) {
                if(view != null){
                    view.showToast(msg);
                }
            }
        });
    }

    @Override
    public void getAddress() {
        model.getAddress(new Callback() {
            @Override
            public void Scuess(Object o) {
                if(view != null){
                    view.getResult((AddressBean) o);
                }
            }

            @Override
            public void Faile(String msg) {
                if(view != null){
                    view.showToast(msg);
                }
            }
        });
    }

    @Override
    public void getProvince(int parentId) {
        model.getProvince(parentId, new Callback() {
            @Override
            public void Scuess(Object o) {
                if(view != null){
                    view.getResult((ProvinceBean) o);
                }
            }

            @Override
            public void Faile(String msg) {
                if(view != null){
                    view.showToast(msg);
                }
            }
        });
    }
}
