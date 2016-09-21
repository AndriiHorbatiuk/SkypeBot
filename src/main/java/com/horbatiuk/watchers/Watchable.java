package com.horbatiuk.watchers;

import com.horbatiuk.watchers.watchingData.iWatching;
import com.samczsun.skype4j.exceptions.ChatNotFoundException;
import com.samczsun.skype4j.exceptions.ConnectionException;
import com.samczsun.skype4j.exceptions.InvalidCredentialsException;
import org.jsoup.nodes.Document;

import java.util.Formatter;

/**
 * Created by Andrey on 16.09.2016.
 */
public interface Watchable {
    public String getNewData(Document page);
    public boolean isNewDataEqualsOldData(String newData);
    public void rewriteData(String newData);
    public void sendMessage(String msg) throws ConnectionException, InvalidCredentialsException, ChatNotFoundException;
}
