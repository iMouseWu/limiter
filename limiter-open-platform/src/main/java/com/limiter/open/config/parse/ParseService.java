package com.limiter.open.config.parse;

import com.limiter.open.config.domain.Config;

/**
 * @author wuhao
 */
public interface ParseService {

    Config parse();

    Config parse(String path);
}
