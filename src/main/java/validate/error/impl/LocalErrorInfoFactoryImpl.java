package validate.error.impl;

import validate.ErrorInfo;
import validate.error.ErrorInfoFactory;

import java.util.HashMap;
import java.util.Map;

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
