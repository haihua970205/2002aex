package com.example.test_kao.presenter.sort;

import com.example.test_kao.base.BasePresenter;
import com.example.test_kao.bean.sort.SortBean;
import com.example.test_kao.bean.sort.VTypeBean;
import com.example.test_kao.interfaces.Callback;
import com.example.test_kao.interfaces.sort.IType;
import com.example.test_kao.model.sort.TypeModel;
import com.example.test_kao.ui.sort.SortFrag;

public class TypePresenter extends BasePresenter<IType.View> implements IType.Presenter {
    IType.View view;
    IType.Model model;
    public TypePresenter(IType.View view) {
        this.view = view;
        model = new TypeModel();
    }

    @Override
    public void getVType() {
        model.getVType(new Callback() {
            @Override
            public void Scuess(Object o) {
                if(view != null){
                    view.getResult((VTypeBean) o);
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
    public void getSort(int id) {
        model.getSort(id, new Callback() {
            @Override
            public void Scuess(Object o) {
                if(view != null){
                    view.getResult((SortBean) o);
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
