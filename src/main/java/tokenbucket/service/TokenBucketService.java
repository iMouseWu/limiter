package tokenbucket.service;

import java.util.concurrent.TimeUnit;

/**
 * @author wuhao
 */
public interface TokenBucketService {

    boolean consume();

    boolean tryConsume();

    boolean tryConsume(long time, TimeUnit timeUnit);
}
