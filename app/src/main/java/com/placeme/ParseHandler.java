package com.placeme;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class ParseHandler extends DefaultHandler {

    private List<Item> dataItem;

    // Used to reference item while parsing
    private Item currentItem;
    // Parsing title indicator
    private boolean parsingTitle;
    // Parsing link indicator
    private boolean parsingContents;
    // Buffer for title contents
    private StringBuffer currentTitleSb;
    // Buffer for content tag contents
    private StringBuffer currentContentSb;

    public ParseHandler() {
        dataItem = new ArrayList<>();
    }

    public List<Item> getItems() {
        return dataItem;
    }

    @Override
    public void startElement(String uri, String localName, String qName,
                             Attributes attributes) throws SAXException {

        if ("entry".equals(qName)) {
            currentItem = new Item();
        } else if ("title".equals(qName)) {
            parsingTitle = true;
            currentTitleSb = new StringBuffer();
        } else if ("content".equals(qName)) {
            parsingContents = true;
            currentContentSb = new StringBuffer();
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {

        if ("entry".equals(qName)) {
            dataItem.add(currentItem);
            currentItem = null;
        } else if ("title".equals(qName)) {
            parsingTitle = false;

            if (currentItem != null)
                /* Title tag for whole channel present.
                 It is parsed before the entry tag is present */
                currentItem.setTitle(currentTitleSb.toString());

        } else if ("content".equals(qName)) {
            parsingContents = false;

            if (currentItem != null)
                currentItem.setContent(currentContentSb.toString());
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {

        if (parsingTitle) {
            if (currentItem != null)
                currentTitleSb.append(new String(ch, start, length));
        } else if (parsingContents) {
            if (currentItem != null)
                currentContentSb.append(new String(ch, start, length));
        }
    }

}
