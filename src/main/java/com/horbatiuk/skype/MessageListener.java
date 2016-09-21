package com.horbatiuk.skype;

import com.horbatiuk.commands.jobs.Jobable;
import com.horbatiuk.commands.SkypeCommandsData;
import com.samczsun.skype4j.chat.Chat;
import com.samczsun.skype4j.events.EventHandler;
import com.samczsun.skype4j.events.Listener;
import com.samczsun.skype4j.events.chat.message.MessageReceivedEvent;
import com.samczsun.skype4j.exceptions.ChatNotFoundException;
import com.samczsun.skype4j.exceptions.ConnectionException;
import com.samczsun.skype4j.exceptions.InvalidCredentialsException;
import com.samczsun.skype4j.exceptions.NoSuchContactException;

import java.io.IOException;

/**
 * Created by Andrey on 09.09.2016.
 */
public final class MessageListener implements Listener {

    @EventHandler
    public void getMessageFromChat(MessageReceivedEvent mre) throws ConnectionException, NoSuchContactException, InvalidCredentialsException, ChatNotFoundException, InterruptedException, IOException {
        String msg = mre.getMessage().getContent().asPlaintext();
        if (msg.contains("!")) {
            if(SkypeCommandsData.getInstance.getListOfCommands().get(msg) != null){
                Chat chat = mre.getChat();
                Jobable jobable = (Jobable)SkypeCommandsData.getInstance.getListOfCommands().get(msg);
                        jobable.doJob(chat);
            }
        }
    }
}
