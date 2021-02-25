package com.cs.concurrency.tool.CountDownlatch;

import java.util.concurrent.CountDownLatch;

public class Boss implements Runnable{
    private CountDownLatch countDownLatch;
    public Boss(CountDownLatch countDownLatch){
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        System.out.println("老板正在等待所有的工人干完活");
        try {
            this.countDownLatch.await();
        }catch (InterruptedException e){
            System.out.println(e);
        }
        System.out.println("工人活干完了，老板开始检查了!");
    }
}
