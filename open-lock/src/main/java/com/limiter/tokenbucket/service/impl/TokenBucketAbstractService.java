package com.limiter.tokenbucket.service.impl;

import java.util.concurrent.TimeUnit;

import com.limiter.tokenbucket.service.TokenBucketService;

/**
 * @author wuhao
 */
public abstract class TokenBucketAbstractService implements TokenBucketService {

	protected abstract boolean lock(String tokenBucketKey);

	protected abstract boolean unlock(String tokenBucketKey);

	private static final long SPINFORCONSUMETIME = 1000L;

	@Override
	public boolean consume(String tokenBucketKey) {
		if (lock(tokenBucketKey)) {
			try {
				while (!doConsume(tokenBucketKey)) {
					try {
						TimeUnit.MILLISECONDS.sleep(SPINFORCONSUMETIME);
					} catch (InterruptedException e) {
						return false;
					}
				}
				return true;
			} finally {
				unlock(tokenBucketKey);
			}
		}
		return false;
	}

	@Override
	public boolean tryConsume(String tokenBucketKey) {
		if (lock(tokenBucketKey)) {
			try {
				return doConsume(tokenBucketKey);
			} finally {
				unlock(tokenBucketKey);
			}
		}
		return false;
	}

	protected abstract boolean doConsume(String tokenBucketKey);

	@Override
	public boolean tryConsume(String tokenBucketKey, long timeout, TimeUnit timeUnit) {
		boolean result = doConsume(tokenBucketKey);
		if (!result) {
			try {
				TimeUnit.MILLISECONDS.sleep(SPINFORCONSUMETIME);
			} catch (InterruptedException e) {
				return false;
			}
		}
		return doConsume(tokenBucketKey);
	}
}
