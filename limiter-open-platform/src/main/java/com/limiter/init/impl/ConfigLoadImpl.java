package com.limiter.init.impl;

import java.util.List;

import com.limiter.common.utils.TokenBucketKeyUtils;
import com.limiter.init.AppkeyDao;
import com.limiter.init.Config;
import com.limiter.init.ConfigLoad;
import com.limiter.init.RuleDao;
import com.limiter.tokenbucket.config.ConfigCenter;
import com.limiter.tokenbucket.config.TokenBucketConfig;
import com.limiter.validate.ErrorInfo;
import com.limiter.validate.error.ErrorInfoFactory;

/**
 * @author wuhao
 */
public class ConfigLoadImpl implements ConfigLoad {

	private RuleDao ruleDao;

	private ConfigCenter configCenter;

	private ErrorInfoFactory errorInfoFactory;

	public void setConfigCenter(ConfigCenter configCenter) {
		this.configCenter = configCenter;
	}

	public void setErrorInfoFactory(ErrorInfoFactory errorInfoFactory) {
		this.errorInfoFactory = errorInfoFactory;
	}

	public void setRuleDao(RuleDao ruleDao) {
		this.ruleDao = ruleDao;
	}

	@Override
	public void load() {
		List<Config> configs = ruleDao.load();
		for (Config config : configs) {
			// 1. 先查询全局的限制
			// 2. 再查询对方法的全局限制
			// 3. 查询对AppKey的全局限制,如果AppKey(对某个商家的商家的限制)
			// 2. 再次查询对这个Appkey和这个方法的限制
			configCenter.registerConfig(getTokenBucket(config, appkey));
			errorInfoFactory.register(getErrorInfo(config, appkey));
		}
	}

	private TokenBucketConfig getTokenBucket(final Config config, final String appkey) {
		return new TokenBucketConfig() {

			@Override
			public String getTokenBucketKey() {
				return TokenBucketKeyUtils.generateTokenBucketKey(appkey, config.getMethod());
			}

			@Override
			public int getCapacity() {
				return config.getCapacity();
			}

			@Override
			public int getAddNum() {
				return config.getAddNum();
			}

			@Override
			public long getAddTimeWithMillisecond() {
				return config.getAddTimeWithMillisecond();
			}

			@Override
			public int getAddPeriod() {
				return config.getAddPeriod();
			}
		};
	}

	private ErrorInfo getErrorInfo(final Config config, final String appkey) {
		return new ErrorInfo() {

			@Override
			public String getErrorCode() {
				return config.getErrorCode();
			}

			@Override
			public String getErrorMessage() {
				return config.getErrorMessage();
			}

			@Override
			public String getMethod() {
				return config.getMethod();
			}

			@Override
			public String getAppkey() {
				return appkey;
			}
		};

	}
}
