package tokenbucket.dao.impl;

import tokenbucket.dao.TokenBucketDAO;
import tokenbucket.domain.TokenBucket;

/**
 * @author wuhao
 */
public class LocalTokenBucketDAOImpl implements TokenBucketDAO {
    @Override
    public TokenBucket getTokenBucket(String tokenBucketKey) {
        return null;
    }

    @Override
    public boolean saveTokenBucket(TokenBucket tokenBucket) {
        return false;
    }
}
