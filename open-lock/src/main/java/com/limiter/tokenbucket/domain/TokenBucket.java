package com.limiter.tokenbucket.domain;

/**
 * @author wuhao
 */
public interface TokenBucket {

    String getTokenBucketKey();

    /**
     * 获取容量
     *
     * @return
     */
    int getCapacity();

    /**
     * 获取令牌的个数
     *
     * @return
     */
    int getTokenNum();

    /**
     * 获取上次令牌填充时间点(有可能这个时间点A去执行填充,但是该值可能是A之前的某个时间点)
     *
     * @return
     */
    long getLastRefillTimePoint();

    /**
     * 获取增加AddNum所需要的毫秒时间
     *
     * @return
     */
    long getAddTimeWithMillisecond();

    /**
     * 添加的个数
     *
     * @return
     */
    int getAddNum();

    /**
     * 令牌增加周期,可以为空.如果为空默认用AddTimeWithMillisecond的时间
     *
     * @return
     */
    long getAddPeriod();

    /**
     * 增加令牌数量
     *
     * @param num
     * @return
     */
    void filledToken(int num);

    /**
     * 减少令牌数量
     *
     * @param num
     * @return
     */
    void reduceToken(int num);

    void setLastRefillTimePoint(long time);


}
