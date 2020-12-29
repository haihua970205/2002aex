package com.example.test_kao.presenter;

import com.example.test_kao.base.BasePresenter;
import com.example.test_kao.bean.BrandBean;
import com.example.test_kao.bean.BrandIdBean;
import com.example.test_kao.bean.Brand_Tv_infoBean;
import com.example.test_kao.interfaces.Callback;
import com.example.test_kao.interfaces.IBrand;
import com.example.test_kao.model.BrandModel;

public class BrandPresenter extends BasePresenter<IBrand.View> implements IBrand.Presenter {
    IBrand.View view;
    IBrand.Model model;
    public BrandPresenter(IBrand.View view) {
        this.view = view;
        model = new BrandModel();
    }

    @Override
    public void getBrand(int p, int size) {
        model.getBrand(p, size, new Callback() {
            @Override
            public void Scuess(Object o) {
                if(view != null){
                    view.getResult((BrandBean) o);
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
    public void getBrandtvinfo(int id) {
        model.getBrandtvinfo(id, new Callback() {
            @Override
            public void Scuess(Object o) {
                if(view != null){
                    view.getResult((Brand_Tv_infoBean) o);
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
    public void getBrandId(int id) {
        model.getBrandId(id, new Callback() {
            @Override
            public void Scuess(Object o) {
                if(view != null){
                    view.getResult((BrandIdBean) o);
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
