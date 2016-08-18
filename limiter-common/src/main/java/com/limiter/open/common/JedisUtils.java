package com.limiter.open.common;

import org.apache.commons.lang3.StringUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author wuhao
 */
public class JedisUtils {

    private static JedisPool jedisPool;

    static {
        JedisConfig jedisConfig = load();

        JedisPoolConfig jdeJedisPoolConfig = new JedisPoolConfig();
        jdeJedisPoolConfig.setMaxIdle(jedisConfig.getMaxIdle());
        jdeJedisPoolConfig.setMaxTotal(jedisConfig.getMaxTotal());
        jedisPool = new JedisPool(jdeJedisPoolConfig, jedisConfig.getHost(), jedisConfig.getPort(),
                jedisConfig.getTimeout(), jedisConfig.getPassword());
    }

    public static Jedis getJedis() {
        return jedisPool.getResource();
    }

    public static JedisConfig load() {
        return load(null);
    }

    private static JedisConfig load(String path) {
        if (StringUtils.isEmpty(path)) {
            path = "redis.properties";
        }
        InputStream inputStream = JedisUtils.class.getClassLoader().getResourceAsStream(path);
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
