package com.limiter.open.tokenbucket.core;

import com.limiter.open.tokenbucket.domain.TokenBucket;

/**
 * @author wuhao
 */
public interface TokenFilledStrategy {

    TokenBucket filled(TokenBucket tokenBucket);

}
