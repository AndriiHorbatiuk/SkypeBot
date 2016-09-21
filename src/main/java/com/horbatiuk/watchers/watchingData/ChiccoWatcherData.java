package com.horbatiuk.watchers.watchingData;

import com.horbatiuk.skype.SkypeConstants;
import com.horbatiuk.skype.SkypeUtils;
import com.samczsun.skype4j.exceptions.ChatNotFoundException;
import com.samczsun.skype4j.exceptions.ConnectionException;

import java.util.LinkedList;

/**
 * Created by Andrey on 16.09.2016.
 */
public final class ChiccoWatcherData implements iWatching {
    public final String WEBSITE = "http://chicco.com.ua";
    public static final String DATA_PATH = "#flashid > a:nth-child(1) > img";
    public static final ChiccoWatcherData getInstance = new ChiccoWatcherData();
    private String data = "";

    private String msg = null;

    private ChiccoWatcherData() {
    }


    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getMsg() {
        return "Ссылка на баннер на сайте Chicco изменилась на: http://www.chicco.com.ua" + data;
    }
}
