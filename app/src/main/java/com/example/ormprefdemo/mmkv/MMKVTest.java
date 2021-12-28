package com.example.ormprefdemo.mmkv;

import com.example.ormprefdemo.PerfTest;
import com.example.ormprefdemo.mmkv.db.BaseUser;
import com.example.ormprefdemo.mmkv.db.Feature;
import com.tencent.mmkv.MMKV;

import java.util.ArrayList;
import java.util.List;

public class MMKVTest extends PerfTest {

    BaseUser user;

    public MMKVTest(int executeTimes, int numberEntities, OnSetLog callback) {
        super(executeTimes, numberEntities, callback);
    }

    @Override
    protected String name() {
        return "MMKV";
    }

    protected void run() {

        MMKV.defaultMMKV();
        MMKV kv = MMKV.mmkvWithID("MyKV");


        List<BaseUser> users = prepareList();

        List<String> stringList = new ArrayList<>();
        stringList.add("aaa");
        stringList.add("bbb");
        stringList.add("ccc");
        byte[] bytes = new byte[348 * 4];
        bytes[1] = 0x00;
        bytes[2] = 0x01;
        bytes[3] = 0x02;
        List<Feature> features = new ArrayList<>();
        features.add(new Feature(new byte[348 * 4]));
        features.add(new Feature(bytes));
        features.add(new Feature(new byte[348 * 4]));

        user = new BaseUser(
                (long) 1,
                "Person_id_" + 1,
                "External_id_" + 1,
                "Gender",
                1 % 20 + 10,
                "CardNumber_" + 1,
                "IdentityNumber_" + 1,
                "Name_" + 1,
                "GroupName_" + 1,
                "Description_" + 1,
                "payLoad_" + 1,
                stringList,
                stringList,
                stringList,
                features,
                false,
                1,
                1,
                false
        );

        for (int i = 0; i < EXECUTE_TIMES; i++) {
            start("Insert");
            insert(kv, users);
            end();
            kv.clearAll();
        }

        List<BaseUser> list = new ArrayList<>();

        insert(kv, users);
        for (int i = 0; i < EXECUTE_TIMES; i++) {
            start("Load  ");
            loadAll(kv);
            end();
        }

        list.size();
        for (int i = 0; i < EXECUTE_TIMES; i++) {
            start("Update");
            update(kv, users);
            end();
        }

        for (int i = 0; i < EXECUTE_TIMES; i++) {
            start("Delete");
            kv.clearAll();

            end();
            insert(kv, users);
        }
    }

    private void insert(MMKV mmkv, List<BaseUser> list) {
        for (BaseUser user : list) {
            mmkv.encode("BaseUser" + user.getId(), user);
        }
    }

    private void load(MMKV mmkv) {
        String[] keys = mmkv.allKeys();
        for (String key : keys) {
            mmkv.decodeParcelable(key, BaseUser.class);
//            log(user.toString());
        }
    }

    private List<BaseUser> loadAll(MMKV mmkv) {
        String[] keys = mmkv.allKeys();
        assert keys != null;
        int length;
        List<BaseUser> list = new ArrayList<>(keys.length);
//        length = Math.min(keys.length, 1000);
        for (int i = 0; i < keys.length; i++) {
            mmkv.decodeParcelable(keys[i], BaseUser.class);
//            mmkv.decodeParcelable(key, BaseUser.class);
        }
        return list;
    }

    private void update(MMKV mmkv, List<BaseUser> list) {
        for (BaseUser user : list) {
            mmkv.encode("BaseUser" + user.getId() , user);
        }
    }

    private List<BaseUser> prepareList() {
        List<BaseUser> list = new ArrayList<>(numberEntities);

        List<String> stringList = new ArrayList<>();
        stringList.add("aaa");
        stringList.add("bbb");
        stringList.add("ccc");
       byte[] bytes = new byte[348 * 4];
       bytes[1] = 0x00;
       bytes[2] = 0x01;
       bytes[3] = 0x02;
//        byte[] bytes1 = {0x00, 0x01, 0x02, 0x03, 0x04};
        List<Feature> features = new ArrayList<>();
        features.add(new Feature(new byte[348 * 4]));
        features.add(new Feature(bytes));
        features.add(new Feature(new byte[348 * 4]));

        for (int i = 0; i < numberEntities; i++) {
            BaseUser user = new BaseUser(
                    (long) i,
                    "Person_id_" + i,
                    "External_id_" + i,
                    "Gender",
                    i % 20 + 10,
                    "CardNumber_" + i,
                    "IdentityNumber_" + i,
                    "Name_" + i,
                    "GroupName_" + i,
                    "Description_" + i,
                    "payLoad_" + i,
                    stringList,
                    stringList,
                    stringList,
                    features,
                    false,
                    i,
                    i,
                    false
            );
            list.add(user);
        }
        return list;
    }

}
