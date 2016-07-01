package com.limiter.tokenbucket.manage;

import com.limiter.tokenbucket.domain.TokenBucket;

/**
 * @author wuhao
 */
public interface TokenFilledStrategy {

    TokenBucket filled(TokenBucket tokenBucket);

}
