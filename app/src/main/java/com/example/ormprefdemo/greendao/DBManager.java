package com.example.ormprefdemo.greendao;

import android.content.Context;

import com.example.ormprefdemo.greendao.db.DaoMaster;
import com.example.ormprefdemo.greendao.db.DaoSession;

import org.greenrobot.greendao.database.Database;

public class DBManager {
    private static final String DATABASE_NAME = "greendao_db";

    private static DaoMaster.DevOpenHelper sOpenHelper;
    private static DaoSession mDaoSession;

    public static void init(Context context) {
        if (sOpenHelper == null) {
            sOpenHelper = new DaoMaster.DevOpenHelper(
                    context,
                    DATABASE_NAME
            );
        }
        Database database1 = sOpenHelper.getWritableDb();
//        Database database1 = sOpenHelper.getEncryptedReadableDb("ThisIsAEncryptedDB");
//        Database database1 = sOpenHelper.getEncryptedWritableDb("ThisIsAEncryptedDb");
        mDaoSession = new DaoMaster(database1).newSession();
    }
    public static DaoSession getDaoSession() {
        return mDaoSession;
    }
}
