package com.horbatiuk.watchers;

import com.horbatiuk.htmlParser.HtmlPageParser;
import com.horbatiuk.watchers.watchingData.ChiccoWatcherData;
import com.samczsun.skype4j.exceptions.ChatNotFoundException;
import com.samczsun.skype4j.exceptions.ConnectionException;
import com.samczsun.skype4j.exceptions.InvalidCredentialsException;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Created by Andrey on 16.09.2016.
 */
public class StartWatching implements Runnable{

    @Override
    public void run() {
        while(true){
            Document page = null;
//            try {
//                TimeUnit.MINUTES.sleep(5);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            try {
                page = HtmlPageParser.parseWebPage(ChiccoWatcherData.getInstance.WEBSITE);
            } catch (IOException e) {
                e.printStackTrace();
            }
            String newData = ChiccoWatcher.getInstance.getNewData(page);
            boolean equalsDatas = ChiccoWatcher.getInstance.isNewDataEqualsOldData(newData);
            if(!equalsDatas){
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
            try {
                TimeUnit.HOURS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
