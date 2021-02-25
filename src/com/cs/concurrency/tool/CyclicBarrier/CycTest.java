package com.cs.concurrency.tool.CyclicBarrier;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CycTest {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        CyclicBarrier cyclicBarrier = new CyclicBarrier(3);
        CycWork work1 = new CycWork(cyclicBarrier,"张三");
        CycWork work2 = new CycWork(cyclicBarrier,"李四");
        CycWork work3 = new CycWork(cyclicBarrier,"王五");
        executorService.execute(work1);
        executorService.execute(work2);
        executorService.execute(work3);
        executorService.shutdown();
    }
}
