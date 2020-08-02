package com.sincosmos.concurrency;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 计数器类，累计方法被调用的次数
 */
public class Counter {
    private long cnt1 = 0;
    private long cnt2 = 0;
    private AtomicLong cnt3 = new AtomicLong(0);
    private volatile long cnt4 = 0;

    private ReentrantLock lock = new ReentrantLock();
    private long cnt5 = 0;

    private synchronized void increaseCnt2(){
        cnt2++;
    }
    public long getCnt1(){return cnt1;}
    public synchronized long getCnt2(){return cnt2;}
    public long getCnt3(){return cnt3.get();}
    public long getCnt4(){return cnt4;}

    private void increaseCnt5(){
        lock.lock();
        try{
            cnt5++;
        }finally {
            lock.unlock();
        }

    }
    public long getCnt5(){
        lock.lock();
        try {
            return cnt5;
        }finally {
            lock.unlock();
        }
    }

    /**
     * 被调用方法，每被调用一次，计数加一
     */
    public void service(){
        //do something
        try {
            TimeUnit.MILLISECONDS.sleep(1000);
        } catch (InterruptedException e) {
            //e.printStackTrace();
        }
        cnt1++;
        increaseCnt2();
        cnt3.incrementAndGet();
        cnt4++;
        increaseCnt5();
    }
}
