package com.horbatiuk.htmlParser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import java.io.IOException;

/**
 * Created by Andrey on 16.09.2016.
 */
public final class HtmlPageParser {

    private HtmlPageParser() {
    }

    public static Document parseWebPage(String url) throws IOException {
        return Jsoup.connect(url).get();
    }
}
