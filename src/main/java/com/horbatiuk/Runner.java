package com.horbatiuk;


import com.horbatiuk.commands.SkypeCommandsData;
import com.horbatiuk.scheduller.Scheduler;
import com.horbatiuk.scheduller.schedulerTasks.ChiccoSchedulerTask;
import com.horbatiuk.scheduller.schedulerTasks.WeatherSchedulerTask;
import com.horbatiuk.skype.SkypeStart;
import com.horbatiuk.utils.Heroku5MinutesPing;
import com.horbatiuk.utils.HerokuPortBind;


import java.io.IOException;

public class Runner {
    public static void main(String[] args) throws InterruptedException, IOException {

        HerokuPortBind.bindPort();
        new SkypeStart().run();
        SkypeCommandsData.initBotCommands();
        Thread herokuPinger = new Thread(new Heroku5MinutesPing());
        herokuPinger.start();
//        Scheduler.setScheduller(Scheduler.setTimeForExecute(), WeatherSchedulerTask.getInstance);
//        Scheduler.setScheduller(Scheduler.setTimeForExecute(), ChiccoSchedulerTask.getInstance);
//        ChiccoSchedulerTask.getInstance.run();

        //Send message
//        try {
//            Skype skype = SkypeUtils.skypeLogin(SkypeUtils.authorisationToSkype());
//            SkypeUtils.sendMessageToChat(SkypeUtils.getChatFromUserName(SkypeConstants.USER_TO_SEND_NOTIFICATIONS), "Get chat id: " +
//                    SkypeUtils.getChatFromUserName(SkypeConstants.USER_TO_SEND_NOTIFICATIONS).getIdentity());
//        } catch (ConnectionException e) {
//            e.printStackTrace();
//        } catch (ChatNotFoundException e) {
//            e.printStackTrace();
//        }
    }
}
