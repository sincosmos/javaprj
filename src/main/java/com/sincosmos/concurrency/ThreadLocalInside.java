package com.sincosmos.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadLocalInside {
    private static ThreadLocal<Integer> v1 = new ThreadLocal<>();
    private static ThreadLocal<String> v2 = new ThreadLocal<>();

    public static void main(String[] args){
        ExecutorService executor = Executors.newFixedThreadPool(3);
        executor.execute(() -> {
            v1.set(0);
            v2.set("str" + 0);
        });
        executor.execute(() -> {
            v1.set(1);
            v2.set("str" + 1);
        });
        executor.execute(() -> {
            v1.set(2);
            v2.set("str" + 2);
        });
    }

}
