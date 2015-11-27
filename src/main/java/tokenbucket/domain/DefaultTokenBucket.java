package tokenbucket.domain;

import java.io.Serializable;

/**
 * @author wuhao
 */
public class DefaultTokenBucket implements TokenBucket, Serializable {

    private static final long serialVersionUID = 7997820220362125951L;

    private int capacity;

    private int tokenNum;

    private long lastRefillTimePoint;

    private int tokenCountPerSecond;

    @Override
    public int getCapacity() {
        return capacity;
    }

    @Override
    public int getTokenNum() {
        return tokenNum;
    }

    @Override
    public long getLastRefillTimePoint() {
        return lastRefillTimePoint;
    }

    @Override
    public long getAddTimeForNano() {
        return 0;
    }

    @Override
    public int getAddNum() {
        return 0;
    }

    @Override
    public long getAddPeriod() {
        return 0;
    }

    @Override
    public int getTokenCountPerSecond() {
        return tokenCountPerSecond;
    }

    @Override
    public void filledToken(int num) {
    }

    @Override
    public boolean reduceToken(int num) {
        return false;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void setTokenNum(int tokenNum) {
        this.tokenNum = tokenNum;
    }

    public void setLastRefillTimePoint(long lastRefillTimePoint) {
        this.lastRefillTimePoint = lastRefillTimePoint;
    }

    public void setTokenCountPerSecond(int tokenCountPerSecond) {
        this.tokenCountPerSecond = tokenCountPerSecond;
    }
}
