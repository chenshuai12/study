package com.cs.concurrency.producerConsumer;

public class Producer extends Thread {
    private MyService myService;
    public Producer(MyService myService){
        this.myService = myService;
    }
    @Override
    public void run(){
        for (int i = 0; i < 10; i++){
            myService.set();
        }
    }
}
