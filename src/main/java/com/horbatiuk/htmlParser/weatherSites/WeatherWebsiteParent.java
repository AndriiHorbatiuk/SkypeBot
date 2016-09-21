package com.horbatiuk.htmlParser.weatherSites;

/**
 * Created by Andrey on 08.09.2016.
 */
public class WeatherWebsiteParent {
    private String minTemp;
    private String maxTemp;
    private String currentTemp;
    private String[] cloudy;

    public String getMinTemp() {
        return minTemp;
    }

    public void setMinTemp(String minTemp) {
        this.minTemp = minTemp;
    }

    public String getMaxTemp() {
        return maxTemp;
    }

    public void setMaxTemp(String maxTemp) {
        this.maxTemp = maxTemp;
    }

    public String getCurrentTemp() {
        return currentTemp;
    }

    public void setCurrentTemp(String currentTemp) {
        this.currentTemp = currentTemp;
    }

    public String[] getCloudy() {
        return cloudy;
    }

    public void setCloudy(String[] cloudy) {
        this.cloudy = cloudy;
    }
}
