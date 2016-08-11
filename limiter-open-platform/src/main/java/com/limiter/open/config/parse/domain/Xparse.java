package com.limiter.open.config.parse.domain;

import com.limiter.open.config.RuleDao;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

/**
 * @author wuhao
 */
public class Xparse {

    private String resourceName;

    public Xparse(String resourceName) {
        this.resourceName = resourceName;
    }

    public XDocument parse() throws IOException, SAXException, ParserConfigurationException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(RuleDao.class.getClassLoader().getResourceAsStream(resourceName));
        return new XDocument(document);
    }
}
