package com.limiter.open.common;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import redis.clients.jedis.Jedis;

import java.lang.reflect.Method;

/**
 * @author wuhao
 */
public class JedisCglibProxy implements MethodInterceptor {

    @Override
    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        Jedis jedis = null;
        try {
            jedis = JedisUtils.getJedis();
            return method.invoke(jedis, args);
        } finally {
            if (null != jedis) {
                jedis.close();
            }
        }
    }
}
