package tokenbucket;

/**
 * @author wuhao
 */
public abstract class TokenBucketAbstractService implements TokenBucketService {

    protected abstract boolean lock();

    protected abstract boolean tryLock();

    protected abstract boolean unlock();

    @Override
    public boolean consume() {
        try {
            if (lock()) {
                return doConsume();
            }
            return false;
        } finally {
            unlock();
        }
    }

    @Override
    public boolean tryConsume() {
        try {
            if (tryLock()) {
                return doConsume();
            }
            return false;
        } finally {
            unlock();
        }

    }

    protected abstract boolean doConsume();


}
