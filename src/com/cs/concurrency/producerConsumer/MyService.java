package com.cs.concurrency.producerConsumer;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class MyService {

    private ReentrantLock lock = new ReentrantLock();
    private Condition setCondition = lock.newCondition();
    private Condition getCondition = lock.newCondition();
    private boolean hasValue = false;

    public void set(){
        System.out.println("进入了生产者方法");
        lock.lock();
        try {
            while (hasValue){
                setCondition.await();
            }
            System.out.println("生产一个产品");
            hasValue = true;
            getCondition.signal();
        } catch (InterruptedException e) {
            System.out.println("收到中断请求");
            Thread.currentThread().interrupt();
        }finally {
            lock.unlock();
        }
    }
    public void get(){
        System.out.println("进入了消费者方法");
        lock.lock();
        try {
            while (!hasValue){
                getCondition.await();
            }
            System.out.println("消费一个产品");
            hasValue = false;
            setCondition.signal();
        } catch (InterruptedException e) {
            System.out.println("收到中断请求");
            Thread.currentThread().interrupt();
        }finally {
            lock.unlock();
        }
    }
}
