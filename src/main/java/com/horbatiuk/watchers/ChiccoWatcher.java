package com.horbatiuk.watchers;

import com.horbatiuk.skype.SkypeConstants;
import com.horbatiuk.skype.SkypeUtils;
import com.horbatiuk.watchers.watchingData.ChiccoWatcherData;
import com.samczsun.skype4j.Skype;
import com.samczsun.skype4j.exceptions.ChatNotFoundException;
import com.samczsun.skype4j.exceptions.ConnectionException;
import com.samczsun.skype4j.exceptions.InvalidCredentialsException;
import org.jsoup.nodes.Document;

/**
 * Created by Andrey on 16.09.2016.
 */
public final class ChiccoWatcher implements Watchable {

    public static final ChiccoWatcher getInstance = new ChiccoWatcher();

    private ChiccoWatcher() {
    }

    @Override
    public String getNewData(Document page) {
        return page.select(ChiccoWatcherData.DATA_PATH).attr("src");
    }

    @Override
    public boolean isNewDataEqualsOldData(String newData) {
        return ChiccoWatcherData.getInstance.getData().equals(newData);
    }

    @Override
    public void rewriteData(String newData) {
        ChiccoWatcherData.getInstance.setData(newData);
    }

    @Override
    public void sendMessage(String msg) throws ConnectionException, InvalidCredentialsException, ChatNotFoundException {
        Skype skype = SkypeUtils.skypeLogin(SkypeUtils.authorisationToSkype());
        SkypeUtils.sendMessageToUser(skype, SkypeConstants.USER_TO_SEND_NOTIFICATIONS, ChiccoWatcherData.getInstance.getMsg());
        skype.logout();
    }

}
