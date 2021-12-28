package com.example.ormprefdemo.dbflow;

import android.util.Log;

import com.dbflow5.adapter.ModelAdapter;
import com.dbflow5.config.DBFlowDatabase;
import com.dbflow5.config.FlowManager;
import com.dbflow5.database.DatabaseWrapper;
import com.dbflow5.query.SQLite;
import com.dbflow5.transaction.ITransaction;
import com.example.ormprefdemo.PerfTest;
import com.example.ormprefdemo.dbflow.db.BaseUser;
import com.example.ormprefdemo.dbflow.db.BaseUser_Table;
import com.example.ormprefdemo.dbflow.db.MyDatabase;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class DBFlowTest extends PerfTest {
    private static final String TAG = "DBFlowTest";


    private BaseUser mUser;

    public DBFlowTest(int executeTimes, int numberEntities, PerfTest.OnSetLog callback) {
        super(executeTimes, numberEntities, callback);
    }

    @Override
    protected String name() {
        return "DBFlow";
    }

    @Override
    protected void run() {
        List<BaseUser> users = prepareEntities();
        ModelAdapter<BaseUser> adapter = FlowManager.getModelAdapter(BaseUser.class);
        DatabaseWrapper wrapper = FlowManager.getDatabase(MyDatabase.class);
        DBFlowDatabase database = FlowManager.getDatabase(MyDatabase.class);
        SQLite.delete(BaseUser.class)
                .where(BaseUser_Table.age.greaterThanOrEq(0))
                .execute(wrapper);

        // 写入数据
        for (int i = 0 ; i < EXECUTE_TIMES; i++) {
            start("insertAll");
            Log.d(TAG, "start   ");
            database.beginTransactionAsync((ITransaction<Object>) databaseWrapper -> {
                for (BaseUser user : users)
                    user.save(databaseWrapper);
                return null;
            }).success((o, o2) -> {
                Log.d(TAG, "end");
                end();                      //  写入成功的回调监听
                Log.d(TAG, "success");
                return null;
            }).error((o, o1) -> {
                Log.d(TAG, "error");
                return null;
            }).execute();
        }

        Log.d(TAG, "begin to load");
        List<BaseUser> list = SQLite.select()
                .from(BaseUser.class)
                .queryList(wrapper);
        Log.d(TAG, "list  " + list.size());
        if (list.size() > 8)
            Log.d(TAG, "list index 8" + list.get(8));

        start("deleteAll");
//            wrapper.beginTransaction();
//            adapter.deleteAll(users, wrapper);
//            wrapper.endTransaction();
        database.beginTransactionAsync((ITransaction<?>) databaseWrapper -> {
            adapter.deleteAll(users, wrapper);
            return null;
        }).success((o1, o2) -> {
            end();
            Log.d(TAG, "delete Success");
            return null;
        }).error((o1, o2) -> {
            Log.d(TAG, "delete error");
            return null;
        }).execute();
//        }

        Log.d(TAG, "begin to load after deleteAll");;
        list = SQLite.select()
                .from(BaseUser.class)
                .queryList(wrapper);
        Log.d(TAG, "list size : " + list.size());
//        list = SQLite.select()
//                .from(BaseUser.class)
//                .queryList(wrapper);
//        Log.d(TAG, "list size after delete All : " + list.size());
//
//        adapter.insertAll(users, wrapper);
//
//        for (int i = 0;i < EXECUTE_TIMES; i++) {
//            start("query");
//            BaseUser user = SQLite.select()
//                    .from(BaseUser.class)
//                    .where(BaseUser_Table.personId.eq("personId_8"))
//                    .querySingle(wrapper);
//            end();
////            assert user != null;
////            Log.d("DBFlow", "query user : " + user.toString());;
//        }
//
        for (int i = 0; i < EXECUTE_TIMES; i++) {
            start("query all");
            List<BaseUser> queryList = SQLite.select()
                    .from(BaseUser.class)
                    .queryList(wrapper);
            end();
//            Log.d("DBFlow", "list : " + list.size());
//            Log.d("DBFlow", "list 8 : " + list.get(8).toString());
        }

        adapter.insertAll(users, wrapper);

        BaseUser user = users.get(8);
        users.remove(user);
        user.personName = "hello";
        users.add(8, user);

        start("update");
        database.beginTransactionAsync((ITransaction<?>) databaseWrapper -> {
            adapter.updateAll(users, databaseWrapper);
            return null;
        }).success((o1, o2) -> {
            end();
            Log.d(TAG, "update success");
            return null;
        }).error((o1, o2) -> {
            Log.d(TAG, "update error");
            return null;
        }).execute();

        List<BaseUser> queryList = SQLite.select()
                .from(BaseUser.class)
                .queryList(wrapper);
        Log.d(TAG, "queryList size : " + queryList.size());
        if (queryList.size() > 0)
            Log.d(TAG, "8 : " + queryList.get(8).toString());

    }

    private List<BaseUser>  prepareEntities() {
        List<BaseUser> users = new ArrayList<>(numberEntities);
        List<String> strings = new LinkedList<>();
        strings.add("aaa");
        strings.add("bbb");
        strings.add("ccc");
        List<byte[]> bytes = new LinkedList<>();
        bytes.add(new byte[348 * 4]);
        bytes.add(new byte[348 * 4]);
        bytes.add(new byte[348 * 4]);
        for (int i = 0; i < numberEntities; i++) {
            BaseUser user = new BaseUser(
                    "personId_" + i,
                    "externalId_" + i,
                    "gender_" + i,
                    i,
                    "cardNumber_" + i,
                    "identityNumber_" + i,
                    "personName_" + i,
                    "groupName_" + i,
                    "description_" + i,
                    "payload",
                    strings,
                    strings,
                    strings,
                    bytes,
                    false,
                    i,
                    i,
                    false,
                    false
            );
            mUser = user;
            users.add(user);
        }
        return users;
    }
}
