package com.cs.concurrency.tool.CyclicBarrier;

import java.util.concurrent.CyclicBarrier;

public class CycWork implements Runnable{

    private CyclicBarrier cyclicBarrier;
    private String name;

    public CycWork(CyclicBarrier cyclicBarrier,String name){
        this.cyclicBarrier = cyclicBarrier;
        this.name = name;
    }

    @Override
    public void run() {
        System.out.println(name + "正在打桩，毕竟比轻松。。。。。");
        try {
            Thread.sleep(5000);
            System.out.println(name + "不容易，终于把桩打完了。。。。");
            cyclicBarrier.await();
        }catch (Exception e){
            System.out.println(e);
        }
        System.out.println(name + ":其他憨批把桩都打完了，我又得继续干后面的活儿了");
    }
}
