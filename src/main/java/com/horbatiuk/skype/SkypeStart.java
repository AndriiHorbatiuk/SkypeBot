package com.horbatiuk.skype;

import com.horbatiuk.commands.SkypeCommandsData;
import com.samczsun.skype4j.Skype;
import com.samczsun.skype4j.Visibility;
import com.samczsun.skype4j.exceptions.ConnectionException;
import com.samczsun.skype4j.exceptions.InvalidCredentialsException;
import com.samczsun.skype4j.exceptions.NotParticipatingException;

/**
 * Created by Andrey on 09.09.2016.
 */
public class SkypeStart {
    public void run() {
        try {
            Skype skype = SkypeUtils.skypeLogin(SkypeUtils.authorisationToSkype());
            skype.loadAllContacts();
            skype.setVisibility(Visibility.ONLINE);
            skype.getEventDispatcher().registerListener(new MessageListener());
        } catch (ConnectionException e) {
            e.printStackTrace();
        } catch (NotParticipatingException e) {
            e.printStackTrace();
        } catch (InvalidCredentialsException e) {
            e.printStackTrace();
        }

    }
}
