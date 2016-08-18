package com.limiter.open.common;

import net.sf.cglib.proxy.Enhancer;
import redis.clients.jedis.Jedis;

/**
 * @author wuhao
 */
public class JedisSupport {

    public JedisSupport() {
    }

    protected Jedis getJedis() {
        Enhancer en = new Enhancer();
        en.setSuperclass(Jedis.class);
        en.setCallback(new JedisCglibProxy());
        return (Jedis) en.create();
    }
}
