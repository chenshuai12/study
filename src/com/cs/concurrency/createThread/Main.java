package com.cs.concurrency.createThread;

import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) {
        Thread myThread = new MyThread();
        myThread.start();
        Thread myRnnable = new Thread(new MyRunnable());
        myRnnable.run();

        MyCallable myCallable = new MyCallable();
        FutureTask futureTask = new FutureTask(myCallable);
        futureTask.run();
        try {
            System.out.println(futureTask.get());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }

        System.out.println("测试结束");
    }
}


