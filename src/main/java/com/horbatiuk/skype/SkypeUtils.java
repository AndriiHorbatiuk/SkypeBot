package com.horbatiuk.skype;


import com.samczsun.skype4j.Skype;
import com.samczsun.skype4j.SkypeBuilder;
import com.samczsun.skype4j.Visibility;
import com.samczsun.skype4j.chat.Chat;
import com.samczsun.skype4j.exceptions.*;
import com.samczsun.skype4j.formatting.Message;
import com.samczsun.skype4j.formatting.Text;
import com.samczsun.skype4j.participants.info.Contact;

public class SkypeUtils {

    public static Skype authorisationToSkype() {
        return new SkypeBuilder(SkypeConstants.USERNAME, SkypeConstants.PASSWORD).withAllResources().withExceptionHandler((errorSource, throwable, willShutdown) -> {
            System.out.println("Error: " + errorSource + " " + throwable + " " + willShutdown);
        }).build();
    }

    public static Skype skypeLogin(Skype authorizedSkype) throws ConnectionException, NotParticipatingException, InvalidCredentialsException {
        authorizedSkype.login();
        authorizedSkype.subscribe();
        System.out.println("loged in");
        return authorizedSkype;
    }

    public static Skype sendMessageToUser(Skype logedInSkype, String userName, String message) throws ConnectionException, ChatNotFoundException, InvalidCredentialsException {
        Skype skype = logedInSkype;
        Contact contact = skype.getOrLoadContact(userName);
            Chat chat = contact.getPrivateConversation();
            chat.sendMessage(Message.create().with(Text.plain(message)));
        System.out.println("message sent");
        return skype;
    }

    public static void sendMessageToChat(Chat chat, String msg) throws ConnectionException {
        chat.sendMessage(msg);
    }

    public static Skype addContactToContactList(Skype logedInSkype, String userName) throws ConnectionException, NotParticipatingException, InvalidCredentialsException, NoSuchContactException {
        Skype skype = logedInSkype;
        Contact contact = skype.getOrLoadContact(SkypeConstants.SKYPEUSER);
        if(!contact.isAuthorized()){
            contact.authorize();
        }
        contact.sendRequest("Hello!");
        return skype;
    }

}

