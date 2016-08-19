package com.limiter.open.config.parse;

import com.limiter.open.config.domain.*;
import com.limiter.open.config.parse.domain.XDocument;
import com.limiter.open.config.parse.domain.XElement;
import com.limiter.open.config.parse.domain.Xparse;
import com.limiter.open.config.parse.exception.ParseException;
import org.apache.commons.lang3.StringUtils;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wuhao
 */
public class LocalFileParseServiceImpl implements ParseService {

    private String configPath;

    public void setConfigPath(String configPath) {
        this.configPath = configPath;
    }

    @Override
    public Config parse() {
        if (StringUtils.isEmpty(configPath)) {
            configPath = "configs.xml";
        }
        return parse(configPath);
    }

    @Override
    public Config parse(String path) {
        try {
            Xparse xparse = new Xparse(path);
            XDocument xDocument = xparse.parse();

            XElement bootElement = xDocument.selectSingleElement("configuration");
            XElement globalConfigElement = bootElement.selectSingleChildElement("globalConfig");
            BaseConfigInfo baseConfigInfo = new BaseConfigInfo();
            filledBaseInfo(baseConfigInfo, globalConfigElement);

            XElement configsElement = bootElement.selectSingleChildElement("configs");

            XElement appkeyConfigElement = configsElement.selectSingleChildElement("appkeyConfig");
            String appkeyConfigPath = appkeyConfigElement.getAttribute("path");
            List<AppkeyConfig> appkeyConfigs = parseAppkeyConfig(appkeyConfigPath);

            XElement methodConfigElement = configsElement.selectSingleChildElement("methodConfig");
            String methodConfigPath = methodConfigElement.getAttribute("path");
            List<MethodConfig> methodConfigs = parseMethodConfig(methodConfigPath);

            XElement detailConfigElement = configsElement.selectSingleChildElement("detailConfig");
            String detailConfigPath = detailConfigElement.getAttribute("path");
            List<DetailConfig> detailConfigs = parseDetailConfig(detailConfigPath);

            Config config = new Config();
            config.setAppkeyConfigs(appkeyConfigs);
            config.setDetailConfigs(detailConfigs);
            config.setGlobalConfig(baseConfigInfo);
            config.setMethodConfigs(methodConfigs);
            return config;
        } catch (IOException | SAXException | ParserConfigurationException e) {
            throw new ParseException(e);
        }
    }

    private List<AppkeyConfig> parseAppkeyConfig(String path)
            throws ParserConfigurationException, SAXException, IOException {
        Xparse xparse = new Xparse(path);
        XDocument xDocument = xparse.parse();
        XElement bootElement = xDocument.selectSingleElement("appkeys");
        List<XElement> elements = bootElement.selectChildElement("appkey");

        List<AppkeyConfig> appkeyConfigs = new ArrayList<>();
        for (XElement element : elements) {
            String appkey = element.getAttribute("name");
            AppkeyConfig appkeyConfig = new AppkeyConfig();
            appkeyConfig.setAppkey(appkey);
            filledBaseInfo(appkeyConfig, element);
            appkeyConfigs.add(appkeyConfig);
        }
        return appkeyConfigs;
    }

    private List<MethodConfig> parseMethodConfig(String path)
            throws ParserConfigurationException, SAXException, IOException {
        Xparse xparse = new Xparse(path);
        XDocument xDocument = xparse.parse();
        XElement bootElement = xDocument.selectSingleElement("methods");
        List<XElement> elements = bootElement.selectChildElement("method");

        List<MethodConfig> methodConfigs = new ArrayList<>();
        for (XElement element : elements) {
            String method = element.getAttribute("name");
            MethodConfig methodConfig = new MethodConfig();
            methodConfig.setMethod(method);
            filledBaseInfo(methodConfig, element);
            methodConfigs.add(methodConfig);
        }
        return methodConfigs;
    }

    private List<DetailConfig> parseDetailConfig(String path)
            throws ParserConfigurationException, SAXException, IOException {
        Xparse xparse = new Xparse(path);
        XDocument xDocument = xparse.parse();
        XElement bootElement = xDocument.selectSingleElement("details");
        List<XElement> elements = bootElement.selectChildElement("detail");

        List<DetailConfig> detailConfigs = new ArrayList<>();
        for (XElement element : elements) {
            String method = element.getAttribute("method");
            String appkey = element.getAttribute("appkey");
            DetailConfig detailConfig = new DetailConfig();
            detailConfig.setMethod(method);
            detailConfig.setAppkey(appkey);
            filledBaseInfo(detailConfig, element);
            detailConfigs.add(detailConfig);
        }
        return detailConfigs;
    }

    private void filledBaseInfo(BaseConfigInfo baseConfigInfo, XElement xElement) {
        XElement capacityElement = xElement.selectSingleChildElement("capacity");
        baseConfigInfo.setCapacity(capacityElement.getTextAsInt());

        XElement addNumElement = xElement.selectSingleChildElement("addNum");
        baseConfigInfo.setAddNum(addNumElement.getTextAsInt());

        XElement addTimeWithMillisecondElement = xElement.selectSingleChildElement("addTimeWithMillisecond");
        baseConfigInfo.setAddTimeWithMillisecond(addTimeWithMillisecondElement.getTextAsLong());

        XElement addPeriodElement = xElement.selectSingleChildElement("addPeriod");
        baseConfigInfo.setAddPeriod(addPeriodElement.getTextAsLong());
    }
}
