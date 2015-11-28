package tokenbucket;

import tokenbucket.service.TokenBucketService;

import java.util.concurrent.TimeUnit;

/**
 * @author wuhao
 */
public class TokenBucketContainer {

    private String tokenBucketName;

    private TokenBucketService tokenBucketService;

    public TokenBucketContainer(String tokenBucketName) {
        this.tokenBucketName = tokenBucketName;
    }

    public void setTokenBucketService(TokenBucketService tokenBucketService) {
        this.tokenBucketService = tokenBucketService;
    }

    public boolean consume() {
        return tokenBucketService.consume(tokenBucketName);
    }

    public boolean tryConsume(long time, TimeUnit timeUnit) throws InterruptedException {
        return tokenBucketService.tryConsume(tokenBucketName, time, timeUnit);
    }

    public boolean tryConsume() {
        return tokenBucketService.tryConsume(tokenBucketName);
    }
}
