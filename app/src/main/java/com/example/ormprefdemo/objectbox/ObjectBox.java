package com.example.ormprefdemo.objectbox;

import android.content.Context;


import com.example.ormprefdemo.objectbox.db.MyObjectBox;

import io.objectbox.BoxStore;
import io.objectbox.android.AndroidObjectBrowser;
import io.objectbox.android.BuildConfig;

public class ObjectBox {
    public static BoxStore mBoxStore;
    public static void init(Context context) {
        mBoxStore = MyObjectBox.builder()
                .androidContext(context.getApplicationContext())
                .build();

//        if (BuildConfig.DEBUG) {
//            boolean started = new AndroidObjectBrowser(mBoxStore).start(context);
//            Log.i("ObjectBrowser", "Started: " + started);
//        }
    }

    public static BoxStore getBoxStore() {
        return mBoxStore;
    }
}
