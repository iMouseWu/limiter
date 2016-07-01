package com.limiter.tokenbucket;

import java.util.concurrent.TimeUnit;

import com.limiter.tokenbucket.service.TokenBucketService;

/**
 * @author wuhao
 */
public class TokenBucketContainer {

    private String tokenBucketkey;

    private TokenBucketService tokenBucketService;

    public TokenBucketContainer(String tokenBucketkey) {
        this.tokenBucketkey = tokenBucketkey;
    }

    public void setTokenBucketService(TokenBucketService tokenBucketService) {
        this.tokenBucketService = tokenBucketService;
    }

    public boolean consume() {
        return tokenBucketService.consume(tokenBucketkey);
    }

    public boolean tryConsume(long time, TimeUnit timeUnit) throws InterruptedException {
        return tokenBucketService.tryConsume(tokenBucketkey, time, timeUnit);
    }

    public boolean tryConsume() {
        return tokenBucketService.tryConsume(tokenBucketkey);
    }

}
