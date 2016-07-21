package com.limiter.tokenbucket;

import com.limiter.tokenbucket.dao.impl.LocalConfigCenterImpl;
import com.limiter.tokenbucket.dao.impl.LocalTokenBucketDAOImpl;

/**
 * @author wuhao
 */
public class ObjectFactory {

    private static LocalConfigCenterImpl configCenter = new LocalConfigCenterImpl();

    private static LocalTokenBucketDAOImpl tokenBucketDAO = new LocalTokenBucketDAOImpl();

    public static LocalConfigCenterImpl getConfigCenterInstance() {
        return configCenter;
    }

    public static LocalTokenBucketDAOImpl getTokenBucketDAOInstance() {
        return tokenBucketDAO;
    }

}
