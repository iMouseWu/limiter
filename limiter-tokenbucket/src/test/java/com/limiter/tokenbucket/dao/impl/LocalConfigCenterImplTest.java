package com.limiter.tokenbucket.dao.impl;

import com.limiter.tokenbucket.ObjectFactory;
import com.limiter.tokenbucket.dao.ConfigCenter;
import com.limiter.tokenbucket.domain.TokenBucketConfig;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author wuhao
 */
public class LocalConfigCenterImplTest {

    private static String tokenBucketKey;

    private static TokenBucketConfig tokenBucketConfig;

    private static ConfigCenter configCenter;

    @BeforeClass
    public static void beforeClass() {
        tokenBucketKey = "test";

        configCenter = ObjectFactory.getConfigCenterInstance();

        tokenBucketConfig = new TokenBucketConfig() {

            @Override
            public String getTokenBucketKey() {
                return tokenBucketKey;
            }

            @Override
            public int getCapacity() {
                return 120;
            }

            @Override
            public int getAddNum() {
                return 1;
            }

            @Override
            public long getAddTimeWithMillisecond() {
                return 1000;
            }

            @Override
            public long getAddPeriod() {
                return 2000;
            }
        };

    }

    @Test
    public void testGetTokenBucket() {
        boolean result = configCenter.register(tokenBucketConfig);
        assertTrue(result);

        LocalConfigCenterImpl configCenter = ObjectFactory.getConfigCenterInstance();
        TokenBucketConfig tokenBucketResult = configCenter.getConfig(tokenBucketKey);
        assertEquals(tokenBucketConfig.getAddNum(), tokenBucketResult.getAddNum());
        assertEquals(tokenBucketConfig.getAddPeriod(), tokenBucketResult.getAddPeriod());
        assertEquals(tokenBucketConfig.getAddTimeWithMillisecond(), tokenBucketResult.getAddTimeWithMillisecond());
        assertEquals(tokenBucketConfig.getCapacity(), tokenBucketResult.getCapacity());
        assertEquals(tokenBucketConfig.getTokenBucketKey(), tokenBucketResult.getTokenBucketKey());
    }
}
