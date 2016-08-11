package com.limiter.open.tokenbucket.core.impl;

import com.limiter.open.tokenbucket.core.TimeTools;

/**
 * @author wuhao
 */
public class TimeToolsImpl implements TimeTools {
    @Override
    public long getCurrentTimeMillis() {
        return System.currentTimeMillis();
    }
}
