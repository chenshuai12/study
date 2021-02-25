package com.cs.concurrency.tool.CountDownlatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestLatch {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newCachedThreadPool();

        CountDownLatch countDownLatch = new CountDownLatch(3);
        Work work1 = new Work(countDownLatch,"张三");
        Work work2 = new Work(countDownLatch,"李四");
        Work work3 = new Work(countDownLatch,"王五");
        Boss boss = new Boss(countDownLatch);
        executor.execute(work1);
        executor.execute(boss);
        executor.execute(work2);
        executor.execute(work3);
        executor.shutdown();
    }
}
