package com.horbatiuk.scheduller.schedullerTasks;

import com.horbatiuk.htmlParser.HtmlPageParser;
import com.horbatiuk.watchers.ChiccoWatcher;
import com.horbatiuk.watchers.watchingData.ChiccoWatcherData;
import com.samczsun.skype4j.exceptions.ChatNotFoundException;
import com.samczsun.skype4j.exceptions.ConnectionException;
import com.samczsun.skype4j.exceptions.InvalidCredentialsException;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

/**
 * Created by Andrey on 16.09.2016.
 */
public class ChiccoSchedullerTask extends TimerTask {

    public final static ChiccoSchedullerTask getInstance = new ChiccoSchedullerTask();

    private ChiccoSchedullerTask() {
    }

    @Override
    public void run() {
        Document page = null;
        try {
            page = HtmlPageParser.parseWebPage(ChiccoWatcherData.getInstance.WEBSITE);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String newData = ChiccoWatcher.getInstance.getNewData(page);
        boolean equalsDatas = ChiccoWatcher.getInstance.isNewDataEqualsOldData(newData);
        if (!equalsDatas) {
            ChiccoWatcher.getInstance.rewriteData(newData);
            try {
                ChiccoWatcher.getInstance.sendMessage(ChiccoWatcherData.getInstance.getMsg());
            } catch (ConnectionException e) {
                e.printStackTrace();
            } catch (InvalidCredentialsException e) {
                e.printStackTrace();
            } catch (ChatNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
