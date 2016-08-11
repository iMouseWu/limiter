package com.limiter.open.validate.error.impl;

import java.util.HashMap;
import java.util.Map;

import com.limiter.open.validate.ErrorInfo;
import com.limiter.open.validate.error.ErrorInfoFactory;

/**
 * @author wuhao
 */
public class LocalErrorInfoFactoryImpl implements ErrorInfoFactory {
    private static Map<String, ErrorInfo> errorInfoMap = new HashMap<>();

    @Override
    public ErrorInfo getErrorInfo(String appKey, String method) {
        return errorInfoMap.get(method);
    }

    @Override
    public void register(ErrorInfo errorInfo) {
        errorInfoMap.put(errorInfo.getMethod(), errorInfo);
    }
}
