package tokenbucket;

import java.util.concurrent.TimeUnit;

/**
 * @author wuhao
 */
public class TokenBucketContainer {

    private String tokenBucketName;

    private final static long DEFAULT_MIN_NANOTIME = 1000L;

    private TokenBucketAbstractService tokenBucketAbstractService;

    public boolean consume() {
        return tokenBucketAbstractService.consume();
    }

    public boolean tryConsume(long time, TimeUnit timeUnit) throws InterruptedException {
        if (tryConsume() || doTry(timeUnit.toNanos(time))) {
            return true;
        }
        return false;
    }

    private boolean doTry(long nanoTime) throws InterruptedException {
        long nowNanoTime = System.nanoTime();
        for (; ; ) {
            if (nanoTime > DEFAULT_MIN_NANOTIME) {
                TimeUnit.NANOSECONDS.sleep(nanoTime);
            }
            if ((System.nanoTime() - nowNanoTime) > nanoTime) {
                return tryConsume();
            }
            if (Thread.interrupted()) {
                throw new InterruptedException();
            }
        }
    }

    public boolean tryConsume() {
        return tokenBucketAbstractService.tryConsume();
    }
}
