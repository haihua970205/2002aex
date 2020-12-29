package com.example.test_kao.presenter;

import com.example.test_kao.base.BasePresenter;
import com.example.test_kao.bean.HomeBean;
import com.example.test_kao.interfaces.Callback;
import com.example.test_kao.interfaces.IMain;
import com.example.test_kao.model.HomeModel;

public class HomePresenter extends BasePresenter<IMain.View> implements IMain.Presenter{
    IMain.View view;
    IMain.Model model;
    public HomePresenter(IMain.View view) {
        super();
        this.view = view;
        model = new HomeModel();
    }

    @Override
    public void getHome() {
        model.getHome(new Callback() {
            @Override
            public void Scuess(Object o) {
                if(view != null){
                    view.getResult((HomeBean) o);
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
