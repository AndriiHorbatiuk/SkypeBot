package com.horbatiuk.skype;

import com.samczsun.skype4j.Skype;
import com.samczsun.skype4j.Visibility;
import com.samczsun.skype4j.exceptions.ConnectionException;

public class SkypeStart {

    public static Skype skype;

    public void run() {
        try {
            SkypeStart.skype = SkypeUtils.skypeLogin(SkypeUtils.authorisationToSkype());
            skype.loadAllContacts();
            skype.setVisibility(Visibility.ONLINE);
            skype.getEventDispatcher().registerListener(new MessageListener());
        } catch (ConnectionException e) {
            e.printStackTrace();
        }
    }
}
