package com.cs.concurrency.createThread;

public class MyRunnable implements Runnable{
    @Override
    public void run() {
        System.out.println(this.getClass() + "   run");
    }
}
