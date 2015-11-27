package tokenbucket.service;

import java.util.concurrent.TimeUnit;

/**
 * @author wuhao
 */
public interface TokenBucketService {

    boolean consume(String source);

    boolean tryConsume(String source);

    boolean tryConsume(String source,long time, TimeUnit timeUnit);
}
