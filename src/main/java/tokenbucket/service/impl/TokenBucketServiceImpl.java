package tokenbucket.service.impl;

import tokenbucket.domain.DefaultTokenBucket;
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

    public void setTokenFilledStrategy(TokenFilledStrategy tokenFilledStrategy) {
        this.tokenFilledStrategy = tokenFilledStrategy;
    }

    public void setTokenBucketManager(TokenBucketManager tokenBucketManager) {
        this.tokenBucketManager = tokenBucketManager;
    }

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
        if (null == tokenBucket) {
            DefaultTokenBucket defaultTokenBucket = new DefaultTokenBucket();
            defaultTokenBucket.setCapacity(120);
            defaultTokenBucket.setLastRefillTimePoint(System.currentTimeMillis());
            defaultTokenBucket.setTokenNum(0);
            defaultTokenBucket.setAddNum(1);
            defaultTokenBucket.setAddPeriod(0);
            defaultTokenBucket.setAddTimeWithMillisecond(6 * 1000);
            defaultTokenBucket.setTokenBucketKey(source);
            tokenBucketManager.saveTokenBucket(defaultTokenBucket);
            tokenBucket = defaultTokenBucket;
        }
        tokenBucket = tokenFilledStrategy.filled(tokenBucket);
        int tokenNum = tokenBucket.getTokenNum();
        if (tokenNum >= 1) {
            tokenBucket.reduceToken(1);
            return true;
        } else {
            return false;
        }
    }


}
