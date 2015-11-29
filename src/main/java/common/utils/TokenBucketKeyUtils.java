package common.utils;

/**
 * @author wuhao
 */
public final class TokenBucketKeyUtils {

    private static final String SPILIT = "_";

    private TokenBucketKeyUtils() {

    }

    public static String generateTokenBucketKey(String appkey, String method) {
        return appkey + SPILIT + method;
    }

}
