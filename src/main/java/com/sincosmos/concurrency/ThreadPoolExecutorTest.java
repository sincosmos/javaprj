package com.sincosmos.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolExecutorTest {
    private ThreadPoolExecutor executorService = new ThreadPoolExecutor(0, Integer.MAX_VALUE,
            60L, TimeUnit.SECONDS,
            new SynchronousQueue<Runnable>()){
        @Override protected void beforeExecute(Thread t, Runnable r) {
            System.out.println("methods that are called\n" +
                    " * before execution of each task");
        }

        @Override protected void afterExecute(Runnable r, Throwable t){
            System.out.println("methods that are called\n" +
                    " * after execution of each task");
        }

    };

    public void config(){
        executorService.setKeepAliveTime(600, TimeUnit.SECONDS);
    }
}
