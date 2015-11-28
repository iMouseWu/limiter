package tokenbucket.manage;

import tokenbucket.domain.TokenBucket;

/**
 * @author wuhao
 */
public interface TokenFilledStrategy {

    TokenBucket filled(TokenBucket tokenBucket);

}
