package com.limiter.config;

import com.alibaba.fastjson.JSON;
import com.limiter.config.domain.Config;

/**
 * @author wuhao
 */
public class LocaFileRuleDaoImpl implements RuleDao {

    private volatile boolean isLoad = false;

    private Config config;

    @Override
    public Config selectConfig() {
        if (!isLoad) {
            synchronized (this) {
                config = load();
                isLoad = true;
            }
        }
        return config;
    }

    private Config load() {
        String content = "";
        config = JSON.parseObject(content, Config.class);
        return config;
    }
}
