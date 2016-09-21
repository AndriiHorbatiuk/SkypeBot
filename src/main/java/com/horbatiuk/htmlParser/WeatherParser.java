package com.horbatiuk.htmlParser;

import com.horbatiuk.htmlParser.weatherSites.WeatherParsable;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

/**
 * Created by Andrey on 07.09.2016.
 */
public final class WeatherParser {

    public static <T extends WeatherParsable> String parseMinTemperature(Document page, T object) throws IOException {
        return page.select(object.minTempPath()).text();
    }

    public static <T extends WeatherParsable> String parseMaxTemperature(Document page, T object) throws IOException {
        return page.select(object.maxTempPath()).text();
    }

    public static <T extends WeatherParsable> String parseCurrentTemperature(Document page, T object) throws IOException {
        return page.select(object.currentTempPath()).text();
    }

    public static <T extends WeatherParsable> String parseCloudy(Document page, T object) throws IOException {
        return page.select(object.cloudyPath()).text();
    }

    public static <T extends WeatherParsable> String parseCloudySinoptik(Document page, T object) throws IOException {
        return page.select(object.cloudyPath()).attr("title");
    }
}