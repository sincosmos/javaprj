package com.sincosmos.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ThreadLocalInside {
    private static ThreadLocal<Integer> v1 = new ThreadLocal<>();
    private static ThreadLocal<String> v2 = new ThreadLocal<>();


    public static void main(String[] args){
        ThreadLocalInside inside = new ThreadLocalInside();
        //inside.testMultiThread();
        inside.digIntoThreadLocal();
    }

    public void digIntoThreadLocal(){
        v1.set(1);
        v2.set("2");
        System.out.println(v2.get());
    }

    public void testMultiThread(){
        ExecutorService executor = Executors.newFixedThreadPool(3);
        executor.execute(() -> {
            v1.set(0);
            v2.set("str" + 0);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + v2.get());
        });
        executor.execute(() -> {
            v1.set(1);
            v2.set("str" + 1);
            System.out.println(Thread.currentThread().getName() + v2.get());
        });
        executor.execute(() -> {
            v1.set(2);
            v2.set("str" + 2);
            System.out.println(Thread.currentThread().getName() + v2.get());
        });
        executor.shutdown();
    }

}
