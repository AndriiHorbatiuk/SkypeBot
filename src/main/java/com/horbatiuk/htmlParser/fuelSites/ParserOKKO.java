package com.horbatiuk.htmlParser.fuelSites;

import java.util.Formatter;

/**
 * Created by Andrey on 16.09.2016.
 */
public final class ParserOKKO {

    public static final ParserOKKO getInstance = new ParserOKKO();

    public static final String WEBSITE = "https://www.okko.ua/ru/fuels";
    public static final String pricePath = "#element_informer_2 > div.banner-all > ul";

    private Formatter f = new Formatter();

    private String[] price;

    private ParserOKKO() {
    }

    public void setPrice(String[] price) {
        this.price = price;
    }

    public Formatter getF() {
        return f.format("Цена на бензин с сайта OKKO:\nA95 pulls: %s\nA95 euro: %s\nA92euro: %s\nDeisel pulls: %s\nDiesel: %s\nGaz: %s",
                price[0], price[1], price[2], price[3], price[4], price[5]);
    }
}
