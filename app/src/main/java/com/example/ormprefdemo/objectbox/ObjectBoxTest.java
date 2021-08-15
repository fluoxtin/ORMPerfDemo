package com.example.ormprefdemo.objectbox;

import com.example.ormprefdemo.PerfTest;
import com.example.ormprefdemo.objectbox.db.BaseUser;
import com.example.ormprefdemo.objectbox.db.BaseUser_;

import java.util.ArrayList;
import java.util.List;

import io.objectbox.Box;
import io.objectbox.query.Query;

public class ObjectBoxTest extends PerfTest {

    BaseUser mUser;

    protected String name() {
        return "ObjectBox";
    }

    protected void run() {

        Box<BaseUser> baseUserBox = ObjectBox
                .getBoxStore()
                .boxFor(BaseUser.class);


        List<BaseUser> users = prepareList();

        baseUserBox.removeAll();
        //  insert method contains putting new data & updating old data
        for (int i = 0; i < EXECUTE_TIMES; i++) {
            start("Insert");
            baseUserBox.put(users);
            end();
            ObjectBox.getBoxStore().runInTx(baseUserBox::removeAll);
        }

//        for (int i = 0;i < EXECUTE_TIMES; i++) {
//            start("Insert One");
//            insert(baseUserBox);
//            end();
//        }

        baseUserBox.put(users);
        Query<BaseUser> query =  baseUserBox
                .query()
                .build();
        query.findLazy();

        List<BaseUser> list = new ArrayList<>();

        for (int i = 0; i < EXECUTE_TIMES; i++) {
            start("Load");
//            list = queryById(baseUserBox).findLazy();
//            list = queryByPersonId(baseUserBox, "Person_id").find(0, 1000);
            baseUserBox.getAll();
            end();
        }

        for (int i = 0; i < EXECUTE_TIMES; i++) {
            start("Update");
            baseUserBox.put(users);
            end();
        }
//        // delete the entities
        for (int i = 0; i < EXECUTE_TIMES; i++) {
            start("Delete");
            baseUserBox.removeAll();
            end();
            baseUserBox.put(users);
        }
    }

    private void insert(Box<BaseUser> box) {
        for (int i = 0; i < numberEntities; i++) {
            box.put(mUser);
        }
    }

    private Query<BaseUser> queryByAge(Box<BaseUser> box, int age1, int age2) {
         return box.query()
                 .between(BaseUser_.age, age1, age2)
                 .build();

    }

    private Query<BaseUser> queryByPersonId(Box<BaseUser> box, String personId) {
        return box.query()
//                .equal(BaseUser_.personId, personId)
                .contains(BaseUser_.personId, personId)
                .build();
    }

    private Query<BaseUser> queryById(Box<BaseUser> box) {
        return box.query()
                .order(BaseUser_.id)
                .build();
    }



    private List<BaseUser> prepareList() {
        List<BaseUser> list = new ArrayList<>(numberEntities);

        List<String> stringList = new ArrayList<>();
        List<byte[]> bytes = new ArrayList<>();
        stringList.add("aaa");
        stringList.add("bbb");
        stringList.add("ccc");
        bytes.add(new byte[348 * 4]);
        bytes.add(new byte[348 * 4]);
        bytes.add(new byte[348 * 4]);

        mUser = new BaseUser(
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
                bytes,
                false,
                1,
                1,
                false
        );

        for (int i = 0; i < numberEntities; i++) {
            BaseUser user = new BaseUser(
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
                    bytes,
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

