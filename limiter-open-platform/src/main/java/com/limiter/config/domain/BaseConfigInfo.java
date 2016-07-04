package com.limiter.config.domain;

/**
 * @author wuhao
 */
public class BaseConfigInfo {

    private int capacity;

    private int addNum;

    private long addTimeWithMillisecond;

    private long addPeriod;

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getAddNum() {
        return addNum;
    }

    public void setAddNum(int addNum) {
        this.addNum = addNum;
    }

    public long getAddTimeWithMillisecond() {
        return addTimeWithMillisecond;
    }

    public void setAddTimeWithMillisecond(long addTimeWithMillisecond) {
        this.addTimeWithMillisecond = addTimeWithMillisecond;
    }

    public long getAddPeriod() {
        return addPeriod;
    }

    public void setAddPeriod(long addPeriod) {
        this.addPeriod = addPeriod;
    }

}
