package com.example.ormprefdemo.realm;


import com.example.ormprefdemo.PerfTest;
import com.example.ormprefdemo.realm.db.BaseUser;

import java.util.ArrayList;
import java.util.List;
import io.realm.Case;
import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;

public class RealmTest extends PerfTest {

    protected String name() {
        return "Realm";
    }

    protected void run() {

        List<BaseUser> list = prepare();
        Realm realm = Realm.getDefaultInstance();
        delete(realm);
        for (int i = 0; i < EXECUTE_TIMES; i++) {
            start("Insert");
            insert(realm, list);
            end();
            delete(realm);
        }


        // 这里相比 ObjectBox 、GreenDao 快的原因是，使用事务，我们只要打开一个事务
        // 在事务不关闭的时候，一个一个的添加数据，和一次添加 n 个数据是差不太多的，
        // 主要原因我觉得是内部写的有类似于 MMVK 的缓冲区，这样可以避免很多 IO 操作，从而提高速度。
        for (int i = 0; i < EXECUTE_TIMES; i++) {
            start("Insert one");
            insertOne(realm, list);
            end();
            delete(realm);
        }

        insert(realm, list);

        for (int i = 0; i < EXECUTE_TIMES; i++) {
            start("Load  ");
            realm.beginTransaction();
//            List<BaseUser> users = realm.copyFromRealm(
////                    queryById(realm)
//                    queryByPersonId(realm, "*Person_id_*")
//            );
            realm.copyFromRealm(
                    realm.where(BaseUser.class).findAll()
            );
//            realm.where(BaseUser.class).findAll();
            realm.commitTransaction();
            end();
        }

        for (int i = 0; i < EXECUTE_TIMES; i++) {
            start("Update");
            insertOrUpdate(realm, list);
            end();
        }

        for (int i = 0; i < EXECUTE_TIMES; i++) {
            start("Delete");
            delete(realm);
            end();
            insert(realm,list);
        }
    }

    private void insert(Realm realm, List<BaseUser> list) {
//        realm.beginTransaction();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.insert(list);
            }
        });
//        realm.commitTransaction();
    }

    private void insertOne(Realm realm, List<BaseUser> list) {
        for (BaseUser user : list) {
            realm.beginTransaction();
            realm.insert(user);
            realm.commitTransaction();
        }
    }

    private void insertOrUpdate(Realm realm, List<BaseUser> list) {
        realm.beginTransaction();
        realm.insertOrUpdate(list);
        realm.commitTransaction();
    }

    private void delete(Realm realm) {
        realm.beginTransaction();
        realm.deleteAll();
        realm.commitTransaction();
    }

    private RealmResults<BaseUser> queryByAge(Realm realm, int age1, int age2) {
        return realm.where(BaseUser.class)
                .greaterThan("age", age1)
                .lessThanOrEqualTo("age", age2)
                .findAll();
    }

    private RealmResults<BaseUser> queryByPersonId(Realm realm, String personId) {


        return realm.where(BaseUser.class)
                .like("personId", personId, Case.INSENSITIVE)
                .limit(1000)
                .findAll();
    }

    private RealmResults<BaseUser> queryById(Realm realm) {
        return realm.where(BaseUser.class)
                .limit(1000)
                .findAll();
    }

    private List<BaseUser> prepare() {
        List<BaseUser> list = new ArrayList<>(numberEntities);

        RealmList<String> stringList = new RealmList<>();
        RealmList<byte[]> bytes = new RealmList<>();
        stringList.add("aaa");
        stringList.add("bbb");
        stringList.add("ccc");
        bytes.add(new byte[348 * 4]);
        bytes.add(new byte[348 * 4]);
        bytes.add(new byte[348 * 4]);

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

