package com.limiter.open.service.impl;

import com.limiter.open.common.utils.TokenBucketKeyUtils;
import com.limiter.open.service.OpenPlatformService;
import com.limiter.open.tokenbucket.core.TokenBucketService;

public class OpenPlatformServiceImpl implements OpenPlatformService {

    private TokenBucketService tokenBucketService;

    public void setTokenBucketService(TokenBucketService tokenBucketService) {
        this.tokenBucketService = tokenBucketService;
    }

    @Override
    public boolean visit(String appkey, String method) {
        return tokenBucketService.tryConsume(TokenBucketKeyUtils.generateTokenBucketKey(appkey, method));
    }

}
