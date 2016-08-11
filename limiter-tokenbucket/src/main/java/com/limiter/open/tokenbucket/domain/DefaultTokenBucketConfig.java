package com.limiter.open.tokenbucket.domain;

import java.io.Serializable;

/**
 * @author wuhao
 */
public class DefaultTokenBucketConfig implements TokenBucketConfig, Serializable {

    private static final long serialVersionUID = 776916671290616324L;

    private int capacity;

    private String tokenBucketKey;

    private int addNum;

    private long addTimeWithMillisecond;

    private long addPeriod;

    @Override
    public String getTokenBucketKey() {
        return null;
    }

    @Override
    public int getCapacity() {
        return 0;
    }

    @Override
    public int getAddNum() {
        return 0;
    }

    @Override
    public long getAddTimeWithMillisecond() {
        return 0;
    }

    @Override
    public long getAddPeriod() {
        return 0;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void setTokenBucketKey(String tokenBucketKey) {
        this.tokenBucketKey = tokenBucketKey;
    }

    public void setAddNum(int addNum) {
        this.addNum = addNum;
    }

    public void setAddTimeWithMillisecond(long addTimeWithMillisecond) {
        this.addTimeWithMillisecond = addTimeWithMillisecond;
    }

    public void setAddPeriod(long addPeriod) {
        this.addPeriod = addPeriod;
    }
}
