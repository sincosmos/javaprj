package com.sincosmos.concurrency;

import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Lock 的 lock() 方法和 lockInterruptibly() 方法的差异测试
 */
public class LockInterruptiblyTest {
    /**
     * 在 lockNoInterrupt 上使用 lock() 方法
     */
    ReentrantLock lockNoInterrupt = new ReentrantLock();
    public void noInterruptLock(){
        Runnable noInterruptLock = () -> {
            lockNoInterrupt.lock();
            try{
                System.out.println(String.format("%s %s locked", new Date(System.currentTimeMillis()), Thread.currentThread().getName()));
            }finally{
                //这里不释放锁，测试锁竞争者如何处理中断
                //lockNoInterrupt.unlock();
            }
        };
        //lockNoInterrupt 将被下面的两个线程竞争
        Thread noInterruptThread1 = new Thread(noInterruptLock, "No Interrupt Thread 1");
        Thread noInterruptThread2 = new Thread(noInterruptLock, "No Interrupt Thread 2");
        //No Interrupt Thread 1 获取锁，并且不释放
        noInterruptThread1.start();
        try{
            TimeUnit.MILLISECONDS.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //No Interrupt Thread 2 尝试获取锁，会被 block
        noInterruptThread2.start();
        try{
            TimeUnit.MILLISECONDS.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //No Interrupt Thread 2 无法得到锁，尝试中断，但中断不会被线程响应
        noInterruptThread2.interrupt();
    }

    /**
     * 在 lockInterrupt 上使用 lockInterruptibly() 方法
     */
    ReentrantLock lockInterrupt = new ReentrantLock();
    public void lockInterrupt(){
        //测试 lockInterruptibly() 方法
        Runnable interruptLock = () -> {
            try{
                lockInterrupt.lockInterruptibly();
                System.out.println(String.format("%s %s locked", new Date(System.currentTimeMillis()), Thread.currentThread().getName()));
            }catch (InterruptedException e) {
                System.out.println(String.format("%s %s interrupted", new Date(System.currentTimeMillis()), Thread.currentThread().getName()));
            }finally{
                //这里不释放锁，测试锁竞争者如何处理中断
                //lockInterrupt.unlock();
            }
        };
        //lockInterrupt 将被下面的两个线程竞争
        Thread interruptThread1 = new Thread(interruptLock, "Interrupt Thread 1");
        Thread interruptThread2 = new Thread(interruptLock, "Interrupt Thread 2");
        //Interrupt Thread 1 获取锁，并且不释放
        interruptThread1.start();
        try{
            TimeUnit.MILLISECONDS.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //Interrupt Thread 2 尝试获取锁，会被 block
        interruptThread2.start();
        try{
            TimeUnit.MILLISECONDS.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //Interrupt Thread 2 无法得到锁，尝试中断，线程中断后结束
        interruptThread2.interrupt();
    }

    public static void main(String[] args){
        LockInterruptiblyTest test = new LockInterruptiblyTest();
        test.noInterruptLock();
        test.lockInterrupt();
    }
}
