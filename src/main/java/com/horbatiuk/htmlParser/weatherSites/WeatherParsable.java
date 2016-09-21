package com.horbatiuk.htmlParser.weatherSites;

/**
 * Created by Andrey on 08.09.2016.
 */
public interface WeatherParsable {
    public String minTempPath();
    public String maxTempPath();
    public String currentTempPath();
    public String cloudyPath();
}
