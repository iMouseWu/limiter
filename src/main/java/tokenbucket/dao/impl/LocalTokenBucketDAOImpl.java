package tokenbucket.dao.impl;

import tokenbucket.dao.TokenBucketDAO;
import tokenbucket.domain.TokenBucket;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wuhao
 */
public class LocalTokenBucketDAOImpl implements TokenBucketDAO {

    private static Map<String, TokenBucket> tokenBucketMap = new HashMap<>();

    private static LocalTokenBucketDAOImpl localTokenBucketDAO = new LocalTokenBucketDAOImpl();

    private LocalTokenBucketDAOImpl() {

    }

    public static LocalTokenBucketDAOImpl getInstance() {
        return localTokenBucketDAO;
    }

    @Override
    public TokenBucket getTokenBucket(String tokenBucketKey) {
        return tokenBucketMap.get(tokenBucketKey);
    }

    @Override
    public boolean saveTokenBucket(TokenBucket tokenBucket) {
        tokenBucketMap.put(tokenBucket.getTokenBucketKey(), tokenBucket);
        return true;
    }
}
