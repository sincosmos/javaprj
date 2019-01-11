package com.sincosmos.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MultiThreadRunner {
    public static void main(String[] args){
        Counter counter = new Counter();
        ExecutorService executor = Executors.newCachedThreadPool();
        for(int i=0; i<500; i++){
            executor.execute(counter::service);
        }
        executor.shutdown();
        try {
            executor.awaitTermination(1, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("cnt1: " + counter.getCnt1());
        System.out.println("cnt2: " + counter.getCnt2());
        System.out.println("cnt3: " + counter.getCnt3());
        System.out.println("cnt4: " + counter.getCnt4());
        System.out.println("cnt5: " + counter.getCnt5());
    }
}
