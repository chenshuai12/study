package com.cs.concurrency.ReentrantLock;


import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class main {
    static class LockTest implements Runnable {
        Lock lock;

        public LockTest(Lock lock) {
            this.lock = lock;
        }

        public void method1() {
            try {
                Boolean  flag = lock.tryLock(1, TimeUnit.SECONDS);
                System.out.println(Thread.currentThread().getName() + "获取锁" + flag);
                if (flag) {
                    System.out.println(Thread.currentThread().getName() + "method1正常执行");
                    method2();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
                System.out.println(Thread.currentThread().getName() + "释放锁成功");
            }
        }
        public void method2 () {
            try {
                Boolean flag = lock.tryLock(1, TimeUnit.SECONDS);
                System.out.println(Thread.currentThread().getName() + "获取锁" + flag);
                if (flag){
                    Thread.sleep(5000);
                    System.out.println(Thread.currentThread().getName() + "method2也正常运行");
                }
            }catch (InterruptedException e){
                e.printStackTrace();
            }finally {
                lock.unlock();
                System.out.println(Thread.currentThread().getName() + "释放锁成功");
            }
        }
            @Override
            public void run () {
                method1();
            }
        }

        public static void main(String[] args) {
            Lock lock = new ReentrantLock();
            LockTest lockTest = new LockTest(lock);
            for (int i = 0; i < 2; i++) {
                new Thread(lockTest).start();
            }
        }
    }
