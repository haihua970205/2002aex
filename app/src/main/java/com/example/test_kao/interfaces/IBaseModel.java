package com.example.test_kao.interfaces;

import io.reactivex.disposables.Disposable;

public interface IBaseModel {
    void addDisposable(Disposable disposable);
    void clear();
}
