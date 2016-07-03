package com.limiter.service.impl;

import com.limiter.common.utils.TokenBucketKeyUtils;
import com.limiter.tokenbucket.service.ConfigCallBack;

public class ConfigCallBackImpl implements ConfigCallBack {

	@Override
	public void callBack(String tokenBucketKey) {
		String appkey = TokenBucketKeyUtils.getAppkey(tokenBucketKey);
		String method = TokenBucketKeyUtils.getMethod(tokenBucketKey);

	}

}
