package tokenbucket.manage.impl;

import tokenbucket.domain.TokenBucket;
import tokenbucket.manage.TokenFilledStrategy;

/**
 * @author wuhao
 */
public class DefaultTokenAddHandle implements TokenFilledStrategy {
    @Override
    public TokenBucket filled(TokenBucket tokenBucket) {

        long now = System.nanoTime();
        long lastRefillTimePoint = tokenBucket.getLastRefillTimePoint();
        long nextRefillTimePoint = lastRefillTimePoint + tokenBucket.getAddPeriod();
        if (now < nextRefillTimePoint) {
            return tokenBucket;
        }
        int numPeriods = (int) Math.max(0, (now - lastRefillTimePoint) / tokenBucket.getAddTimeForNano());

        lastRefillTimePoint += numPeriods * tokenBucket.getAddTimeForNano();
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
