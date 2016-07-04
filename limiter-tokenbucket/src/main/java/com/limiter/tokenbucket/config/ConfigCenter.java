package com.limiter.tokenbucket.config;

/**
 * @author wuhao
 */
public interface ConfigCenter {

    TokenBucketConfig getConfig(String tokenBucketKey);

    void register(TokenBucketConfig tokenBucketConfig);

    void clear();

}
