package com.limiter.tokenbucket.manage.impl;

import com.limiter.tokenbucket.domain.TokenBucket;
import com.limiter.tokenbucket.manage.TokenFilledStrategy;
import com.limiter.tokenbucket.time.TimeTools;

/**
 * @author wuhao
 */
public class DefaultTokenFilledStrategy implements TokenFilledStrategy {

    private TimeTools timeTools;

    public void setTimeTools(TimeTools timeTools) {
        this.timeTools = timeTools;
    }

    @Override
    public TokenBucket filled(TokenBucket tokenBucket) {
        long now = timeTools.getCurrentTimeMillis();
        long lastRefillTimePoint = tokenBucket.getLastRefillTimePoint();

        long addPeriod = tokenBucket.getAddPeriod();
        long addTimeWithMillisecond = tokenBucket.getAddTimeWithMillisecond();
        if (addPeriod == 0 || addPeriod < addTimeWithMillisecond) {
            addPeriod = addTimeWithMillisecond;
        }
        long nextRefillTimePoint = lastRefillTimePoint + addPeriod;
        if (now < nextRefillTimePoint) {
            return tokenBucket;
        }
        int numPeriods = (int) Math.max(0, (now - lastRefillTimePoint) / addTimeWithMillisecond);

        lastRefillTimePoint += numPeriods * addTimeWithMillisecond;
        tokenBucket.setLastRefillTimePoint(lastRefillTimePoint);
        int addNum = numPeriods * tokenBucket.getAddNum();
        filledToken(tokenBucket, addNum);
        return tokenBucket;
    }

    private void filledToken(TokenBucket tokenBucket, int addNum) {
        int capacity = tokenBucket.getCapacity();
        int newTokens = Math.min(capacity, Math.max(0, addNum));
        int maxAddToken = capacity - tokenBucket.getTokenNum();
        tokenBucket.filledToken(Math.max(0, Math.min(maxAddToken, newTokens)));
    }

}
