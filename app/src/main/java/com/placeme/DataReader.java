package com.placeme;

import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class DataReader {
    String url;

    public DataReader(String url) {
        this.url = url;
    }

    public List<Item> getItems() throws Exception {
        // SAX parse RSS data
        SAXParserFactory factory = SAXParserFactory.newInstance();

        SAXParser saxParser = factory.newSAXParser();

        ParseHandler handler = new ParseHandler();

        saxParser.parse(url, handler);

        return handler.getItems();

    }
}
