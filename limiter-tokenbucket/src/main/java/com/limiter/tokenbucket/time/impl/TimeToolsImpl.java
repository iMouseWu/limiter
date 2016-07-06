package com.limiter.tokenbucket.time.impl;

import com.limiter.tokenbucket.time.TimeTools;

/**
 * @author wuhao
 */
public class TimeToolsImpl implements TimeTools {
    @Override
    public long getCurrentTimeMillis() {
        return System.currentTimeMillis();
    }
}
