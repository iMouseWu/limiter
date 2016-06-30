package tokenbucket.service.impl;

import tokenbucket.config.ConfigCenter;
import tokenbucket.config.TokenBucketConfig;
import tokenbucket.domain.DefaultTokenBucket;
import tokenbucket.domain.TokenBucket;
import tokenbucket.manage.TokenBucketManager;
import tokenbucket.manage.TokenFilledStrategy;
import tokenbucket.service.TokenBucketService;

/**
 * @author wuhao
 */
public class TokenBucketServiceImpl extends TokenBucketAbstractService implements TokenBucketService {

	private TokenFilledStrategy tokenFilledStrategy;

	private TokenBucketManager tokenBucketManager;

	private ConfigCenter configCenter;

	public void setConfigCenter(ConfigCenter configCenter) {
		this.configCenter = configCenter;
	}

	public void setTokenFilledStrategy(TokenFilledStrategy tokenFilledStrategy) {
		this.tokenFilledStrategy = tokenFilledStrategy;
	}

	public void setTokenBucketManager(TokenBucketManager tokenBucketManager) {
		this.tokenBucketManager = tokenBucketManager;
	}

	@Override
	public boolean lock(String source) {
		return tokenBucketManager.lock(source);
	}

	@Override
	public boolean unlock(String source) {
		return tokenBucketManager.unlock(source);
	}

	@Override
	protected boolean doConsume(String tokenBucketKey) {
		TokenBucket tokenBucket = tokenBucketManager.getTokenBucket(tokenBucketKey);
		if (null == tokenBucket) {
			tokenBucket = createDefaultTokenBucket(tokenBucketKey);
			if (null != configCenter) {
				TokenBucketConfig tokenBucketConfig = configCenter.getConfig(tokenBucketKey);
				if (null != tokenBucketConfig) {
					tokenBucket = createTokenBucket(tokenBucketConfig);
				}
			}
		}
		tokenBucket = tokenFilledStrategy.filled(tokenBucket);
		tokenBucketManager.saveTokenBucket(tokenBucket);
		int tokenNum = tokenBucket.getTokenNum();
		if (tokenNum >= 1) {
			tokenBucket.reduceToken(1);
			return true;
		} else {
			return false;
		}
	}

	private DefaultTokenBucket createDefaultTokenBucket(String tokenBucketKey) {
		DefaultTokenBucket defaultTokenBucket = new DefaultTokenBucket();
		defaultTokenBucket.setCapacity(120);
		defaultTokenBucket.setLastRefillTimePoint(System.currentTimeMillis());
		defaultTokenBucket.setTokenNum(120);
		defaultTokenBucket.setAddNum(1);
		defaultTokenBucket.setAddPeriod(1000);
		defaultTokenBucket.setAddTimeWithMillisecond(1000);
		defaultTokenBucket.setTokenBucketKey(tokenBucketKey);
		return defaultTokenBucket;
	}

	private DefaultTokenBucket createTokenBucket(TokenBucketConfig tokenBucketConfig) {
		DefaultTokenBucket defaultTokenBucket = new DefaultTokenBucket();
		defaultTokenBucket.setCapacity(tokenBucketConfig.getCapacity());
		defaultTokenBucket.setLastRefillTimePoint(System.currentTimeMillis());
		defaultTokenBucket.setTokenNum(tokenBucketConfig.getCapacity());
		defaultTokenBucket.setAddNum(tokenBucketConfig.getAddNum());
		defaultTokenBucket.setAddPeriod(tokenBucketConfig.getAddPeriod());
		defaultTokenBucket.setAddTimeWithMillisecond(tokenBucketConfig.getAddTimeWithMillisecond());
		defaultTokenBucket.setTokenBucketKey(tokenBucketConfig.getTokenBucketKey());
		return defaultTokenBucket;
	}

}
