package com.sincosmos.concurrency;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockTest {
    public static void main(String[] args){
        final SharedData sharedData = new SharedData();
        ExecutorService executor = Executors.newFixedThreadPool(6);

        for(int i=0; i<3; i++){
            executor.submit(()->{while(true) sharedData.get();});
        }
        for(int i=0; i<3; i++){
            executor.submit(()->{sharedData.put(new Random().nextInt(10000));});
        }
        executor.shutdown();
    }

    private static class SharedData {
        private Object data = null;
        private ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
        void get(){
            rwl.readLock().lock();
            System.out.println(Thread.currentThread().getName() + " is ready to read data!");
            try{
                TimeUnit.MILLISECONDS.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                System.out.println(Thread.currentThread().getName() + " has read data:" + data);
                rwl.readLock().unlock();
            }
        }

        void put(Object data){
            rwl.writeLock().lock();
            System.out.println(Thread.currentThread().getName() + " is ready to write data!");
            try{
                TimeUnit.MILLISECONDS.sleep(1000);
                this.data = data;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                System.out.println(Thread.currentThread().getName() + " has write data:" + data);
                rwl.writeLock().unlock();
            }
        }
    }
}
