package com.horbatiuk.htmlParser.fuelSites;

import java.util.Formatter;

/**
 * Created by Andrey on 16.09.2016.
 */
public final class ParserMinfin {

    public static final String WEBSITE = "http://index.minfin.com.ua/fuel/reg/?Киевская";

    public final static int fuelTableNumber = 1;

    private String prices;

    private String dateAndCity;

    public static final ParserMinfin getInstance = new ParserMinfin();

    private ParserMinfin() {
    }

    private Formatter f = new Formatter();

    public Formatter getF() {
        return f.format("Цена на бензин с сайта minfin:\n%s\n%s", dateAndCity, prices);
    }

    public void setPrices(String prices) {
        this.prices = prices;
    }

    public void setDateAndCity(String dateAndCity) {
        this.dateAndCity = dateAndCity;
    }
}
