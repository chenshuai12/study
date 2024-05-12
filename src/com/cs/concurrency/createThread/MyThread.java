package com.cs.concurrency.createThread;

public class MyThread extends Thread {
    public void run(){
        System.out.println(this.getClass() + "   run");
    }
}
