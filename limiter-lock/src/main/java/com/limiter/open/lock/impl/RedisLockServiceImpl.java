package com.limiter.open.lock.impl;

import com.limiter.open.common.JedisSupport;
import com.limiter.open.lock.LockService;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import redis.clients.jedis.Jedis;

import java.util.concurrent.TimeUnit;

/**
 * @author wuhao
 */
public class RedisLockServiceImpl extends JedisSupport implements LockService {

    private final static long TIME_MAX_LOCK = 1000L * 7;

    private final static String NOT_EXIST_VALUE = "nil";

    private final static long SUCCESS_RESULT = 1;

    @Override
    public boolean lock(String source) {
        do {
            if (tryLock(source)) {
                return true;
            }
            try {
                TimeUnit.MILLISECONDS.sleep(300);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        } while (true);
        return false;
    }

    @Override
    public boolean tryLock(String source) {
        String value = System.currentTimeMillis() + "";
        Long result = getJedis().setnx(source, value);
        if (result == SUCCESS_RESULT) {
            return true;
        }
        String oldValue = getJedis().get(source);
        if (StringUtils.equals(oldValue, NOT_EXIST_VALUE)) {
            result = getJedis().setnx(source, value);
            return result == SUCCESS_RESULT;
        }
        long time = NumberUtils.toLong(oldValue);
        if (time < System.currentTimeMillis()) {
            String oldValueMirror = getJedis().getSet(source, System.currentTimeMillis() + TIME_MAX_LOCK + "");
            return StringUtils.equals(oldValueMirror, NOT_EXIST_VALUE) || StringUtils.equals(oldValue, oldValueMirror);
        }
        return false;
    }

    @Override
    public boolean tryLock(String source, long timeout, TimeUnit unit) {
        return false;
    }

    @Override
    public boolean unlock(String source) {
        Jedis jedis = new Jedis("localhost");
        jedis.del(source);
        return true;
    }
}
