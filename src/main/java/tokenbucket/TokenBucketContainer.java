package tokenbucket;

import tokenbucket.service.TokenBucketService;

import java.util.concurrent.TimeUnit;

/**
 * @author wuhao
 */
public class TokenBucketContainer {

    private String tokenBucketName;

    private final static long DEFAULT_MIN_NANOTIME = 1000L;

    private TokenBucketService tokenBucketService;

    public boolean consume() {
        return tokenBucketService.consume(tokenBucketName);
    }

    public boolean tryConsume(long time, TimeUnit timeUnit) throws InterruptedException {
        return tokenBucketService.tryConsume(tokenBucketName);
    }

    public boolean tryConsume() {
        return tokenBucketService.tryConsume(tokenBucketName);
    }
}
