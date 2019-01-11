package com.sincosmos.concurrency;

import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockTest {
    private ArrayList<Integer> list = new ArrayList<>();
    private Lock lock = new ReentrantLock();
    public static void main(String[] args){
        final ReentrantLockTest test = new ReentrantLockTest();

        new Thread(){
            public void run(){
                test.insert(Thread.currentThread());
            }
        }.start();

        new Thread(){
            public void run(){
                test.insert(Thread.currentThread());
            }
        }.start();
    }

    public void insert(Thread thread){
        //局部变量，并不能起到保护作用
        //Lock lock = new ReentrantLock();
        lock.lock();
        try{
            System.out.println(thread.getName() + " acquired lock");
            for(int i=0; i<5; i++){
                list.add(i);
            }
        }catch (Exception e){

        }finally {
            System.out.println(thread.getName() + " released lock");
            lock.unlock();
        }
    }
}
