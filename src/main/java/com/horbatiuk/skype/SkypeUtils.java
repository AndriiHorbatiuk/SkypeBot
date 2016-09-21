package com.horbatiuk.skype;


import com.samczsun.skype4j.Skype;
import com.samczsun.skype4j.SkypeBuilder;
import com.samczsun.skype4j.chat.Chat;
import com.samczsun.skype4j.chat.messages.ChatMessage;
import com.samczsun.skype4j.exceptions.*;
import com.samczsun.skype4j.formatting.Message;
import com.samczsun.skype4j.formatting.Text;
import com.samczsun.skype4j.participants.info.Contact;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class SkypeUtils {

    public static Skype authorisationToSkype() {
        return new SkypeBuilder(SkypeConstants.USERNAME, SkypeConstants.PASSWORD).withAllResources().withExceptionHandler((errorSource, throwable, willShutdown) -> {
            System.out.println("Error: " + errorSource + " " + throwable + " " + willShutdown);
        }).build();
    }

    public static Skype skypeLogin(Skype authorizedSkype) {
        try {
            authorizedSkype.login();
            authorizedSkype.subscribe();
        } catch (InvalidCredentialsException e) {
            e.printStackTrace();
        } catch (ConnectionException e) {
            e.printStackTrace();
        } catch (NotParticipatingException e) {
            e.printStackTrace();
        }
        System.out.println("Loged in to Skype");
        return authorizedSkype;
    }

    public static void sendMessageToUser(Skype logedInSkype, String userName, String message) {
        Contact contact = null;
        try {
            contact = logedInSkype.getOrLoadContact(userName);
            Chat chat = contact.getPrivateConversation();
            chat.sendMessage(Message.create().with(Text.plain(message)));
            logedInSkype.logout();
        } catch (ConnectionException e) {
            e.printStackTrace();
        } catch (ChatNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("Private message sent");
    }

    public static void sendMessageToChat(Chat chat, String msg) throws ConnectionException {
        chat.sendMessage(msg);
    }

    public static Skype addContactToContactList(Skype logedInSkype, String userName) {
        Contact contact = null;
        try {
            contact = logedInSkype.getOrLoadContact(SkypeConstants.SKYPEUSER);
            if (!contact.isAuthorized()) {
                contact.authorize();
            }
            contact.sendRequest("Hello!");
            return logedInSkype;
        } catch (ConnectionException e) {
            e.printStackTrace();
        } catch (NoSuchContactException e) {
            e.printStackTrace();
        }
        return logedInSkype;
    }

    public static Chat getChatFromUserName(String userName) throws ConnectionException, ChatNotFoundException {
        Skype skype = skypeLogin(authorisationToSkype());
        return skype.getOrLoadContact(userName).getPrivateConversation();
    }
}

