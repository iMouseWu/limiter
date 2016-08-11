package com.limiter.open.validate.error;

import com.limiter.open.validate.ErrorInfo;

/**
 * @author wuhao
 */
public interface ErrorInfoFactory {

    ErrorInfo getErrorInfo(String appKey, String method);

    void register(ErrorInfo errorInfo);

}
