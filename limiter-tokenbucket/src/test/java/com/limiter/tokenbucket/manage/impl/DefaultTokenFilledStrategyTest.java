package com.limiter.tokenbucket.manage.impl;

import com.limiter.tokenbucket.domain.DefaultTokenBucket;
import com.limiter.tokenbucket.domain.TokenBucket;
import com.limiter.tokenbucket.time.TimeTools;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author wuhao
 */
public class DefaultTokenFilledStrategyTest {

    private static DefaultTokenFilledStrategy tokenFilledStrategy;

    private static final long CURRENTTIMEMILLIS = 3456L;

    @BeforeClass
    public static void beforeClass() {
        tokenFilledStrategy = new DefaultTokenFilledStrategy();
        tokenFilledStrategy.setTimeTools(new TestTimeTools());
    }

    @Test
    public void testFilledWithNormal() {
        DefaultTokenBucket defaultTokenBucket = new DefaultTokenBucket();
        defaultTokenBucket.setAddNum(2);
        defaultTokenBucket.setAddPeriod(0);
        defaultTokenBucket.setAddTimeWithMillisecond(1000);
        defaultTokenBucket.setCapacity(120);
        defaultTokenBucket.setLastRefillTimePoint(1000);
        defaultTokenBucket.setTokenBucketKey("test");
        defaultTokenBucket.setTokenNum(10);
        TokenBucket tokenBucket = tokenFilledStrategy.filled(defaultTokenBucket);
        assertEquals(tokenBucket.getTokenNum(), 14);
        assertEquals(tokenBucket.getLastRefillTimePoint(), 3000);
    }

    @Test
    public void testFilledWithAddTimeWithMillisecondBiggerTime() {
        DefaultTokenBucket defaultTokenBucket = new DefaultTokenBucket();
        defaultTokenBucket.setAddNum(2);
        defaultTokenBucket.setAddPeriod(0);
        defaultTokenBucket.setAddTimeWithMillisecond(3000);
        defaultTokenBucket.setCapacity(120);
        defaultTokenBucket.setLastRefillTimePoint(1000);
        defaultTokenBucket.setTokenBucketKey("test");
        defaultTokenBucket.setTokenNum(10);
        TokenBucket tokenBucket = tokenFilledStrategy.filled(defaultTokenBucket);
        assertEquals(tokenBucket.getTokenNum(), 10);
        assertEquals(tokenBucket.getLastRefillTimePoint(), 1000);
    }

    @Test
    public void testFilledWithAddPeriod() {
        DefaultTokenBucket defaultTokenBucket = new DefaultTokenBucket();
        defaultTokenBucket.setAddNum(2);
        defaultTokenBucket.setAddPeriod(500);
        defaultTokenBucket.setAddTimeWithMillisecond(1000);
        defaultTokenBucket.setCapacity(120);
        defaultTokenBucket.setLastRefillTimePoint(1000);
        defaultTokenBucket.setTokenBucketKey("test");
        defaultTokenBucket.setTokenNum(10);
        TokenBucket tokenBucket = tokenFilledStrategy.filled(defaultTokenBucket);
        assertEquals(tokenBucket.getTokenNum(), 14);
        assertEquals(tokenBucket.getLastRefillTimePoint(), 3000);
    }

    @Test
    public void testFilledWithAddPeriodWithAddTimeWithMillisecondBiggerTime() {
        DefaultTokenBucket defaultTokenBucket = new DefaultTokenBucket();
        defaultTokenBucket.setAddNum(2);
        defaultTokenBucket.setAddPeriod(500);
        defaultTokenBucket.setAddTimeWithMillisecond(3000);
        defaultTokenBucket.setCapacity(120);
        defaultTokenBucket.setLastRefillTimePoint(1000);
        defaultTokenBucket.setTokenBucketKey("test");
        defaultTokenBucket.setTokenNum(10);
        TokenBucket tokenBucket = tokenFilledStrategy.filled(defaultTokenBucket);
        assertEquals(tokenBucket.getTokenNum(), 10);
        assertEquals(tokenBucket.getLastRefillTimePoint(), 1000);
    }

    @Test
    public void testFilledWithAddPeriodBigger() {
        DefaultTokenBucket defaultTokenBucket = new DefaultTokenBucket();
        defaultTokenBucket.setAddNum(2);
        defaultTokenBucket.setAddPeriod(3000);
        defaultTokenBucket.setAddTimeWithMillisecond(1000);
        defaultTokenBucket.setCapacity(120);
        defaultTokenBucket.setLastRefillTimePoint(1000);
        defaultTokenBucket.setTokenBucketKey("test");
        defaultTokenBucket.setTokenNum(10);
        TokenBucket tokenBucket = tokenFilledStrategy.filled(defaultTokenBucket);
        assertEquals(tokenBucket.getTokenNum(), 10);
        assertEquals(tokenBucket.getLastRefillTimePoint(), 1000);
    }

    @Test
    public void testFilledWithAddPeriodBiggerWithAddTimeWithMillisecondBiggerTime() {
        DefaultTokenBucket defaultTokenBucket = new DefaultTokenBucket();
        defaultTokenBucket.setAddNum(2);
        defaultTokenBucket.setAddPeriod(3000);
        defaultTokenBucket.setAddTimeWithMillisecond(1000);
        defaultTokenBucket.setCapacity(120);
        defaultTokenBucket.setLastRefillTimePoint(1000);
        defaultTokenBucket.setTokenBucketKey("test");
        defaultTokenBucket.setTokenNum(10);
        TokenBucket tokenBucket = tokenFilledStrategy.filled(defaultTokenBucket);
        assertEquals(tokenBucket.getTokenNum(), 10);
        assertEquals(tokenBucket.getLastRefillTimePoint(), 1000);
    }

    @Test
    public void testFilledWithCapacityWithOverflow() {
        DefaultTokenBucket defaultTokenBucket = new DefaultTokenBucket();
        defaultTokenBucket.setAddNum(2);
        defaultTokenBucket.setAddTimeWithMillisecond(1000);
        defaultTokenBucket.setCapacity(2);
        defaultTokenBucket.setLastRefillTimePoint(1000);
        defaultTokenBucket.setTokenBucketKey("test");
        defaultTokenBucket.setTokenNum(2);
        TokenBucket tokenBucket = tokenFilledStrategy.filled(defaultTokenBucket);
        assertEquals(tokenBucket.getTokenNum(), 2);
        assertEquals(tokenBucket.getLastRefillTimePoint(), 3000);
    }

    @Test
    public void testFilledWithCapacityWithOverflowCase1() {
        DefaultTokenBucket defaultTokenBucket = new DefaultTokenBucket();
        defaultTokenBucket.setAddNum(2);
        defaultTokenBucket.setAddTimeWithMillisecond(1000);
        defaultTokenBucket.setCapacity(2);
        defaultTokenBucket.setLastRefillTimePoint(1000);
        defaultTokenBucket.setTokenBucketKey("test");
        defaultTokenBucket.setTokenNum(1);
        TokenBucket tokenBucket = tokenFilledStrategy.filled(defaultTokenBucket);
        assertEquals(tokenBucket.getTokenNum(), 2);
        assertEquals(tokenBucket.getLastRefillTimePoint(), 3000);
    }

    public static class TestTimeTools implements TimeTools {

        @Override
        public long getCurrentTimeMillis() {
            return CURRENTTIMEMILLIS;
        }
    }
}
