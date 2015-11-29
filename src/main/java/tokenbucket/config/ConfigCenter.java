package tokenbucket.config;

/**
 * @author wuhao
 */
public interface ConfigCenter {

    TokenBucketConfig getConfig(String tokenBucketKey);

    void registerConfig(TokenBucketConfig tokenBucketConfig);

    void clear();

}
