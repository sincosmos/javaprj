package com.sincosmos.concurrency;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MessageBufferQueue {
    private int watermark;
    private final ScheduledExecutorService scheduledExecutor = Executors.newScheduledThreadPool(2);
    private final ExecutorService executor =
            Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 2);

    private BlockingDeque<Object> buffer;
    private Lock lock = new ReentrantLock();
    public MessageBufferQueue(int watermark, int interval){
        buffer = new LinkedBlockingDeque<>();
        this.watermark = watermark;
        scheduledExecutor.scheduleAtFixedRate(()->{
            //consume
            if(buffer.size() > 0) {
                lock.lock();
                try{
                    if(buffer.size() > 0){
                        List<Object> tmp = new ArrayList<>(watermark);
                        buffer.drainTo(tmp, watermark);
                        consume(tmp);
                    }
                }finally {
                    lock.unlock();
                }
            }
        }, 5000, interval, TimeUnit.SECONDS);
    }

    public void addMessage(Object msg){
        buffer.add(msg);
        if(buffer.size() >= watermark){
            lock.lock();
            try{
                if(buffer.size() >= watermark){
                    List<Object> tmp = new ArrayList<>(watermark);
                    buffer.drainTo(tmp, watermark);
                    scheduledExecutor.submit(()->{
                        consume(tmp);
                    });
                }
            }finally {
                lock.unlock();
            }
        }
    }


    private void consume(List<Object> messages){
        while(!messages.isEmpty()){
            executor.submit(()->{
                //consume msg;
            });
        }
    }
}
