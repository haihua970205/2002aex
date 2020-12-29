package com.example.test_kao.presenter;

import com.example.test_kao.base.BasePresenter;
import com.example.test_kao.bean.InfoBean;
import com.example.test_kao.bean.TypeBean;
import com.example.test_kao.interfaces.Callback;
import com.example.test_kao.interfaces.ICate;
import com.example.test_kao.model.InfoModel;

public class InfoPresenter extends BasePresenter<ICate.View> implements ICate.Presenter {
    ICate.View view;
    ICate.Model model;
    public InfoPresenter(ICate.View view) {
        this.view = view;
        model = new InfoModel();
    }

    @Override
    public void getType(int type) {
        model.getType(type,new Callback() {
            @Override
            public void Scuess(Object o) {
                if(view != null){
                    view.getResult((TypeBean) o);
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
    public void getCate(int id) {
        model.getCate(id,new Callback() {
            @Override
            public void Scuess(Object o) {
                if(view != null){
                    view.getResult((InfoBean) o);
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
