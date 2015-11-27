package tokenbucket.service.impl;

import tokenbucket.domain.TokenBucket;
import tokenbucket.manage.TokenBucketManager;
import tokenbucket.manage.TokenFilledStrategy;
import tokenbucket.service.TokenBucketService;

import java.util.concurrent.TimeUnit;

/**
 * @author wuhao
 */
public class TokenBucketServiceImpl extends TokenBucketAbstractService implements TokenBucketService {

    private TokenFilledStrategy tokenFilledStrategy;

    private TokenBucketManager tokenBucketManager;

    @Override
    public boolean lock(String source) {
        return tokenBucketManager.lock(source);
    }

    @Override
    protected boolean tryLock(String source) {
        return tokenBucketManager.tryLock(source);
    }

    @Override
    public boolean unlock(String source) {
        return tokenBucketManager.unlock(source);
    }

    @Override
    protected boolean tryLock(String source, long timeout, TimeUnit unit) {
        return tokenBucketManager.tryLock(source, timeout, unit);
    }

    @Override
    protected boolean doConsume(String source) {
        TokenBucket tokenBucket = tokenBucketManager.getTokenBucket(source);
        tokenBucket = tokenFilledStrategy.filled(tokenBucket);
        int tokenNum = tokenBucket.getTokenNum();
        if (tokenNum >= 1) {
            return tokenBucket.reduceToken(1);
        } else {
            return false;
        }
    }


}
