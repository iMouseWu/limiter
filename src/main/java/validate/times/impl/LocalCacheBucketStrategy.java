package validate.times.impl;

import validate.buket.TokenBucket;
import validate.buket.TokenBuckets;
import validate.buket.TokenContainer;
import validate.times.LimitProperty;
import validate.times.LimitPropertyFactory;
import validate.times.TimeCountStrategy;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 本地缓存策略,不支持多个JVM的情况
 *
 * @author wuhao
 */
public class LocalCacheBucketStrategy implements TimeCountStrategy {

    private Map<String, TokenContainer> bucketMap = new HashMap<String, TokenContainer>();

    private LimitPropertyFactory limitPropertyAchieve;

    private final static int CAPACITY = 16;

    /**
     * 分段锁
     */
    private static Object[] locks = new Object[CAPACITY];

    static {
        for (int i = 0; i < CAPACITY - 1; i++) {
            locks[i] = new Object();
        }
    }

    @Override
    public int getCount(String vistor, String resource) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public boolean visit(String vistor, String resource) {
        LimitProperty visitProperty = limitPropertyAchieve.getProperty(vistor, resource);
        int bucketNum = visitProperty.getVisitLimitCount();
        int refillTokens = bucketNum / visitProperty.getSecond();

        String key = generateKey(vistor, resource);

        TokenContainer tokenContainer = bucketMap.get(key);
        int index = getIndex(key);
        synchronized (locks[index]) {
            tokenContainer = bucketMap.get(key);
            if (null == tokenContainer) {
                tokenContainer = new TokenContainer(key, bucketNum, bucketNum, TimeUnit.SECONDS.toMillis(1));
                bucketMap.put(key, tokenContainer);
            }
        }
        TokenBucket tokenBucket = TokenBuckets.builder().withTokenContainer(tokenContainer)
                .withFixedIntervalRefillStrategy(refillTokens, tokenContainer).build();
        synchronized (tokenContainer) {
            return tokenBucket.tryConsume();
        }
    }

    private String generateKey(String vistor, String resource) {
        return vistor + "_" + resource;
    }

    private int getIndex(String key) {
        return Math.abs(key.hashCode() % (CAPACITY - 1));
    }

}
