package com.limiter.tokenbucket.domain;

import java.io.Serializable;

/**
 * @author wuhao
 */
public class DefaultTokenBucket implements TokenBucket, Serializable {

    private static final long serialVersionUID = 7997820220362125951L;

    private int capacity;

    private int tokenNum;

    private long lastRefillTimePoint;

    private String tokenBucketKey;

    private long addTimeWithMillisecond;

    private int addNum;

    private long addPeriod;

    @Override
    public String getTokenBucketKey() {
        return tokenBucketKey;
    }

    public void setTokenBucketKey(String tokenBucketKey) {
        this.tokenBucketKey = tokenBucketKey;
    }

    @Override
    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public int getTokenNum() {
        return tokenNum;
    }

    public void setTokenNum(int tokenNum) {
        this.tokenNum = tokenNum;
    }

    @Override
    public long getLastRefillTimePoint() {
        return lastRefillTimePoint;
    }

    public void setLastRefillTimePoint(long lastRefillTimePoint) {
        this.lastRefillTimePoint = lastRefillTimePoint;
    }

    @Override
    public long getAddTimeWithMillisecond() {
        return addTimeWithMillisecond;
    }

    public void setAddTimeWithMillisecond(long addTimeWithMillisecond) {
        this.addTimeWithMillisecond = addTimeWithMillisecond;
    }

    @Override
    public int getAddNum() {
        return addNum;
    }

    public void setAddNum(int addNum) {
        this.addNum = addNum;
    }

    @Override
    public long getAddPeriod() {
        return addPeriod;
    }

    public void setAddPeriod(long addPeriod) {
        this.addPeriod = addPeriod;
    }

    @Override
    public void filledToken(int num) {
        tokenNum += num;
    }

    @Override
    public void reduceToken(int num) {
        tokenNum -= num;
    }
}
