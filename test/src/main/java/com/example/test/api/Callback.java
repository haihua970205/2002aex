package com.example.test.api;

public interface Callback<T> {
    void Scuess(T t);
    void Faile(String msg);
}
