package com.cs.concurrency;

import com.google.common.util.concurrent.ThreadFactoryBuilder;


import java.util.concurrent.*;


public class test {

    private static ThreadPoolExecutor pool = new ThreadPoolExecutor(2, 2, 1, TimeUnit.MINUTES, new ArrayBlockingQueue<>(1),
            new ThreadFactoryBuilder().setNameFormat("测试-pool-%d").build(),
            (r, executor) -> {
                throw new RuntimeException("线程池满了");
            });
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++){
            int finalI = i;
            pool.submit(() -> {
                System.out.println(Thread.currentThread() + "第" + finalI + "个任务");
                try {
                    Thread.sleep(100000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
    }
}
