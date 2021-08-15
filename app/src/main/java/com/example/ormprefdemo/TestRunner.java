package com.example.ormprefdemo;

import android.app.Activity;

import com.example.ormprefdemo.databinding.ObjectboxBinding;

import java.util.List;

public class TestRunner {

    boolean running;
    int numberEntities;
    int numberTimes;
    Activity mActivity;
    ObjectboxBinding mBinding;
    List<PerfTest> mPerfTestList;

    public TestRunner(Activity activity, List<PerfTest> test, int numberTimes, int numberEntities, ObjectboxBinding binding) {
        mActivity = activity;
        this.numberTimes = numberTimes;
        this.numberEntities = numberEntities;
        mBinding = binding;
        mPerfTestList = test;
        run(test);
    }

    public void run (List<PerfTest> list) {
        if (running) {
            throw new RuntimeException("Already running!");
        }

        running = true;
        new Thread(() -> {
            for (PerfTest test : list) {
                test.init(mActivity, numberTimes, numberEntities, mBinding);
                test.logName();
                test.run();
            }
         }).start();
    }
}
