package tokenbucket.service.impl;

import tokenbucket.config.ConfigCenter;
import tokenbucket.config.TokenBucketConfig;
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

    private ConfigCenter configCenter;

    public void setConfigCenter(ConfigCenter configCenter) {
        this.configCenter = configCenter;
    }

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
    protected boolean doConsume(String tokenBucketKey) {
        TokenBucket tokenBucket = tokenBucketManager.getTokenBucket(tokenBucketKey);
        if (null == tokenBucket) {
            //FIXME IF tokenBucketConfig IS NULL
            TokenBucketConfig tokenBucketConfig = configCenter.getConfig(tokenBucketKey);
            DefaultTokenBucket defaultTokenBucket = new DefaultTokenBucket();
            defaultTokenBucket.setCapacity(tokenBucketConfig.getCapacity());
            defaultTokenBucket.setLastRefillTimePoint(System.currentTimeMillis());
            defaultTokenBucket.setTokenNum(tokenBucketConfig.getCapacity());
            defaultTokenBucket.setAddNum(tokenBucketConfig.getAddNum());
            defaultTokenBucket.setAddPeriod(tokenBucketConfig.getAddPeriod());
            defaultTokenBucket.setAddTimeWithMillisecond(tokenBucketConfig.getAddTimeWithMillisecond());
            defaultTokenBucket.setTokenBucketKey(tokenBucketKey);
            tokenBucketManager.saveTokenBucket(defaultTokenBucket);
            tokenBucket = defaultTokenBucket;
        }
        tokenBucket = tokenFilledStrategy.filled(tokenBucket);
        tokenBucketManager.saveTokenBucket(tokenBucket);
        int tokenNum = tokenBucket.getTokenNum();
        if (tokenNum >= 1) {
            tokenBucket.reduceToken(1);
            return true;
        } else {
            return false;
        }
    }


}
