package com.limiter.tokenbucket.service.impl;

import com.limiter.tokenbucket.config.ConfigCenter;
import com.limiter.tokenbucket.config.TokenBucketConfig;
import com.limiter.tokenbucket.domain.DefaultTokenBucket;
import com.limiter.tokenbucket.domain.TokenBucket;
import com.limiter.tokenbucket.manage.TokenBucketManager;
import com.limiter.tokenbucket.manage.TokenFilledStrategy;
import com.limiter.tokenbucket.service.ConfigCallBack;
import com.limiter.tokenbucket.service.TokenBucketService;

/**
 * @author wuhao
 */
public class TokenBucketServiceImpl extends TokenBucketAbstractService implements TokenBucketService {

    private TokenFilledStrategy tokenFilledStrategy;

    private TokenBucketManager tokenBucketManager;

    private ConfigCenter configCenter;

    private ConfigCallBack configCallBack;

    public void setConfigCenter(ConfigCenter configCenter) {
        this.configCenter = configCenter;
    }

    public void setTokenFilledStrategy(TokenFilledStrategy tokenFilledStrategy) {
        this.tokenFilledStrategy = tokenFilledStrategy;
    }

    public void setTokenBucketManager(TokenBucketManager tokenBucketManager) {
        this.tokenBucketManager = tokenBucketManager;
    }

    public void setConfigCallBack(ConfigCallBack configCallBack) {
        this.configCallBack = configCallBack;
    }

    @Override
    public boolean lock(String source) {
        return tokenBucketManager.lock(source);
    }

    @Override
    public boolean unlock(String source) {
        return tokenBucketManager.unlock(source);
    }

    @Override
    protected boolean doConsume(String tokenBucketKey) {
        TokenBucket tokenBucket = tokenBucketManager.getTokenBucket(tokenBucketKey);
        if (null == tokenBucket) {
            if (null != configCenter) {
                TokenBucketConfig tokenBucketConfig = configCenter.getConfig(tokenBucketKey);
                if (null == tokenBucketConfig && null != configCallBack) {
                    configCallBack.callBack(tokenBucketKey);
                    tokenBucketConfig = configCenter.getConfig(tokenBucketKey);
                }
                if (null == tokenBucketConfig) {
                    return true;
                }
                tokenBucket = createTokenBucket(tokenBucketConfig);
            }
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

    private DefaultTokenBucket createTokenBucket(TokenBucketConfig tokenBucketConfig) {
        DefaultTokenBucket defaultTokenBucket = new DefaultTokenBucket();
        defaultTokenBucket.setCapacity(tokenBucketConfig.getCapacity());
        defaultTokenBucket.setLastRefillTimePoint(System.currentTimeMillis());
        defaultTokenBucket.setTokenNum(tokenBucketConfig.getCapacity());
        defaultTokenBucket.setAddNum(tokenBucketConfig.getAddNum());
        defaultTokenBucket.setAddPeriod(tokenBucketConfig.getAddPeriod());
        defaultTokenBucket.setAddTimeWithMillisecond(tokenBucketConfig.getAddTimeWithMillisecond());
        defaultTokenBucket.setTokenBucketKey(tokenBucketConfig.getTokenBucketKey());
        return defaultTokenBucket;
    }

}
