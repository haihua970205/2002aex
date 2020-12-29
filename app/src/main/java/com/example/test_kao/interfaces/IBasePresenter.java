package com.example.test_kao.interfaces;

public interface IBasePresenter<V extends IBaseView> {
    //与V连
    void attachView(V view);
    void unAttachView();
}
