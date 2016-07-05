package com.limiter.lock;

import java.util.concurrent.TimeUnit;

/**
 * @author wuhao
 */
public interface LockService {

    boolean lock(String source);

    boolean tryLock(String source);

    boolean tryLock(String source, long timeout, TimeUnit unit);

    boolean unlock(String source);

}
