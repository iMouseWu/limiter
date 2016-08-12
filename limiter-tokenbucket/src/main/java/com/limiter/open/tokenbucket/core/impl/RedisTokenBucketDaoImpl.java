package com.limiter.open.tokenbucket.core.impl;

import com.limiter.open.tokenbucket.core.TokenBucketDao;
import com.limiter.open.tokenbucket.domain.TokenBucket;
import org.apache.commons.lang3.SerializationUtils;
import redis.clients.jedis.Jedis;

/**
 * @author wuhao
 */
public class RedisTokenBucketDaoImpl implements TokenBucketDao {
    @Override
    public TokenBucket getTokenBucket(String tokenBucketKey) {
        Jedis jedis = new Jedis("localhost");
        byte[] bytes = jedis.get(tokenBucketKey.getBytes());
        return (TokenBucket) SerializationUtils.deserialize(bytes);
    }

    @Override
    public boolean saveTokenBucket(TokenBucket tokenBucket) {
        Jedis jedis = new Jedis("localhost");
        String tokenBucketKey = tokenBucket.getTokenBucketKey();
        jedis.set(tokenBucketKey.getBytes(), SerializationUtils.serialize(tokenBucket));
        return true;
    }
}
