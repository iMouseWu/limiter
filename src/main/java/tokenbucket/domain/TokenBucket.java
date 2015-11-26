package tokenbucket.domain;

/**
 * @author wuhao
 */
public interface TokenBucket {

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
     * 每秒添加令牌的个数
     */
    int getTokenCountPerSecond();


}
