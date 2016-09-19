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
        if (data.equals("")) {
            LinkedList<String> listOfMsgs = SkypeUtils.getListOfMessagesWithWord(SkypeUtils.skypeLogin(SkypeUtils.authorisationToSkype()),
                    "http://chicco.com.ua");
            data = listOfMsgs.getLast();
        }
        return data;
    }

    public void setData(String data) {
        this.data = data;
        try {
            SkypeUtils.sendMessageToChat(SkypeUtils.getChatFromUserName(SkypeConstants.USER_TO_SEND_NOTIFICATIONS),"Запоминаю url баннера: http://www.chicco.com.ua" + data);
        } catch (ConnectionException e) {
            e.printStackTrace();
        } catch (ChatNotFoundException e) {
            e.printStackTrace();
        }
    }

    public String getMsg() {
        return "Ссылка на баннер на сайте Chicco изменилась на: http://www.chicco.com.ua" + data;
    }
}
