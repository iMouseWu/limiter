package tokenbucket.service.impl;

import tokenbucket.TokenAddHandle;
import tokenbucket.service.TokenBucketService;

import java.util.concurrent.TimeUnit;

/**
 * @author wuhao
 */
public class TokenBucketServiceImpl extends TokenBucketAbstractService implements TokenBucketService {

    private TokenAddHandle tokenAddHandle;

    @Override
    public boolean lock() {
        return false;
    }

    @Override
    protected boolean tryLock() {
        return false;
    }

    @Override
    public boolean unlock() {
        return false;
    }

    @Override
    protected boolean doConsume() {
        return false;
    }

    @Override
    public boolean tryConsume(long time, TimeUnit timeUnit) {
        return false;
    }
}
