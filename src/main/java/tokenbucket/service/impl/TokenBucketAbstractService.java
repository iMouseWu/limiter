package tokenbucket.service.impl;

import tokenbucket.service.TokenBucketService;

import java.util.concurrent.TimeUnit;

/**
 * @author wuhao
 */
public abstract class TokenBucketAbstractService implements TokenBucketService {

    protected abstract boolean lock(String source);

    protected abstract boolean tryLock(String source);

    protected abstract boolean unlock(String source);

    protected abstract boolean tryLock(String source, long timeout, TimeUnit unit);

    @Override
    public boolean consume(String source) {
        try {
            if (lock(source)) {
                return doConsume(source);
            }
            return false;
        } finally {
            unlock(source);
        }
    }

    @Override
    public boolean tryConsume(String source) {
        try {
            if (tryLock(source)) {
                return doConsume(source);
            }
            return false;
        } finally {
            unlock(source);
        }

    }

    protected abstract boolean doConsume(String source);

    @Override
    public boolean tryConsume(String source, long time, TimeUnit timeUnit) {
        return false;
    }
}
