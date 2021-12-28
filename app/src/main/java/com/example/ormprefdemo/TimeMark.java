package com.example.ormprefdemo;

import android.os.SystemClock;
import android.util.Log;

import java.util.ArrayList;
import java.util.IntSummaryStatistics;

public class TimeMark {

    private static final String TAG = "TimeMark";

    private boolean started;
    private long timeMills;
    private String name;
    public String logMessage;
    public ArrayList<Integer> times;

    public TimeMark() {
        times = new ArrayList<>();
    }

    public void start(String name) {
        if (started) {
            throw new RuntimeException("Already start");
        }
        started = true;
        this.name = name;
        timeMills = SystemClock.elapsedRealtime();
    }

    public void end() {
        long time = SystemClock.elapsedRealtime() - timeMills;
        if (!started) {
            throw new RuntimeException("Already stop");
        }
        started = false;
        times.add((int) time);

        logMessage = name + " : " + "\t" + averageTime(times) + " ms";
        Log.d(TAG, "time: " + time);

    }

    private double averageTime(ArrayList<Integer> times) {
        IntSummaryStatistics statistics =  times
                .stream()
                .mapToInt((x) -> x)
                .summaryStatistics();
        return statistics.getAverage();
    }
}
