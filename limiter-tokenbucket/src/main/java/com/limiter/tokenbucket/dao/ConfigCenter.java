package com.limiter.tokenbucket.dao;

import com.limiter.tokenbucket.domain.TokenBucketConfig;

/**
 * @author wuhao
 */
public interface ConfigCenter {

    TokenBucketConfig getConfig(String tokenBucketKey);

    boolean register(TokenBucketConfig tokenBucketConfig);

    boolean clear();

}
