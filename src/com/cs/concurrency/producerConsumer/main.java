package com.cs.concurrency.producerConsumer;

public class main {
    public static void main(String[] args) {
        MyService myService = new MyService();
        Producer producer = new Producer(myService);
        Comsumer consumer = new Comsumer(myService);
        producer.start();
        consumer.start();
    }
}
