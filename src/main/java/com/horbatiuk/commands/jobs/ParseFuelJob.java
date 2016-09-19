package com.horbatiuk.commands.jobs;

import com.horbatiuk.htmlParser.FuelParserMethods;
import com.horbatiuk.htmlParser.HtmlPageParser;
import com.horbatiuk.htmlParser.fuelSites.ParserMinfin;
import com.horbatiuk.htmlParser.fuelSites.ParserOKKO;
import com.horbatiuk.skype.SkypeUtils;
import com.samczsun.skype4j.chat.Chat;
import com.samczsun.skype4j.exceptions.ChatNotFoundException;
import com.samczsun.skype4j.exceptions.ConnectionException;
import com.samczsun.skype4j.exceptions.InvalidCredentialsException;
import com.samczsun.skype4j.exceptions.NoSuchContactException;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;

/**
 * Created by Andrey on 16.09.2016.
 */
public final class ParseFuelJob implements Jobable {

    public final static ParseFuelJob getInstance = new ParseFuelJob();

    private ParseFuelJob() {
    }

    @Override
    public void doJob(Chat chat) throws ConnectionException, NoSuchContactException, ChatNotFoundException, InvalidCredentialsException, InterruptedException, IOException {
        //Minfin
        Document page = HtmlPageParser.parseWebPage(ParserMinfin.WEBSITE);
        Element table = FuelParserMethods.getFuelTable(page);
        ParserMinfin.getInstance.setPrices(FuelParserMethods.getDataFromTable(FuelParserMethods.getTableRows(table)));
        ParserMinfin.getInstance.setDateAndCity(FuelParserMethods.getDateAndCityFromTable(table));

        //OKKO

        page = HtmlPageParser.parseWebPage(ParserOKKO.WEBSITE);
        ParserOKKO.getInstance.setPrice(FuelParserMethods.getStringWithPricesOKKO(page));

        SkypeUtils.sendMessageToChat(chat, ParserOKKO.getInstance.getF().toString() + "\n\n" + ParserMinfin.getInstance.getF().toString());
    }
}
