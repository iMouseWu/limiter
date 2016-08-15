package com.limiter.open.tokenbucket.core.impl;

import com.limiter.open.common.JedisSupport;
import com.limiter.open.tokenbucket.core.TokenBucketDao;
import com.limiter.open.tokenbucket.domain.TokenBucket;
import org.apache.commons.lang3.SerializationUtils;
import redis.clients.jedis.Jedis;

/**
 * @author wuhao
 */
public class RedisTokenBucketDaoImpl extends JedisSupport implements TokenBucketDao {
    @Override
    public TokenBucket getTokenBucket(String tokenBucketKey) {
        byte[] bytes = getJedis().get(tokenBucketKey.getBytes());
        return (TokenBucket) SerializationUtils.deserialize(bytes);
    }

    @Override
    public boolean saveTokenBucket(TokenBucket tokenBucket) {
        String tokenBucketKey = tokenBucket.getTokenBucketKey();
        getJedis().set(tokenBucketKey.getBytes(), SerializationUtils.serialize(tokenBucket));
        return true;
    }
}
