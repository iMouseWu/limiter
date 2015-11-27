package tokenbucket.service.impl;

import tokenbucket.TokenAddHandle;
import tokenbucket.lock.LockService;
import tokenbucket.service.TokenBucketService;

import java.util.concurrent.TimeUnit;

/**
 * @author wuhao
 */
public class TokenBucketServiceImpl extends TokenBucketAbstractService implements TokenBucketService {

    private TokenAddHandle tokenAddHandle;

    private LockService lockService;

    @Override
    public boolean lock(String source) {
        return lockService.lock(source);
    }

    @Override
    protected boolean tryLock(String source) {
        return lockService.tryLock(source);
    }

    @Override
    public boolean unlock(String source) {
        return lockService.unlock(source);
    }

    @Override
    protected boolean tryLock(String source, long timeout, TimeUnit unit) {
        return lockService.tryLock(source, timeout, unit);
    }

    @Override
    protected boolean doConsume(String source) {
        return false;
    }


}
