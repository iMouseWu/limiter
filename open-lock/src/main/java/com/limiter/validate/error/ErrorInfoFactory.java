package com.limiter.validate.error;

import com.limiter.validate.ErrorInfo;

/**
 * @author wuhao
 */
public interface ErrorInfoFactory {

    ErrorInfo getErrorInfo(String appKey, String method);

    void register(ErrorInfo errorInfo);

}
