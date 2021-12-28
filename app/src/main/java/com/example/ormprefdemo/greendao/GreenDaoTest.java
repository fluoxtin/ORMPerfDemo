package com.example.ormprefdemo.greendao;

import android.util.Log;

import com.example.ormprefdemo.PerfTest;
import com.example.ormprefdemo.greendao.db.BaseUser;
import com.example.ormprefdemo.greendao.db.BaseUserDao;
import com.example.ormprefdemo.greendao.db.DaoSession;

import org.greenrobot.greendao.query.Query;

import java.util.ArrayList;
import java.util.List;

public class GreenDaoTest extends PerfTest {
    private static final String TAG = "GreenDao";

    public GreenDaoTest(int executeTimes, int numberEntities, OnSetLog callback) {
        super(executeTimes, numberEntities, callback);
    }

    protected String name() {
        return "GreenDao";
    }

    protected void run() {

        DaoSession daoSession = DBManager.getDaoSession();
        BaseUserDao baseUserDao = daoSession.getBaseUserDao();
        List<BaseUser> users = prepareList();

        baseUserDao.deleteAll();

        for (int i = 0; i < EXECUTE_TIMES; i++) {
            start("Insert All");
            // 因为我这里循环了，所以数据库中已经有的数据不能再插入，然后就报错了。
            baseUserDao.insertInTx(users);
            end();
            baseUserDao.deleteAll();
        }

        for (int i = 0; i < EXECUTE_TIMES; i++) {
            start("Delete All");
            baseUserDao.deleteAll();
            end();
            baseUserDao.deleteAll();
        }

        baseUserDao.insertInTx(users);

        Query<BaseUser> query = baseUserDao.queryBuilder().limit(1000).build();
        List<BaseUser> list = new ArrayList<>();

        for (int i = 0; i < EXECUTE_TIMES; i++) {
            start("Query All ");
            list = baseUserDao.loadAll();
//            list = baseUserDao.queryBuilder().build().list();

//            list = queryByPersonId(baseUserDao, "%Person_id%").list();
//            query.list();
//            users =  query.list();
            end();
            Log.d(TAG, "list size : " + list.size());
        }

        for (int i = 0; i < EXECUTE_TIMES; i++) {
            start("Update");
            baseUserDao.updateInTx(users);
            end();
        }

//        for (int i = 0; i < EXECUTE_TIMES; i++) {
//            start("Delete");
//            baseUserDao.deleteAll();
//            end();
//        }
    }

    private void insert(BaseUserDao dao, List<BaseUser> list) {
        for (BaseUser user : list) {
            dao.insert(user);
        }
    }

    private Query<BaseUser> queryByAge(BaseUserDao dao, int age1, int age2) {
        return dao.queryBuilder()
                .where(BaseUserDao.Properties.Age.gt(age1))
                .where(BaseUserDao.Properties.Age.le(age2))
                .build();
    }

    private Query<BaseUser> queryByPersonId(BaseUserDao dao, String value) {
        return dao.queryBuilder()
                .where(BaseUserDao.Properties.PersonId.like(value))
                .build();
    }

    private Query<BaseUser> queryById(BaseUserDao dao) {
        return dao.queryBuilder()
                .limit(1000)
                .where(BaseUserDao.Properties.Id.ge(0)).build();
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
                    false,
                    false
            );
            list.add(user);
        }
        return list;
    }
}

