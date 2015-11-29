package tokenbucket.config;

/**
 * @author wuhao
 */
public interface TokenBucketConfig {

    /**
     * 获取令牌桶的Key
     *
     * @return
     */
    String getTokenBucketKey();

    /**
     * 获取令牌桶的容量
     *
     * @return
     */
    int getCapacity();

    /**
     * 获取令牌桶AddTimeWithMillisecond时间段内增加的令牌数
     *
     * @return
     */
    int getAddNum();

    /**
     * 获取令牌桶AddTimeWithMillisecond时间段
     *
     * @return
     */
    long getAddTimeWithMillisecond();

    /**
     * 获取令牌桶增加令牌的频率
     *
     * @return
     */
    int getAddPeriod();


}
