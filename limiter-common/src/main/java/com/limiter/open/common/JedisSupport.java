package com.limiter.open.common;

import net.sf.cglib.proxy.Enhancer;
import redis.clients.jedis.Jedis;

/**
 * @author wuhao
 */
public class JedisSupport {

    private String redisConfigPath;

    public JedisSupport() {
    }

    public JedisSupport(String redisConfigPath) {
        this.redisConfigPath = redisConfigPath;
    }

    protected Jedis getJedis() {
        Enhancer en = new Enhancer();
        en.setSuperclass(Jedis.class);
        en.setCallback(new JedisCglibProxy(redisConfigPath));
        return (Jedis) en.create();
    }
}
