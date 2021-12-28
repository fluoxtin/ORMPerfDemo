package com.example.ormprefdemo;

import android.os.Handler;
import android.os.Looper;

public abstract class PerfTest {

    protected int EXECUTE_TIMES;
    protected int numberEntities;
    private final TimeMark mTimeMark;
    private int i = 0;
    private final Handler handler;
    private final OnSetLog listener;

    public PerfTest(int executeTimes, int numberEntities, OnSetLog callback) {
        EXECUTE_TIMES = executeTimes;
        this.numberEntities = numberEntities;
        listener = callback;
        mTimeMark = new TimeMark();
        handler = new Handler(Looper.getMainLooper());
    }

    protected abstract String name();

    protected abstract void run();

    protected void logName() {
        log("\n" + name());
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
        handler.post(() -> listener.setLog(s));
    }

    public interface OnSetLog {
        void setLog(String s);
    }
}
