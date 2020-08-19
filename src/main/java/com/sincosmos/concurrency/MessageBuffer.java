package com.sincosmos.concurrency;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class MessageBuffer {
    private List<Object> buffer;
    private int watermark;
    private final ScheduledExecutorService scheduledExecutor = Executors.newScheduledThreadPool(2);
    private final ExecutorService executor =
            Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 2);
    private final ReadWriteLock lock = new ReentrantReadWriteLock();

    private final Lock readLock = lock.readLock();
    private final Lock writeLock = lock.writeLock();

    public MessageBuffer(int watermark, int interval){
        this.watermark = watermark;
        buffer = new ArrayList<>();
        scheduledExecutor.scheduleAtFixedRate(()->{
            //consume
            //update buffer to a new ArrayList, use writeLock
            writeLock.lock();
            try{
                if(buffer.size() > 0) {
                    consume(buffer);
                    buffer = new ArrayList<>();
                }
            }finally {
                writeLock.unlock();
            }
        }, 5000, interval, TimeUnit.SECONDS);
    }

    public void addMessage(Object msg){
        buffer.add(msg);
        if(buffer.size() > watermark){
            //update buffer to a new ArrayList, use writeLock
            writeLock.lock();
            try{
                if(buffer.size() > watermark){
                    scheduledExecutor.submit(()->{
                        consume(buffer);
                    });
                    buffer = new ArrayList<>();
                }
            }finally {
                writeLock.unlock();
            }
        }
    }


    private void consume(List<Object> messages){
        for(Object msg : messages){
            executor.submit(()->{
                //consume msg;
            });
        }
    }
}
