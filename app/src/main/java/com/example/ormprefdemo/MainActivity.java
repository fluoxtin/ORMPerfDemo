package com.example.ormprefdemo;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.ormprefdemo.databinding.ObjectboxBinding;
import com.example.ormprefdemo.greendao.GreenDaoTest;
import com.example.ormprefdemo.mmkv.MMKVTest;
import com.example.ormprefdemo.objectbox.ObjectBoxTest;
import com.example.ormprefdemo.realm.RealmTest;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private ObjectboxBinding mObjectBoxBinding;
    private int numberTime;
    private int numberEntity;
    private boolean objectBoxCheck;
    private boolean greenDaoCheck;
    private boolean realmCheck;
    private boolean mmkvCheck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mObjectBoxBinding = ObjectboxBinding.inflate(getLayoutInflater());
        setContentView(mObjectBoxBinding.getRoot());

        initView();
    }

    private void initView() {

        final EditText numberTimes = mObjectBoxBinding.numberTimes;
        final EditText numberEntities = mObjectBoxBinding.numberEntities;
        final Button button = mObjectBoxBinding.btnStart;

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numberTime = getIntegerFromEditTextOrZero(numberTimes);
                numberEntity = getIntegerFromEditTextOrZero(numberEntities);

                mObjectBoxBinding.tv.setText("");

                objectBoxCheck = mObjectBoxBinding.objectBoxCheck.isChecked();
                greenDaoCheck = mObjectBoxBinding.greenDaoCheck.isChecked();
                realmCheck = mObjectBoxBinding.realmCheck.isChecked();
                mmkvCheck = mObjectBoxBinding.mmkvCheck.isChecked();

                runTest(objectBoxCheck, greenDaoCheck, realmCheck, mmkvCheck);
            }
        });
    }

    private void runTest(boolean objectBox, boolean greenDao, boolean realm, boolean mmkv) {
        List<PerfTest> list = new ArrayList<>();
        if (objectBox) {
            list.add(new ObjectBoxTest());
        }

        if (greenDao) {
            list.add(new GreenDaoTest());
        }

        if (realm) {
            list.add(new RealmTest());
        }

        if (mmkv) {
            list.add(new MMKVTest());
        }

        new TestRunner(MainActivity.this, list, numberTime, numberEntity, mObjectBoxBinding);
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