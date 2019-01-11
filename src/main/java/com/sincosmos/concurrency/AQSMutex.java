package com.sincosmos.concurrency;

import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class AQSMutex implements Lock {
    private static final class Sync extends AbstractQueuedSynchronizer {
        @Override
        public boolean tryAcquire(int acquires){
            assert acquires == 1; //otherwise unused
            if(compareAndSetState(0, 1)){
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }
            return false;
        }

        @Override
        protected boolean isHeldExclusively(){
            return getState() == 1;
        }

        @Override
        protected boolean tryRelease(int releases){
            assert releases == 0;
            if(getState() == 0) throw new IllegalMonitorStateException();
            setExclusiveOwnerThread(null);
            setState(releases);
            return true;
        }

        Condition newCondition() {
            return new ConditionObject();
        }
    }

    private final Sync sync = new Sync();

    @Override
    public void lock() {sync.tryAcquire(1);}

    @Override
    public void lockInterruptibly() throws InterruptedException {
        sync.acquireInterruptibly(1);
    }

    @Override
    public boolean tryLock() {return sync.tryAcquire(1);}

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public void unlock() {sync.tryRelease(0);}

    @Override
    public Condition newCondition() {return sync.newCondition();}

    public boolean isLocked(){return sync.isHeldExclusively();}

    public boolean hasQueuedThreads() { return sync.hasQueuedThreads(); }
}
