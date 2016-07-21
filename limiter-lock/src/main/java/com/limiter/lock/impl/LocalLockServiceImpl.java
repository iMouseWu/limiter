package com.limiter.lock.impl;

import com.limiter.lock.LockService;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author wuhao
 */
public class LocalLockServiceImpl implements LockService {

    private static volatile Map<String, ReentrantLock> lockMap = new HashMap<>();

    @Override
    public boolean lock(String source) {
        ReentrantLock reentrantLock = initAndGetLock(source);
        reentrantLock.lock();
        return true;
    }

    @Override
    public boolean tryLock(String source) {
        ReentrantLock reentrantLock = initAndGetLock(source);
        return reentrantLock.tryLock();
    }

    @Override
    public boolean tryLock(String source, long timeout, TimeUnit unit) {
        ReentrantLock reentrantLock = initAndGetLock(source);
        try {
            return reentrantLock.tryLock(timeout, unit);
        } catch (InterruptedException e) {
            return false;
        }
    }

    @Override
    public boolean unlock(String source) {
        ReentrantLock reentrantLock = lockMap.get(source);
        if (null == reentrantLock) {
            throw new RuntimeException("source error " + source);
        }
        reentrantLock.unlock();
        return true;
    }

    private ReentrantLock initAndGetLock(String source) {
        ReentrantLock reentrantLock = lockMap.get(source);
        if (null == reentrantLock) {
            synchronized (this) {
                reentrantLock = lockMap.get(source);
                if (null == reentrantLock) {
                    reentrantLock = new ReentrantLock(true);
                    lockMap.put(source, reentrantLock);
                }
            }
        }
        return reentrantLock;
    }
}
