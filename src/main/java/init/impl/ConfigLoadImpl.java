package init.impl;

import common.utils.TokenBucketKeyUtils;
import init.AppkeyDao;
import init.Config;
import init.ConfigLoad;
import init.RuleDao;
import tokenbucket.config.ConfigCenter;
import tokenbucket.config.TokenBucketConfig;
import validate.ErrorInfo;
import validate.error.ErrorInfoFactory;

import java.util.List;

/**
 * @author wuhao
 */
public class ConfigLoadImpl implements ConfigLoad {

    private RuleDao ruleDao;

    private ConfigCenter configCenter;

    private ErrorInfoFactory errorInfoFactory;

    private AppkeyDao appkeyDao;

    public void setConfigCenter(ConfigCenter configCenter) {
        this.configCenter = configCenter;
    }

    public void setErrorInfoFactory(ErrorInfoFactory errorInfoFactory) {
        this.errorInfoFactory = errorInfoFactory;
    }

    public void setAppkeyDao(AppkeyDao appkeyDao) {
        this.appkeyDao = appkeyDao;
    }

    public void setRuleDao(RuleDao ruleDao) {
        this.ruleDao = ruleDao;
    }

    @Override
    public void load() {
        List<Config> configs = ruleDao.load();
        List<String> appkeys = appkeyDao.getAllAppkey();
        for (Config config : configs) {
            for (String appkey : appkeys) {
                configCenter.registerConfig(getTokenBucket(config, appkey));
                errorInfoFactory.register(getErrorInfo(config, appkey));
            }
        }
    }

    private TokenBucketConfig getTokenBucket(Config config, String appkey) {
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

    private ErrorInfo getErrorInfo(Config config, String appkey) {
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
