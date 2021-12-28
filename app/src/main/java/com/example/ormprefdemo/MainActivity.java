package com.example.ormprefdemo;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ormprefdemo.databinding.ObjectboxBinding;
import com.example.ormprefdemo.dbflow.DBFlowTest;
import com.example.ormprefdemo.greendao.GreenDaoTest;
import com.example.ormprefdemo.mmkv.MMKVTest;
import com.example.ormprefdemo.objectbox.ObjectBoxTest;
import com.example.ormprefdemo.realm.RealmTest;

import java.util.LinkedList;
import java.util.Queue;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private ObjectboxBinding mObjectBoxBinding;

    private int numberTime;
    private int numberEntity;

    private boolean objectBoxCheck;
    private boolean greenDaoCheck;
    private boolean realmCheck;
    private boolean mmkvCheck;
    private boolean dbFlowCheck;

    private Queue<PerfTest> list;
    private TestRunner testRunner;
    private PerfTest.OnSetLog logListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mObjectBoxBinding = ObjectboxBinding.inflate(getLayoutInflater());
        setContentView(mObjectBoxBinding.getRoot());
        testRunner = TestRunner.getInstance();
        list = new LinkedList<>();

        initView();
    }

    private void initView() {

        final EditText numberTimes = mObjectBoxBinding.numberTimes;
        final EditText numberEntities = mObjectBoxBinding.numberEntities;
        final Button button = mObjectBoxBinding.btnStart;

        button.setOnClickListener(view -> {
            numberTime = getIntegerFromEditTextOrZero(numberTimes);
            numberEntity = getIntegerFromEditTextOrZero(numberEntities);

            mObjectBoxBinding.tv.setText("");

            objectBoxCheck = mObjectBoxBinding.objectBoxCheck.isChecked();
            greenDaoCheck = mObjectBoxBinding.greenDaoCheck.isChecked();
            realmCheck = mObjectBoxBinding.realmCheck.isChecked();
            mmkvCheck = mObjectBoxBinding.mmkvCheck.isChecked();
            dbFlowCheck = mObjectBoxBinding.dbFlow.isChecked();

            runTest();
        });

        logListener = s -> mObjectBoxBinding.tv.append(s + "\n");
    }

    private void runTest() {
        if (objectBoxCheck)
            list.offer(new ObjectBoxTest(numberTime, numberEntity, logListener));

        if (greenDaoCheck)
            list.offer(new GreenDaoTest(numberTime, numberEntity, logListener));

        if (realmCheck)
            list.offer(new RealmTest(numberTime, numberEntity, logListener));

        if (mmkvCheck)
            list.offer(new MMKVTest(numberTime, numberEntity, logListener));

        if (dbFlowCheck)
            list.offer(new DBFlowTest(numberTime, numberEntity, logListener));

        testRunner.run(list);
    }

    private int getIntegerFromEditTextOrZero(EditText editText) {
        try {
            return Integer.parseInt(editText.getText().toString());
        } catch (NumberFormatException e) {
            mObjectBoxBinding.tv.append(e.getMessage() + "\n");
            return 0;
        }
    }
}