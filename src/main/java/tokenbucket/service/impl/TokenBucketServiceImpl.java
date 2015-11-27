package tokenbucket.service.impl;

import tokenbucket.TokenAddHandle;
import tokenbucket.manage.TokenBucketManager;
import tokenbucket.service.TokenBucketService;

import java.util.concurrent.TimeUnit;

/**
 * @author wuhao
 */
public class TokenBucketServiceImpl extends TokenBucketAbstractService implements TokenBucketService {

    private TokenAddHandle tokenAddHandle;

    private TokenBucketManager tokenBucketManager;

    @Override
    public boolean lock(String source) {
        return tokenBucketManager.lock(source);
    }

    @Override
    protected boolean tryLock(String source) {
        return tokenBucketManager.tryLock(source);
    }

    @Override
    public boolean unlock(String source) {
        return tokenBucketManager.unlock(source);
    }

    @Override
    protected boolean tryLock(String source, long timeout, TimeUnit unit) {
        return tokenBucketManager.tryLock(source, timeout, unit);
    }

    @Override
    protected boolean doConsume(String source) {
        return false;
    }


}
