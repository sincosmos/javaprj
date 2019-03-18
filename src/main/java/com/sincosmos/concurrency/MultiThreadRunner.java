package com.sincosmos.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MultiThreadRunner {
    public static void main(String[] args){
        System.out.println("starting...");
        Counter counter = new Counter();
        ExecutorService executor = Executors.newFixedThreadPool(100);
        long st=System.currentTimeMillis();
        for(int i=0; i<50000; i++){
            executor.execute(counter::service);
        }
        executor.shutdown();

        try {
            // 10s 退出
            if(!executor.awaitTermination(10, TimeUnit.SECONDS)){
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            //e.printStackTrace();
        }
        long ed= System.currentTimeMillis();
        System.out.println("time: " + (ed-st)/1000.0);
        System.out.println("cnt1: " + counter.getCnt1());
        System.out.println("cnt2: " + counter.getCnt2());
        System.out.println("cnt3: " + counter.getCnt3());
        System.out.println("cnt4: " + counter.getCnt4());
        System.out.println("cnt5: " + counter.getCnt5());
    }
}
