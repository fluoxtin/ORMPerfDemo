package com.example.ormprefdemo;

import android.app.Activity;

import com.example.ormprefdemo.databinding.ObjectboxBinding;


public abstract class PerfTest {

    protected int EXECUTE_TIMES;
    protected int numberEntities;
    protected Activity mActivity;
    private final TimeMark mTimeMark;
    private int i = 0;
    private ObjectboxBinding mBinding;

    public PerfTest() {
        mTimeMark = new TimeMark();
    }

    protected abstract String name();

    protected abstract void run();

    protected void logName() {
        log("\n" + name());
    }

    protected void init(Activity activity, int executeTimes, int numberEntities, ObjectboxBinding binding) {
        mActivity = activity;
        EXECUTE_TIMES = executeTimes;
        this.numberEntities = numberEntities;
        mBinding = binding;
    }


    protected void start(String name) {
        mTimeMark.start(name);
    }

    protected void end() {
        mTimeMark.end();
        i++;
        if (i == EXECUTE_TIMES) {
            log(mTimeMark.logMessage);
            mTimeMark.times.clear();
            i = 0;
        }
    }

    protected void log(String s) {
        mActivity.runOnUiThread(() -> {
            mBinding.tv.append(s + "\n");
        });
    }
}
