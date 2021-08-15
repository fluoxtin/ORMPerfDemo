package com.example.ormprefdemo.mmkv;

import android.content.Context;

import com.tencent.mmkv.MMKV;

public class MMKVManager {
    public static void init(Context context){
        String rootDir = MMKV.initialize(context);
        System.out.println("RootDir : " + rootDir);
    }
}
