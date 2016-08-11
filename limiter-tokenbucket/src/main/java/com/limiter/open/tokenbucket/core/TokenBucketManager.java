package com.limiter.open.tokenbucket.core;

import com.limiter.open.lock.LockService;
import com.limiter.open.tokenbucket.domain.TokenBucket;

import java.util.concurrent.TimeUnit;

/**
 * @author wuhao
 */
public class TokenBucketManager implements LockService, TokenBucketDao {

    private LockService lockService;

    private TokenBucketDao tokenBucketDAO;

    public void setLockService(LockService lockService) {
        this.lockService = lockService;
    }

    public void setTokenBucketDAO(TokenBucketDao tokenBucketDAO) {
        this.tokenBucketDAO = tokenBucketDAO;
    }

    @Override
    public boolean lock(String tokenBucketKey) {
        return lockService.lock(tokenBucketKey);
    }

    @Override
    public boolean tryLock(String tokenBucketKey) {
        return lockService.tryLock(tokenBucketKey);
    }

    @Override
    public boolean tryLock(String tokenBucketKey, long timeout, TimeUnit unit) {
        return lockService.tryLock(tokenBucketKey, timeout, unit);
    }

    @Override
    public boolean unlock(String tokenBucketKey) {
        return lockService.unlock(tokenBucketKey);
    }

    @Override
    public TokenBucket getTokenBucket(String tokenBucketKey) {
        return tokenBucketDAO.getTokenBucket(tokenBucketKey);
    }

    @Override
    public boolean saveTokenBucket(TokenBucket tokenBucket) {
        return tokenBucketDAO.saveTokenBucket(tokenBucket);
    }
}
