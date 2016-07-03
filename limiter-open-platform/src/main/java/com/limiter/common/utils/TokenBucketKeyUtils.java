package com.limiter.common.utils;

/**
 * @author wuhao
 */
public final class TokenBucketKeyUtils {

	private static final String SPILIT = "_";

	private TokenBucketKeyUtils() {

	}

	public static String generateTokenBucketKey(String appkey, String method) {
		return appkey + SPILIT + method;
	}

	public static String getAppkey(String tokenBucketKey) {
		return spilitTokenBucketKey(tokenBucketKey)[0];
	}

	public static String getMethod(String tokenBucketKey) {
		return spilitTokenBucketKey(tokenBucketKey)[1];
	}

	private static String[] spilitTokenBucketKey(String tokenBucketKey) {
		return tokenBucketKey.split(SPILIT);
	}

}
