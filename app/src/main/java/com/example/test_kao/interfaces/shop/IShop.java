package com.example.test_kao.interfaces.shop;

import com.example.test_kao.bean.AddShopBean;
import com.example.test_kao.bean.login.LoginBean;
import com.example.test_kao.bean.shop.DeleteShopBean;
import com.example.test_kao.bean.shop.ShopBean;
import com.example.test_kao.bean.shop.UpdateShopBean;
import com.example.test_kao.interfaces.Callback;
import com.example.test_kao.interfaces.IBaseModel;
import com.example.test_kao.interfaces.IBasePresenter;
import com.example.test_kao.interfaces.IBaseView;
import com.example.test_kao.interfaces.login.ILogin;

import java.util.HashMap;

public interface IShop {
    interface View extends IBaseView {
        void shopResult(ShopBean shopBean);
        void updateResult(UpdateShopBean updateShopBean);
        void deleteResult(DeleteShopBean deleteShopBean);

    }

    interface Presenter extends IBasePresenter<IShop.View> {
        void getCarList();
        void updateCar(HashMap map);
        void deleteCar(String productIds);

    }


    interface Model extends IBaseModel {
        void getCarList(Callback callback);
        void updateCar(HashMap map , Callback callback);
        void deleteCar(String productIds , Callback callback);

    }
}
