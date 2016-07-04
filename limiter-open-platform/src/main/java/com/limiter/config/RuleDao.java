package com.limiter.config;

import com.limiter.config.domain.Config;

/**
 * @author wuhao
 */
public interface RuleDao {

    Config selectConfig();

}
