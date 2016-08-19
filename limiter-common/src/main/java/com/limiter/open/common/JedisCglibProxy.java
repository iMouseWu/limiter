package com.limiter.open.common;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.apache.commons.lang3.StringUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.Properties;

/**
 * @author wuhao
 */
public class JedisCglibProxy implements MethodInterceptor {

    private static JedisPool jedisPool;

    private String redisConfigPath;

    private volatile boolean isLoad;

    public JedisCglibProxy() {

    }

    public JedisCglibProxy(String redisConfigPath) {
        this.redisConfigPath = redisConfigPath;
    }

    @Override
    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        Jedis jedis = null;
        init();
        try {
            jedis = getJedis();
            return method.invoke(jedis, args);
        } finally {
            if (null != jedis) {
                jedis.close();
            }
        }
    }

    private Jedis getJedis() {
        return jedisPool.getResource();
    }

    private void init() {
        if (isLoad) {
            return;
        }
        synchronized (JedisCglibProxy.class) {
            if (!isLoad) {
                JedisConfig jedisConfig = load(redisConfigPath);

                JedisPoolConfig jdeJedisPoolConfig = new JedisPoolConfig();
                jdeJedisPoolConfig.setMaxIdle(jedisConfig.getMaxIdle());
                jdeJedisPoolConfig.setMaxTotal(jedisConfig.getMaxTotal());
                jedisPool = new JedisPool(jdeJedisPoolConfig, jedisConfig.getHost(), jedisConfig.getPort(),
                        jedisConfig.getTimeout(), jedisConfig.getPassword());
                isLoad = true;
            }
        }
    }

    private JedisConfig load(String path) {
        if (StringUtils.isEmpty(path)) {
            path = "redis.properties";
        }
        InputStream inputStream = JedisCglibProxy.class.getClassLoader().getResourceAsStream(path);
        Properties properties = new Properties();
        try {
            properties.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException("can not parse redis properties", e);
        }

        JedisConfig jedisConfig = new JedisConfig();
        jedisConfig.setMaxTotal(Integer.valueOf(properties.getProperty("redis.maxTotal")));
        jedisConfig.setMaxIdle(Integer.valueOf(properties.getProperty("redis.maxIdle")));
        jedisConfig.setPassword(properties.getProperty("redis.password"));
        jedisConfig.setTimeout(Integer.valueOf(properties.getProperty("redis.timeout")));

        jedisConfig.setHost(properties.getProperty("redis.host"));
        jedisConfig.setPort(Integer.valueOf(properties.getProperty("redis.port")));

        return jedisConfig;
    }
}
