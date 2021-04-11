package com.cs.concurrency.semaphore;

import java.util.concurrent.Semaphore;

public class main {
    static class TaskThread extends Thread{
        Semaphore semaphore;
        public TaskThread(Semaphore semaphore){
            this.semaphore = semaphore;
        }
        @Override
        public void run(){
            try {
                semaphore.acquire();
                System.out.println(getName() + " acquire");
                Thread.sleep(1000);
                semaphore.release();
                System.out.println(getName() + " release");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        int threadNum = 5;
        Semaphore semaphore = new Semaphore(2);
        for (int i = 0; i < 5; i++){
            new TaskThread(semaphore).start();
        }
    }
}
