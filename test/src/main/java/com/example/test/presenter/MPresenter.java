package com.example.test.presenter;

import com.example.test.MainActivity;
import com.example.test.api.Callback;
import com.example.test.bean.InfoBean;
import com.example.test.interfaces.IMain;
import com.example.test.model.MModel;

public class MPresenter implements IMain.Presenter {
    IMain.View view;
    IMain.Model model;
    public MPresenter(IMain.View view) {
        this.view = view;
        model = new MModel();
    }

    @Override
    public void getData() {
        model.getData(new Callback() {
            @Override
            public void Scuess(Object o) {
                view.getResult((InfoBean) o);
            }

            @Override
            public void Faile(String msg) {

            }
        });
    }
}
