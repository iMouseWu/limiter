package com.limiter.open.tokenbucket.config;

import com.limiter.open.tokenbucket.domain.TokenBucketConfig;

/**
 * @author wuhao
 */
public interface ConfigCenter {

    TokenBucketConfig getConfig(String tokenBucketKey);

    boolean register(TokenBucketConfig tokenBucketConfig);

    boolean clear();

}
