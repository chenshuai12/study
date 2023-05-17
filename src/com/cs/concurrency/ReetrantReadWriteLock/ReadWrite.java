package com.cs.concurrency.ReetrantReadWriteLock;

import lombok.*;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ReadWrite {
    /**
     * 共享数据，可以多个线程读数据，只能有一个线程写数据
     */
    private int data;
    /**
     * 创建读写锁
     */
    ReadWriteLock rwLock = new ReentrantReadWriteLock();
    /**
     * 读数据,上读锁
     */
    public void get(){
        //读锁
        rwLock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + ",Read!");
            Thread.sleep(2000);
            System.out.println(Thread.currentThread().getName() + "读出数据为：" + this.getData());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            rwLock.readLock().unlock();
        }
    }
    /**
     * 读写锁，上写锁
     */
    public void put(int data){
        //写锁
        rwLock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "，Write!");
            Thread.sleep(2000);
            this.setData(data);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            rwLock.writeLock().unlock();
        }
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }
}
