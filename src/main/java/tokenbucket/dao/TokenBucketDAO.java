package tokenbucket.dao;

import tokenbucket.domain.TokenBucket;

/**
 * @author wuhao
 */
public interface TokenBucketDAO {

    TokenBucket getTokenBucket(String tokenBucketKey);

    boolean saveTokenBucket(TokenBucket tokenBucket);

}
