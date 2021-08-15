package com.example.ormprefdemo;


import android.app.Application;

import com.example.ormprefdemo.greendao.DBManager;
import com.example.ormprefdemo.mmkv.MMKVManager;
import com.example.ormprefdemo.objectbox.ObjectBox;
import com.example.ormprefdemo.realm.RealmManager;

// 这里写的类需要在 AndroidManifest 中声明，不然程序不会在一开始
// 运行时执行它。
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        // objectBox 初始化
        ObjectBox.init(this);

        // GreenDao 初始化
        DBManager.init(this);

        // realm 初始化
        RealmManager.init(this);

        //mmkv initialize
        MMKVManager.init(this);
//
    }
}
