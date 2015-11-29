package validate.error;

import validate.ErrorInfo;

/**
 * @author wuhao
 */
public interface ErrorInfoFactory {

    ErrorInfo getErrorInfo(String appKey, String method);

    void register(ErrorInfo errorInfo);

}
