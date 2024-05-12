package com.cs.concurrency.createThread;

import java.util.concurrent.Callable;

public class MyCallable implements Callable {
    @Override
    public Object call() throws Exception {
        System.out.println(this.getClass() + "   run");
        Thread.sleep(10000);
        return "csCallable";
    }
}
