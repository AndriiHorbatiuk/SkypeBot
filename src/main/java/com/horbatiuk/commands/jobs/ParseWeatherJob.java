package com.horbatiuk.commands.jobs;

import com.horbatiuk.htmlParser.HtmlPageParser;
import com.horbatiuk.htmlParser.WeatherParser;
import com.horbatiuk.htmlParser.weatherSites.ParserGismeteo;
import com.horbatiuk.htmlParser.weatherSites.ParserSinoptik;
import com.horbatiuk.skype.SkypeUtils;
import com.samczsun.skype4j.chat.Chat;
import com.samczsun.skype4j.exceptions.*;
import org.jsoup.nodes.Document;

import java.io.IOException;

/**
 * Created by Andrey on 09.09.2016.
 */
public final class ParseWeatherJob implements Jobable {

    @Override
    public void doJob(Chat chat) throws ConnectionException, NoSuchContactException, ChatNotFoundException, InvalidCredentialsException, InterruptedException, IOException {

        Document parsePage = null;
        //Sinoptik
        parsePage = HtmlPageParser.parseWebPage(ParserSinoptik.WEBSITE);
        ParserSinoptik.getInstance.setCurrentTemp(WeatherParser.parseCurrentTemperature(parsePage, ParserSinoptik.getInstance));
        ParserSinoptik.getInstance.setMinTemp(WeatherParser.parseMinTemperature(parsePage, ParserSinoptik.getInstance));
        ParserSinoptik.getInstance.setMaxTemp(WeatherParser.parseMaxTemperature(parsePage, ParserSinoptik.getInstance));

        //Gismeteo
        parsePage = HtmlPageParser.parseWebPage(ParserGismeteo.WEBSITE);
        ParserGismeteo.getInstance.setMinTemp(WeatherParser.parseMinTemperature(parsePage, ParserGismeteo.getInstance));
        ParserGismeteo.getInstance.setMaxTemp(WeatherParser.parseMaxTemperature(parsePage, ParserGismeteo.getInstance));
        ParserGismeteo.getInstance.setCloudy(WeatherParser.parseCloudy(parsePage, ParserGismeteo.getInstance).split(" "));

        SkypeUtils.sendMessageToChat(chat, ParserSinoptik.getInstance.getF().toString() + "\n\n" + ParserGismeteo.getInstance.getF().toString());

    }
}
