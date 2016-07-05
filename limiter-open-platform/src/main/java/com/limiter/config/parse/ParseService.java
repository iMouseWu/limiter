package com.limiter.config.parse;

import com.limiter.config.domain.Config;

/**
 * @author wuhao
 */
public interface ParseService {

    Config parse();

    Config parse(String path);
}
