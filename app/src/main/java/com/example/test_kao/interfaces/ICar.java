package com.example.test_kao.interfaces;

import com.example.test_kao.bean.AddShopBean;
import com.example.test_kao.bean.shop.CarBean;
import com.example.test_kao.bean.RelateBean;
import com.example.test_kao.bean.TalkBean;

import java.util.HashMap;
import java.util.Map;

public interface ICar {
    interface View extends IBaseView{
        void getResult(CarBean carBean);
        void getResult(RelateBean relateBean);
        void getResult(TalkBean talkBean);
        void getResult(AddShopBean addShopBean);
    }
    interface Presenter extends IBasePresenter<ICar.View>{
        void getCar(int id);
        void getrelated(int id);
        void getTalk();
        void addCar(HashMap map);
    }
    interface Model extends IBaseModel{
        void getCar(int id,Callback callback);
        void getrelated(int id,Callback callback);
        void getTalk(Callback callback);
        void addCar(HashMap map,Callback callback);
    }
}
