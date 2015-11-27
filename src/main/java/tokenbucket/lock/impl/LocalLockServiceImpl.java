package tokenbucket.lock.impl;

import tokenbucket.lock.LockService;

import java.util.concurrent.TimeUnit;

/**
 * @author wuhao
 */
public class LocalLockServiceImpl implements LockService {

    @Override
    public boolean lock(String source) {
        return false;
    }

    @Override
    public boolean tryLock(String source) {
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
