package com.example.test_kao.interfaces.shop;

import com.example.test_kao.bean.shop.AddressBean;
import com.example.test_kao.bean.shop.CheckCarBean;
import com.example.test_kao.bean.shop.ProvinceBean;
import com.example.test_kao.interfaces.Callback;
import com.example.test_kao.interfaces.IBaseModel;
import com.example.test_kao.interfaces.IBasePresenter;
import com.example.test_kao.interfaces.IBaseView;
import com.example.test_kao.interfaces.IMain;

public interface ICarInfo {
    interface View extends IBaseView{
        void getResult(CheckCarBean checkCarBean);
        void getResult(AddressBean addressBean);
        void getResult(ProvinceBean provinceBean);
    }
    interface Presenter extends IBasePresenter<ICarInfo.View> {
        void getCheckCarList(int addId,int coupId);
        void getAddress();
        void getProvince(int parentId);
    }
    interface Model extends IBaseModel {
        void getCheckCarList(int addId,int coupId,Callback callback);
        void getAddress(Callback callback);
        void getProvince(int parentId,Callback callback);
    }
}
