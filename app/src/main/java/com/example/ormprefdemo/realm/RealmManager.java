package com.example.ormprefdemo.realm;

import android.content.Context;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class RealmManager {
    public static void init(Context context) {
        Realm.init(context);
        RealmConfiguration configuration = new RealmConfiguration
                .Builder()
                .name("myrealm.realm")
                .allowQueriesOnUiThread(true)
                .allowWritesOnUiThread(true)
                .build();
        Realm.setDefaultConfiguration(configuration);
    }
}
