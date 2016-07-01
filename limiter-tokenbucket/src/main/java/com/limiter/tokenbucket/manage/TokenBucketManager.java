package com.limiter.tokenbucket.manage;

import java.util.concurrent.TimeUnit;

import com.limiter.lock.LockService;
import com.limiter.tokenbucket.dao.TokenBucketDAO;
import com.limiter.tokenbucket.domain.TokenBucket;

/**
 * @author wuhao
 */
public class TokenBucketManager implements LockService, TokenBucketDAO {

    private LockService lockService;

    private TokenBucketDAO tokenBucketDAO;

    public void setLockService(LockService lockService) {
        this.lockService = lockService;
    }

    public void setTokenBucketDAO(TokenBucketDAO tokenBucketDAO) {
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
