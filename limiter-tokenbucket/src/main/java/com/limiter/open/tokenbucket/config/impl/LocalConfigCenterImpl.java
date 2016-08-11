package com.limiter.open.tokenbucket.config.impl;

import com.limiter.open.tokenbucket.domain.TokenBucketConfig;
import com.limiter.open.tokenbucket.config.ConfigCenter;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wuhao
 */
public class LocalConfigCenterImpl implements ConfigCenter {

    private static Map<String, TokenBucketConfig> configMap = new HashMap<>();

    @Override
    public TokenBucketConfig getConfig(String tokenBucketKey) {
        return configMap.get(tokenBucketKey);
    }

    @Override
    public boolean register(TokenBucketConfig tokenBucketConfig) {
        configMap.put(tokenBucketConfig.getTokenBucketKey(), tokenBucketConfig);
        return true;
    }

    @Override
    public boolean clear() {
        configMap.clear();
        return true;
    }

}
