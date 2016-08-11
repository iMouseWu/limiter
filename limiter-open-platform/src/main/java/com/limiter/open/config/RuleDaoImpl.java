package com.limiter.open.config;

import com.limiter.open.config.domain.Config;
import com.limiter.open.config.parse.ParseService;

/**
 * @author wuhao
 */
public class RuleDaoImpl implements RuleDao {

    private ParseService parseService;

    private volatile boolean isLoad = false;

    private Config config;

    public void setParseService(ParseService parseService) {
        this.parseService = parseService;
    }

    @Override
    public Config selectConfig() {
        if (!isLoad) {
            synchronized (this) {
                config = parseService.parse();
                isLoad = true;
            }
        }
        return config;
    }

}
