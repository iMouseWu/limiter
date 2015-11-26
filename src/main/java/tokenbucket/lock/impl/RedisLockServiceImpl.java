package tokenbucket.lock.impl;

import tokenbucket.lock.LockService;

/**
 * @author wuhao
 */
public class RedisLockServiceImpl implements LockService{
    @Override
    public boolean lock(String source) {
        return false;
    }

    @Override
    public boolean tryLock(String source) {
        return false;
    }

    @Override
    public boolean unlock(String source) {
        return false;
    }
}
