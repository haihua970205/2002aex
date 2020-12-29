package com.example.test_kao.ui.me;

import android.content.Context;

import java.security.SecureRandom;

import io.realm.RealmConfiguration;

public class Realms {

    public static io.realm.Realm getRealm(Context context){
//        byte[] bytes = new byte[1024];
//        new SecureRandom().nextBytes(bytes);
        //初始化
        io.realm.Realm.init(context);

        //自定义配置
        RealmConfiguration config = new RealmConfiguration.Builder()
                .name("db.realm")
                .schemaVersion(2)
                .deleteRealmIfMigrationNeeded()
                .build();
        return io.realm.Realm.getInstance(config);
    }



}
