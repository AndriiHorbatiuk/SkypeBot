package com.horbatiuk.htmlParser.weatherSites;

import com.horbatiuk.htmlParser.HtmlPageParser;
import com.horbatiuk.htmlParser.WeatherParser;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.Formatter;

/**
 * Created by Andrey on 08.09.2016.
 */
public class ParserSinoptik extends WeatherWebsiteParent implements WeatherParsable, Runnable {
    public static final String WEBSITE = "https://sinoptik.ua/погода-киев"; //http://bit.ly/1n9KYYa

    private Formatter f = new Formatter();

    public static final ParserSinoptik getInstance = new ParserSinoptik();

    private ParserSinoptik() {
    }

    public Formatter getF() {
        return f.format("\nПрогноз с сайта sinoptik.ua\nСейчас в Киеве: %s\nМинимальная температура сегодня: %s, максимальная: %s.",
                getCurrentTemp(), getMinTemp(), getMaxTemp());
    }

    @Override
    public String minTempPath() {
        return "#bd1 > div.temperature > div.min > span";
    }

    @Override
    public String maxTempPath() {
        return "#bd1 > div.temperature > div.max > span";
    }

    @Override
    public String currentTempPath() {
        return "#bd1c > div.wMain.clearfix > div.lSide > div.imgBlock > p.today-temp";
    }
//@TODO Доделать вытягивание осадков http://stackoverflow.com/questions/24772828/how-to-parse-html-table-using-jsoup
    @Override
    public String cloudyPath() {
        return "#bd1c > div.wMain.clearfix > div.rSide > table > tbody > tr.img.weatherIcoS > td > div";
    }

    @Override
    public void run() {
        Document parsePage = null;
        try {
            parsePage = HtmlPageParser.parseWebPage(ParserSinoptik.WEBSITE);
            this.setCurrentTemp(WeatherParser.parseCurrentTemperature(parsePage, this));
            this.setMinTemp(WeatherParser.parseMinTemperature(parsePage, this));
            this.setMaxTemp(WeatherParser.parseMaxTemperature(parsePage, this));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
