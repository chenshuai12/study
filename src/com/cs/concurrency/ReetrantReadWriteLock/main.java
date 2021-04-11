package com.cs.concurrency.ReetrantReadWriteLock;

import java.util.Random;

public class main {
    public static void main(String[] args) {
        /**
         * 创建ReadWrite对象
         */
        final ReadWrite rw = new ReadWrite();
        for (int i = 0; i < 10; i++){
            /**
             * 创建并启动10个读线程
             */
            new Thread(() -> rw.get()).start();
            /**
             * 创建并启动10个写线程
             */
            new Thread(() -> rw.put(new Random().nextInt(8))).start();
        }
    }
}
