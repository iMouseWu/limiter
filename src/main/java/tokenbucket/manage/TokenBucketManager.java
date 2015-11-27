package tokenbucket.manage;

import tokenbucket.dao.TokenBucketDAO;
import tokenbucket.domain.TokenBucket;
import tokenbucket.lock.LockService;

import java.util.concurrent.TimeUnit;

/**
 * @author wuhao
 */
public class TokenBucketManager implements LockService, TokenBucketDAO {

    private LockService lockService;

    private TokenBucketDAO tokenBucketDAO;

    @Override
    public boolean lock(String tokenBucketKey) {
        return lockService.lock(tokenBucketKey);
    }

    @Override
    public boolean tryLock(String tokenBucketKey) {
        return lockService.tryLock(tokenBucketKey);
    }

    @Override
    public boolean tryLock(String tokenBucketKey, long timeout, TimeUnit unit) {
        return lockService.tryLock(tokenBucketKey, timeout, unit);
    }

    @Override
    public boolean unlock(String tokenBucketKey) {
        return lockService.unlock(tokenBucketKey);
    }

    @Override
    public TokenBucket getTokenBucket(String tokenBucketKey) {
        return tokenBucketDAO.getTokenBucket(tokenBucketKey);
    }

    @Override
    public boolean saveTokenBucket(TokenBucket tokenBucket) {
        return tokenBucketDAO.saveTokenBucket(tokenBucket);
    }
}
