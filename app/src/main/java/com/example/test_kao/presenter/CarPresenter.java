package com.example.test_kao.presenter;

import com.example.test_kao.base.BasePresenter;
import com.example.test_kao.bean.AddShopBean;
import com.example.test_kao.bean.shop.CarBean;
import com.example.test_kao.bean.RelateBean;
import com.example.test_kao.bean.TalkBean;
import com.example.test_kao.interfaces.Callback;
import com.example.test_kao.interfaces.ICar;
import com.example.test_kao.model.CarModel;

import java.util.HashMap;
import java.util.Map;

public class CarPresenter extends BasePresenter<ICar.View> implements ICar.Presenter {
    ICar.View view;
    ICar.Model model;
    public CarPresenter(ICar.View view) {
        this.view = view;
        model = new CarModel();
    }

    @Override
    public void getCar(int id) {
        model.getCar(id, new Callback() {
            @Override
            public void Scuess(Object o) {
                if(view != null){
                    view.getResult((CarBean) o);
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
    public void getrelated(int id) {
        model.getrelated(id, new Callback() {
            @Override
            public void Scuess(Object o) {
                if(view != null){
                    view.getResult((RelateBean) o);
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
    public void getTalk() {
        model.getTalk(new Callback() {
            @Override
            public void Scuess(Object o) {
                if(view != null){
                    view.getResult((TalkBean) o);
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
    public void addCar(HashMap map) {
        model.addCar(map, new Callback() {
            @Override
            public void Scuess(Object o) {
                if(view != null){
                    view.getResult((AddShopBean) o);
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
