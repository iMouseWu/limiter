package com.limiter.service.impl;

import com.limiter.common.utils.TokenBucketKeyUtils;
import com.limiter.service.OpenPlatformService;
import com.limiter.tokenbucket.service.TokenBucketService;

public class OpenPlatformServiceImpl implements OpenPlatformService {

	private TokenBucketService tokenBucketService;

	public void setTokenBucketService(TokenBucketService tokenBucketService) {
		this.tokenBucketService = tokenBucketService;
	}

	@Override
	public boolean visit(String appkey, String method) {
		return tokenBucketService.consume(TokenBucketKeyUtils.generateTokenBucketKey(appkey, method));
	}

}
