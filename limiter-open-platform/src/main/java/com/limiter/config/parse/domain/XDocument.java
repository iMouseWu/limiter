package com.limiter.config.parse.domain;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * @author wuhao
 */
public class XDocument {

    private Document document;

    public XDocument(Document document) {
        this.document = document;
    }

    public XElement selectSingleElement(String tagName) {
        NodeList nodeList = document.getElementsByTagName(tagName);
        if (nodeList.getLength() == 0) {
            return null;
        }
        if (nodeList.getLength() > 1) {
            throw new RuntimeException();
        }
        Node node = nodeList.item(0);

        return new XElement((Element) node);
    }
}
