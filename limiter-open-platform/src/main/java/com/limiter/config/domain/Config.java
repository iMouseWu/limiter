package com.limiter.config.domain;

import java.util.List;

/**
 * @author wuhao
 */
public class Config {

    private BaseConfigInfo globalConfig;

    private List<DetailConfig> detailConfigs;

    private List<MethodConfig> methodConfigs;

    private List<AppkeyConfig> appkeyConfigs;

    public BaseConfigInfo getGlobalConfig() {
        return globalConfig;
    }

    public void setGlobalConfig(BaseConfigInfo globalConfig) {
        this.globalConfig = globalConfig;
    }

    public List<DetailConfig> getDetailConfigs() {
        return detailConfigs;
    }

    public void setDetailConfigs(List<DetailConfig> detailConfigs) {
        this.detailConfigs = detailConfigs;
    }

    public List<MethodConfig> getMethodConfigs() {
        return methodConfigs;
    }

    public void setMethodConfigs(List<MethodConfig> methodConfigs) {
        this.methodConfigs = methodConfigs;
    }

    public List<AppkeyConfig> getAppkeyConfigs() {
        return appkeyConfigs;
    }

    public void setAppkeyConfigs(List<AppkeyConfig> appkeyConfigs) {
        this.appkeyConfigs = appkeyConfigs;
    }

    public DetailConfig getDetailConfig(String appkey, String method) {
        for (DetailConfig detailConfig : detailConfigs) {
            if (detailConfig.getAppkey().equals(appkey) && detailConfig.getMethod().equals(method)) {
                return detailConfig;
            }
        }
        return null;
    }

    public MethodConfig getMethodConfig(String method) {
        for (MethodConfig methodConfig : methodConfigs) {
            if (methodConfig.getMethod().equals(method)) {
                return methodConfig;
            }
        }
        return null;
    }

    public AppkeyConfig getAppkeyConfig(String appkey) {
        for (AppkeyConfig appkeyConfig : appkeyConfigs) {
            if (appkeyConfig.getAppkey().equals(appkey)) {
                return appkeyConfig;
            }
        }
        return null;
    }



}
