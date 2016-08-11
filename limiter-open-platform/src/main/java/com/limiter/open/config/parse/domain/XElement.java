package com.limiter.open.config.parse.domain;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wuhao
 */
public class XElement {

    private Element element;

    public XElement(Element element) {
        this.element = element;
    }

    public XElement selectSingleChildElement(String tagName) {
        NodeList nodeList = element.getElementsByTagName(tagName);
        if (nodeList.getLength() == 0) {
            return null;
        }
        if (nodeList.getLength() > 1) {
            throw new RuntimeException();
        }
        Node node = nodeList.item(0);
        return new XElement((Element) node);
    }

    public List<XElement> selectChildElement(String tagName) {
        NodeList nodeList = element.getElementsByTagName(tagName);

        List<XElement> elements = new ArrayList<>();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            elements.add(new XElement((Element) node));
        }
        return elements;
    }

    public String getText() {
        return element.getTextContent();
    }

    public Integer getTextAsInt() {
        return Integer.valueOf(getText());
    }

    public Long getTextAsLong() {
        return Long.valueOf(getText());
    }

    public String getAttribute(String name) {
        return element.getAttribute(name);
    }
}
