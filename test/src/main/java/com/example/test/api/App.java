package com.example.test.api;

import android.app.Application;

import java.util.HashMap;

public class App extends Application {

    private static HashMap<String, Object> map;

    @Override
    public void onCreate() {
        super.onCreate();
        map = new HashMap<>();
    }
    public static HashMap<String, Object> getMap(){
        return map;
    }
}
