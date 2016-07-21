package com.limiter.tokenbucket.dao.impl;

import com.limiter.tokenbucket.dao.ConfigCenter;
import com.limiter.tokenbucket.domain.TokenBucketConfig;

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
