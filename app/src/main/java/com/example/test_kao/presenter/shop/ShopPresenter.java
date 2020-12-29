package com.example.test_kao.presenter.shop;

import com.example.test_kao.base.BasePresenter;
import com.example.test_kao.bean.shop.DeleteShopBean;
import com.example.test_kao.bean.shop.ShopBean;
import com.example.test_kao.bean.shop.UpdateShopBean;
import com.example.test_kao.interfaces.Callback;
import com.example.test_kao.interfaces.shop.IShop;
import com.example.test_kao.model.shop.ShopModel;

import java.util.HashMap;

public class ShopPresenter extends BasePresenter<IShop.View> implements IShop.Presenter {
    IShop.View view;
    IShop.Model model;
    public ShopPresenter(IShop.View view) {
        this.view = view;
        model = new ShopModel();
    }

    @Override
    public void getCarList() {
        model.getCarList(new Callback() {
            @Override
            public void Scuess(Object o) {
                if(view != null){
                    view.shopResult((ShopBean) o);
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
    public void updateCar(HashMap map) {
        model.updateCar(map, new Callback() {
            @Override
            public void Scuess(Object o) {
                if(view != null){
                    view.updateResult((UpdateShopBean) o);
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
    public void deleteCar(String productIds) {
        model.deleteCar(productIds, new Callback() {
            @Override
            public void Scuess(Object o) {
                if(view != null){
                   view.deleteResult((DeleteShopBean) o);
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
