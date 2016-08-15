package com.limiter.open.common;

import redis.clients.jedis.Jedis;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author wuhao
 */
public class JedisSupport {

    public JedisSupport() {
    }

    protected Jedis getJedis() {
        return (Jedis) Proxy.newProxyInstance(JedisSupport.class.getClassLoader(), new Class[] { Jedis.class },
                new JedisInterceptor());
    }

    private class JedisInterceptor implements InvocationHandler {
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
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

}
