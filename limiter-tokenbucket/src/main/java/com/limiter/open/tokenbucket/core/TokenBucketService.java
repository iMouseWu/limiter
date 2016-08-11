package com.limiter.open.tokenbucket.core;

import java.util.concurrent.TimeUnit;

/**
 * @author wuhao
 */
public interface TokenBucketService {

    /**
     * 阻塞方法,只有消费成功了才会返回
     * @param tokenBucketKey
     * @return
     */
    boolean consume(String tokenBucketKey);

    /**
     * 非阻塞方法,消费不成功(令牌数量不够)立马返回
     * @param tokenBucketKey
     * @return
     */
    boolean tryConsume(String tokenBucketKey);

    /**
     * 如果消费失败,则会等到
     * @param tokenBucketKey
     * @param time
     * @param timeUnit
     * @return
     */
    boolean tryConsume(String tokenBucketKey, long time, TimeUnit timeUnit);
}
