package com.sincosmos.collection;

import java.util.Date;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class QueueInterruptibly {
    private static BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(10);

    private static void queuePutPoll(){
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0; i<12; ++i){
                    try {
                        //Block if queue is full
                        QueueInterruptibly.queue.put(i);
                    } catch (InterruptedException e) {
                        System.out.println("interrupted");
                        e.printStackTrace();
                    }
                }
            }
        });


        t1.start();

        QueueInterruptibly.queue.forEach(System.out::println);
        System.out.println("-----------------");
        QueueInterruptibly.queue.poll();
        QueueInterruptibly.queue.poll();
        QueueInterruptibly.queue.poll();
        QueueInterruptibly.queue.forEach(System.out::println);
    }

    private static void lockInterruptibly(){
        ReentrantLock lock = new ReentrantLock();

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try{
                    lock.lockInterruptibly();
                    System.out.println(String.format("%s %s locked", new Date(System.currentTimeMillis()), Thread.currentThread().getName()));
                } catch (InterruptedException e) {
                    System.out.println(String.format("%s %s interrupted", new Date(System.currentTimeMillis()), Thread.currentThread().getName()));
                }finally {
                    //System.out.println(String.format("%s %s unlocked", new Date(System.currentTimeMillis()), Thread.currentThread().getName()));
                    //lock.unlock();
                }
            }
        };

        Runnable runnableNew = new Runnable() {
            @Override
            public void run() {
                lock.lock();
                try{
                    System.out.println(String.format("%s %s locked", new Date(System.currentTimeMillis()), Thread.currentThread().getName()));
                } finally {
                    //System.out.println(String.format("%s %s unlocked", new Date(System.currentTimeMillis()), Thread.currentThread().getName()));
                    //lock.unlock();
                }
            }
        };

        Thread t1 = new Thread(runnableNew, "Thread 1");
        Thread t2 = new Thread(runnableNew, "Thread 2");
        t1.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t2.start();
        t2.interrupt();
    }

    public static void main(String[] args){
        //queuePutPoll();
        lockInterruptibly();
    }
}
