package com.limiter.open.config;

import com.limiter.open.common.utils.TokenBucketKeyUtils;
import com.limiter.open.config.domain.Config;
import com.limiter.open.config.domain.DetailConfig;
import com.limiter.open.tokenbucket.domain.TokenBucketConfig;
import com.limiter.open.tokenbucket.config.ConfigCenter;

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
