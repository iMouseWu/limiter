package tokenbucket.config;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wuhao
 */
public class LocalConfigCenterImpl implements ConfigCenter {

    private Map<String, TokenBucketConfig> configMap = new HashMap<>();

    @Override
    public TokenBucketConfig getConfig(String tokenBucketKey) {
        return configMap.get(tokenBucketKey);
    }

    @Override
    public void registerConfig(TokenBucketConfig tokenBucketConfig) {
        configMap.put(tokenBucketConfig.getTokenBucketKey(), tokenBucketConfig);
    }

    @Override
    public void clear() {
        configMap.clear();
    }


}
