package com.limiter.tokenbucket.dao;

import com.limiter.tokenbucket.domain.TokenBucket;

/**
 * @author wuhao
 */
public interface TokenBucketDAO {

    TokenBucket getTokenBucket(String tokenBucketKey);

    boolean saveTokenBucket(TokenBucket tokenBucket);

}
