package com.horbatiuk.commands.jobs;

import com.samczsun.skype4j.chat.Chat;
import com.samczsun.skype4j.exceptions.ChatNotFoundException;
import com.samczsun.skype4j.exceptions.ConnectionException;
import com.samczsun.skype4j.exceptions.InvalidCredentialsException;
import com.samczsun.skype4j.exceptions.NoSuchContactException;

import java.io.IOException;

/**
 * Created by Andrey on 09.09.2016.
 */
public interface Jobable {
    public void doJob(Chat chat) throws ConnectionException, NoSuchContactException, ChatNotFoundException, InvalidCredentialsException, InterruptedException, IOException;
}
