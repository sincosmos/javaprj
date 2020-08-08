package com.sincosmos.concurrency;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class HashMapConcurrencyProblem {
    public static void main(String[] args){
        HashMap<Integer, Integer> map = new HashMap<>();
        AtomicInteger atomicInteger = new AtomicInteger();
        for(int i=0; i<10; ++i){
            new Thread(()->{
                while(atomicInteger.get() < 1000000){
                    map.put(atomicInteger.get(), atomicInteger.get());
                    atomicInteger.incrementAndGet();
                }
            }).start();
        }
    }
}
