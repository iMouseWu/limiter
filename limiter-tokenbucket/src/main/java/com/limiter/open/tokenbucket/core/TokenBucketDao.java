package com.limiter.open.tokenbucket.core;

import com.limiter.open.tokenbucket.domain.TokenBucket;

/**
 * @author wuhao
 */
public interface TokenBucketDao {

    TokenBucket getTokenBucket(String tokenBucketKey);

    boolean saveTokenBucket(TokenBucket tokenBucket);

}
