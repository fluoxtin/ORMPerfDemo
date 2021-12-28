package com.example.ormprefdemo;

import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestRunner {

    ExecutorService es;
    static TestRunner testRunner;

    public static TestRunner getInstance() {
        if (testRunner == null)
            testRunner = new TestRunner();
        return testRunner;
    }

    private TestRunner() {
        es = Executors.newSingleThreadExecutor();
    }


    public synchronized void run (Queue<PerfTest> list) {

        while (list.peek() != null) {
            PerfTest test = list.poll();
            es.submit(() -> {
                test.logName();
                test.run();
            });
        }
    }
}
