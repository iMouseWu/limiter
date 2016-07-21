package com.limiter.lock;

import com.limiter.lock.impl.LocalLockServiceImpl;

/**
 * @author wuhao
 */
public class ObjectFactory {

    private static LocalLockServiceImpl localLockServiceImpl = new LocalLockServiceImpl();

    public static LocalLockServiceImpl getLocalLockServiceInstance() {
        return localLockServiceImpl;
    }
}
