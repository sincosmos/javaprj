package com.sincosmos.concurrency;

import java.util.concurrent.*;

public class LatchBarrier {
    private static void latch(){
        //serves as a simple on/off latch, or gate
        CountDownLatch startSignal = new CountDownLatch(1);
        //all worker completed
        CountDownLatch doneSignal = new CountDownLatch(5);

        ExecutorService executor = Executors.newFixedThreadPool(5);
        for(int i=0; i<5; ++i){
            executor.submit(new Worker(startSignal, doneSignal));
        }
        //* Initiates an orderly shutdown in which previously submitted
        //* tasks are executed, but no new tasks will be accepted.
        executor.shutdown();

        System.out.println("Preparing...");
        //all worker start
        startSignal.countDown();

        //wait for all worker done
        try {
            doneSignal.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("all worker done.");
    }

    private static void barrier(){
        CyclicBarrier cb = new CyclicBarrier(5, ()->System.out.println("all runner ready"));
        ExecutorService executor = Executors.newFixedThreadPool(5);
        for(int i=0; i<5; ++i){
            executor.submit(new Runner(cb));
        }
        executor.shutdown();
    }

    public static void main(String[] args){
        //latch();
        barrier();
    }
}
class Runner implements Runnable{
    private CyclicBarrier cb;
    public Runner(CyclicBarrier cb){
        this.cb = cb;
    }
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " preparing...");
        try {
            cb.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " done...");
    }
}


class Worker implements Runnable{
    private final CountDownLatch startSignal;
    private final CountDownLatch doneSignal;
    Worker(CountDownLatch startSignal, CountDownLatch doneSignal){
        this.startSignal = startSignal;
        this.doneSignal = doneSignal;
    }

    @Override
    public void run() {
        try {
            startSignal.await();
            doWork();
            doneSignal.countDown();
        } catch (InterruptedException ex) {} // return;
    }

    private void doWork() throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + " is starting...");
        //TimeUnit.SECONDS.sleep(3);
        System.out.println(Thread.currentThread().getName() + " is done.");
    }
}
