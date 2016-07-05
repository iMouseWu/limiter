package com.limiter.lock.impl;

import java.util.concurrent.TimeUnit;

import com.limiter.lock.LockService;

/**
 * @author wuhao
 */
public class RedisLockServiceImpl implements LockService {
    @Override
    public boolean lock(String source) {
        return false;
    }

    @Override
    public boolean tryLock(String source) {
        //        {
        //            long nowNanoTime = System.nanoTime();
        //            for (; ; ) {
        //                if (nanoTime > DEFAULT_MIN_NANOTIME) {
        //                    TimeUnit.NANOSECONDS.sleep(nanoTime);
        //                }
        //                if ((System.nanoTime() - nowNanoTime) > nanoTime) {
        //                    return tryConsume();
        //                }
        //                if (Thread.interrupted()) {
        //                    throw new InterruptedException();
        //                }
        //            }
        //        }
        return false;
    }

    @Override
    public boolean tryLock(String source, long timeout, TimeUnit unit) {
        return false;
    }

    @Override
    public boolean unlock(String source) {
        return false;
    }
}
