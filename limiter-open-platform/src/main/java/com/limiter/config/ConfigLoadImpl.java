package com.limiter.config;

import com.limiter.common.utils.TokenBucketKeyUtils;
import com.limiter.config.domain.Config;
import com.limiter.config.domain.DetailConfig;
import com.limiter.tokenbucket.config.ConfigCenter;
import com.limiter.tokenbucket.config.TokenBucketConfig;

import java.util.List;

/**
 * @author wuhao
 */
public class ConfigLoadImpl implements ConfigLoad {

    private RuleDao ruleDao;

    private ConfigCenter configCenter;

    public void setConfigCenter(ConfigCenter configCenter) {
        this.configCenter = configCenter;
    }

    public void setRuleDao(RuleDao ruleDao) {
        this.ruleDao = ruleDao;
    }

    @Override
    public void load() {
        Config config = ruleDao.selectConfig();

        List<DetailConfig> detailConfigs = config.getDetailConfigs();
        for (DetailConfig detailConfig : detailConfigs) {
            configCenter.register(getTokenBucket(detailConfig));
        }

    }

    private TokenBucketConfig getTokenBucket(final DetailConfig config) {
        return new TokenBucketConfig() {

            @Override
            public String getTokenBucketKey() {
                return TokenBucketKeyUtils.generateTokenBucketKey(config.getAppkey(), config.getMethod());
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
            public long getAddPeriod() {
                return config.getAddPeriod();
            }
        };
    }
}
