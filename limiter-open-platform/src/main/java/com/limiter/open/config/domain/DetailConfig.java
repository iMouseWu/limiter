package com.limiter.open.config.domain;

/**
 * @author wuhao
 */
public class DetailConfig extends BaseConfigInfo {

    private String appkey;

    private String method;

    public String getAppkey() {
        return appkey;
    }

    public void setAppkey(String appkey) {
        this.appkey = appkey;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }
}
