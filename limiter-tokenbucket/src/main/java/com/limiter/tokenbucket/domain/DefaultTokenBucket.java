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
    public long getAddTimeWithMillisecond() {
        return addTimeWithMillisecond;
    }

    @Override
    public int getAddNum() {
        return addNum;
    }

    @Override
    public long getAddPeriod() {
        return addPeriod;
    }

    @Override
    public void filledToken(int num) {
        tokenNum += num;
    }

    @Override
    public void reduceToken(int num) {
        tokenNum -= num;
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

    public void setTokenBucketKey(String tokenBucketKey) {
        this.tokenBucketKey = tokenBucketKey;
    }

    public void setAddTimeWithMillisecond(long addTimeWithMillisecond) {
        this.addTimeWithMillisecond = addTimeWithMillisecond;
    }

    public void setAddNum(int addNum) {
        this.addNum = addNum;
    }

    public void setAddPeriod(long addPeriod) {
        this.addPeriod = addPeriod;
    }
}
