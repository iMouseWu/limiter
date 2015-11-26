package tokenbucket.lock;

/**
 * @author wuhao
 */
public interface LockService {

    boolean lock(String source);

    boolean tryLock(String source);

    boolean unlock(String source);
}
