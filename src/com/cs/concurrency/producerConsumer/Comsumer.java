package com.cs.concurrency.producerConsumer;

public class Comsumer extends Thread {
    private MyService myService;
    public Comsumer(MyService myService){
        this.myService = myService;
    }
    @Override
    public void run(){
        for (int i = 0; i < 10; i++){
            myService.get();
        }
    }
}
