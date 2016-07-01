package com.limiter.init.impl;

import com.alibaba.fastjson.JSON;
import com.limiter.init.Config;
import com.limiter.init.RuleDao;

import java.util.List;

/**
 * @author wuhao
 */
public class LocaFileRuleDaoImpl implements RuleDao {
    @Override
    public List<Config> load() {
        String content = "";
        List<Config> configs = JSON.parseArray(content, Config.class);
        return configs;
    }
}
