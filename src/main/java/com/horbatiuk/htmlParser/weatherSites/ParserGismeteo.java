package com.horbatiuk.htmlParser.weatherSites;

import com.horbatiuk.htmlParser.HtmlPageParser;
import com.horbatiuk.htmlParser.WeatherParser;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.Formatter;

/**
 * Created by Andrey on 08.09.2016.
 */
public class ParserGismeteo extends WeatherWebsiteParent implements WeatherParsable, Runnable {

    public final static ParserGismeteo getInstance = new ParserGismeteo();

    private ParserGismeteo() {
    }

    public static final String WEBSITE = "https://www.gismeteo.ua/weather-kyiv-4944/";

    private Formatter f = new Formatter();

    public Formatter getF() {
        return f.format("Прогноз с сайта gismeteo.ua\nМинимальная температура сегодня: %s, максимальная: %s." +
                "\nОсадки:\nНочью: %s, Утром: %s, Днем: %s, Вечером: %s",
                getMinTemp(), getMaxTemp(), getCloudy()[0], getCloudy()[1], getCloudy()[2], getCloudy()[3]);
    }

    @Override
    public String minTempPath() {
        return "#tab_wdaily1 > div > span.value.m_temp.c";
    }

    @Override
    public String maxTempPath() {
        return "#tab_wdaily1 > div > em > span.value.m_temp.c";
    }

    @Override
    public String currentTempPath() {
        return "#weather > div.fcontent > div.section.higher > div.temp > dd.value.m_temp.c";
    }

    @Override
    public String cloudyPath() {
        return "#tbwdaily1 > tr.wrow > td.cltext";
    }

    @Override
    public void run() {
        Document parsePage = null;
        try {
            parsePage = HtmlPageParser.parseWebPage(ParserGismeteo.WEBSITE);
            this.setMinTemp(WeatherParser.parseMinTemperature(parsePage, this));
            this.setMaxTemp(WeatherParser.parseMaxTemperature(parsePage, this));
            this.setCloudy(WeatherParser.parseCloudy(parsePage,this).split(" "));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
