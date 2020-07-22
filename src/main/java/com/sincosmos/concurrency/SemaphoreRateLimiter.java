package com.sincosmos.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class SemaphoreRateLimiter {
    public static void main(String[] args){
        final Semaphore sem = new Semaphore(5);
        Runnable job = new Runnable() {
            @Override
            public void run() {
                try {
                    sem.acquire();
                    System.out.println(Thread.currentThread().getName() + " is running...");
                    TimeUnit.SECONDS.sleep(5);
                    sem.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        ExecutorService executor = Executors.newCachedThreadPool();
        for(int i=0; i<20; i++){
            executor.execute(job);
        }
        executor.shutdown();
    }
}
