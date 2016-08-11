package com.limiter.open.config;

import com.limiter.open.config.parse.LocalFileParseServiceImpl;
import com.limiter.open.config.domain.*;
import org.apache.commons.collections4.CollectionUtils;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @author wuhao
 */
public class LocalFileParseServiceImplTest {

    @Test
    public void testParse() {
        LocalFileParseServiceImpl localFileParseService = new LocalFileParseServiceImpl();
        Config config = localFileParseService.parse("configs.xml");
        assertNotNull(config);

        BaseConfigInfo baseConfigInfo = config.getGlobalConfig();
        assertNotNull(baseConfigInfo);
        assertEquals(baseConfigInfo.getAddNum(), 1);
        assertEquals(baseConfigInfo.getAddPeriod(), 2000);
        assertEquals(baseConfigInfo.getAddTimeWithMillisecond(), 1000);
        assertEquals(baseConfigInfo.getCapacity(), 120);


        List<AppkeyConfig> appkeyConfigs = config.getAppkeyConfigs();
        assertTrue(CollectionUtils.isNotEmpty(appkeyConfigs));
        AppkeyConfig appkeyConfig = appkeyConfigs.get(0);
        assertEquals(appkeyConfig.getAddNum(), 1);
        assertEquals(appkeyConfig.getAddPeriod(), 2000);
        assertEquals(appkeyConfig.getAddTimeWithMillisecond(), 1000);
        assertEquals(appkeyConfig.getCapacity(), 120);
        assertEquals(appkeyConfig.getAppkey(), "test");

        List<MethodConfig> methodConfigs = config.getMethodConfigs();
        assertTrue(CollectionUtils.isNotEmpty(methodConfigs));
        MethodConfig methodConfig = methodConfigs.get(0);
        assertEquals(methodConfig.getAddNum(), 1);
        assertEquals(methodConfig.getAddPeriod(), 2000);
        assertEquals(methodConfig.getAddTimeWithMillisecond(), 1000);
        assertEquals(methodConfig.getCapacity(), 120);
        assertEquals(methodConfig.getMethod(), "getTest");

        List<DetailConfig> detailConfigs = config.getDetailConfigs();
        assertTrue(CollectionUtils.isNotEmpty(detailConfigs));
        DetailConfig detailConfig = detailConfigs.get(0);
        assertEquals(detailConfig.getAddNum(), 1);
        assertEquals(detailConfig.getAddPeriod(), 2000);
        assertEquals(detailConfig.getAddTimeWithMillisecond(), 1000);
        assertEquals(detailConfig.getCapacity(), 120);
        assertEquals(detailConfig.getMethod(), "getTest");
        assertEquals(detailConfig.getAppkey(), "test");
    }
}
