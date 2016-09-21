package com.horbatiuk.htmlParser;

import com.horbatiuk.htmlParser.fuelSites.ParserMinfin;
import com.horbatiuk.htmlParser.fuelSites.ParserOKKO;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Created by Andrey on 16.09.2016.
 */
public final class FuelParserMethods {

    //Minfin

    public static Element getFuelTable(Document page){
        return page.select("table").get(ParserMinfin.fuelTableNumber);
    }

    public static Elements getTableRows(Element table){
        return table.select("tr");
    }

    public static String getDataFromTable(Elements rows){
        StringBuilder sb = new StringBuilder();
        for(Element row : rows){
            sb.append(row.text());
            sb.append("\n");
        }
        return sb.toString();
    }

    public static String getDateAndCityFromTable(Element table){
        return table.select("caption").text();
    }

    //OKKO

    public static String[] getStringWithPricesOKKO(Document page){
       return page.select(ParserOKKO.pricePath).text().split(" ");
    }
}
