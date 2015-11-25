package tokenbucket;

/**
 * @author wuhao
 */
public interface TokenBucketService {

    boolean consume();

    boolean tryConsume();
}
