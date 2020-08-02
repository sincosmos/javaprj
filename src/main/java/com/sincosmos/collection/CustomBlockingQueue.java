package com.sincosmos.collection;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class CustomBlockingQueue<T> {
    private final int capacity;
    private Semaphore notFull;
    private Semaphore notEmpty;
    private List<T> holder;
    public CustomBlockingQueue(int capacity){
        this.capacity = capacity;
        this.notFull = new Semaphore(capacity);
        this.notEmpty = new Semaphore(0);
        holder = Collections.synchronizedList(new LinkedList<>());
    }

    //Blocks if queue is full
    public void put(T x){
        try {
            notFull.acquire();
            if(holder.add(x)){
                notEmpty.release();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //Blocks if queue is empty
    public T take() {
        T rtn = null;
        try {
            notEmpty.acquire();
            rtn = holder.remove(0);
            notFull.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return rtn;
    }

    public static void main(String[] args){
        CustomBlockingQueue<Integer> cbq = new CustomBlockingQueue<>(2);

        ExecutorService executorService = Executors.newCachedThreadPool();
        /*executorService.execute(()->{
            for(int i=1; i<10; i++){
                cbq.put(i);
                System.out.println("Put " + i);
            }
        });*/

        executorService.execute(()->{
            System.out.println(Thread.currentThread().getName() + " taking " + cbq.take());
        });

        executorService.execute(()->{
            System.out.println(Thread.currentThread().getName() + " taking " + cbq.take());
        });

        /*executorService.execute(()->{
            System.out.println(Thread.currentThread().getName() + " taking " + cbq.take());
        });*/

        executorService.shutdown();
    }
}
