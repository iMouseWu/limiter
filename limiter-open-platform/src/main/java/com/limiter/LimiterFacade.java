package com.limiter;

import com.limiter.config.ConfigLoad;
import com.limiter.config.ConfigLoadImpl;
import com.limiter.config.RuleDao;
import com.limiter.config.RuleDaoImpl;
import com.limiter.config.parse.LocalFileParseServiceImpl;
import com.limiter.config.parse.ParseService;
import com.limiter.lock.LockService;
import com.limiter.lock.impl.LocalLockServiceImpl;
import com.limiter.tokenbucket.dao.ConfigCenter;
import com.limiter.tokenbucket.dao.TokenBucketDAO;
import com.limiter.tokenbucket.dao.impl.LocalConfigCenterImpl;
import com.limiter.tokenbucket.dao.impl.LocalTokenBucketDAOImpl;
import com.limiter.tokenbucket.manage.TokenBucketManager;
import com.limiter.tokenbucket.manage.TokenFilledStrategy;
import com.limiter.tokenbucket.manage.impl.DefaultTokenFilledStrategy;
import com.limiter.tokenbucket.service.ConfigCallBack;
import com.limiter.tokenbucket.service.TokenBucketService;
import com.limiter.tokenbucket.service.impl.TokenBucketServiceImpl;
import com.limiter.tokenbucket.time.impl.TimeToolsImpl;

import java.util.concurrent.TimeUnit;

/**
 * @author wuhao
 */
public class LimiterFacade implements TokenBucketService {

    private ParseService parseService;

    private RuleDao ruleDao;

    private ConfigCenter configCenter;

    private ConfigLoad configLoad;

    private LockService lockService;

    private TokenBucketDAO tokenBucketDAO;

    private TokenFilledStrategy tokenFilledStrategy;

    private ConfigCallBack configCallBack;

    private TokenBucketService tokenBucketService;

    private volatile boolean isLoad;

    public void setParseService(ParseService parseService) {
        this.parseService = parseService;
    }

    public void setRuleDao(RuleDao ruleDao) {
        this.ruleDao = ruleDao;
    }

    public void setConfigCenter(ConfigCenter configCenter) {
        this.configCenter = configCenter;
    }

    public void setConfigLoad(ConfigLoad configLoad) {
        this.configLoad = configLoad;
    }

    public void setLockService(LockService lockService) {
        this.lockService = lockService;
    }

    public void setTokenBucketDAO(TokenBucketDAO tokenBucketDAO) {
        this.tokenBucketDAO = tokenBucketDAO;
    }

    public void setTokenFilledStrategy(TokenFilledStrategy tokenFilledStrategy) {
        this.tokenFilledStrategy = tokenFilledStrategy;
    }

    public void setConfigCallBack(ConfigCallBack configCallBack) {
        this.configCallBack = configCallBack;
    }

    @Override
    public boolean consume(String tokenBucketKey) {
        init();
        return tokenBucketService.consume(tokenBucketKey);
    }

    @Override
    public boolean tryConsume(String tokenBucketKey) {
        init();
        return tokenBucketService.tryConsume(tokenBucketKey);
    }

    @Override
    public boolean tryConsume(String tokenBucketKey, long time, TimeUnit timeUnit) {
        init();
        return tokenBucketService.tryConsume(tokenBucketKey, time, timeUnit);
    }

    private void init() {
        if (isLoad) {
            return;
        }
        synchronized (this) {
            if (isLoad) {
                return;
            }
            load();
        }
    }

    private void load() {
        if (null == parseService) {
            parseService = new LocalFileParseServiceImpl();
        }

        if (null == configCenter) {
            configCenter = new LocalConfigCenterImpl();
        }

        if (null == lockService) {
            lockService = new LocalLockServiceImpl();
        }

        if (null == tokenBucketDAO) {
            tokenBucketDAO = new LocalTokenBucketDAOImpl();
        }

        if (null == ruleDao) {
            ruleDao = new RuleDaoImpl();
            ((RuleDaoImpl) ruleDao).setParseService(parseService);
        }

        if (null == tokenFilledStrategy) {
            tokenFilledStrategy = new DefaultTokenFilledStrategy();
            ((DefaultTokenFilledStrategy) tokenFilledStrategy).setTimeTools(new TimeToolsImpl());
        }

        if (null == configLoad) {
            configLoad = new ConfigLoadImpl();
            ((ConfigLoadImpl) configLoad).setConfigCenter(configCenter);
            ((ConfigLoadImpl) configLoad).setRuleDao(ruleDao);
        }

        tokenBucketService = new TokenBucketServiceImpl();
        TokenBucketManager tokenBucketManager = new TokenBucketManager();
        tokenBucketManager.setLockService(lockService);
        tokenBucketManager.setTokenBucketDAO(tokenBucketDAO);
        ((TokenBucketServiceImpl) tokenBucketService).setTokenBucketManager(tokenBucketManager);
        ((TokenBucketServiceImpl) tokenBucketService).setTokenFilledStrategy(tokenFilledStrategy);
        ((TokenBucketServiceImpl) tokenBucketService).setConfigCenter(configCenter);
        configLoad.load();
    }
}
