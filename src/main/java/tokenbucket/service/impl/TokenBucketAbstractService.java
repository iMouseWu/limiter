package tokenbucket.service.impl;

import tokenbucket.service.TokenBucketService;

import java.util.concurrent.TimeUnit;

/**
 * @author wuhao
 */
public abstract class TokenBucketAbstractService implements TokenBucketService {

    protected abstract boolean lock(String tokenBucketKey);

    protected abstract boolean tryLock(String tokenBucketKey);

    protected abstract boolean unlock(String tokenBucketKey);

    protected abstract boolean tryLock(String tokenBucketKey, long timeout, TimeUnit unit);

    @Override
    public boolean consume(String tokenBucketKey) {
        if (lock(tokenBucketKey)) {
            try {
                return doConsume(tokenBucketKey);
            } finally {
                unlock(tokenBucketKey);
            }
        }
        return false;
    }

    @Override
    public boolean tryConsume(String tokenBucketKey) {
        if (tryLock(tokenBucketKey)) {
            try {
                return doConsume(tokenBucketKey);
            } finally {
                unlock(tokenBucketKey);
            }
        }
        return false;
    }

    protected abstract boolean doConsume(String tokenBucketKey);

    @Override
    public boolean tryConsume(String tokenBucketKey, long timeout, TimeUnit timeUnit) {
        if (tryLock(tokenBucketKey, timeout, timeUnit)) {
            try {
                return doConsume(tokenBucketKey);
            } finally {
                unlock(tokenBucketKey);
            }
        }
        return false;
    }
}
