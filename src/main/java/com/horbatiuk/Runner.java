package com.horbatiuk;



import com.horbatiuk.commands.SkypeCommandsData;
import com.horbatiuk.scheduller.Scheduler;
import com.horbatiuk.scheduller.schedullerTasks.WeatherSchedullerTask;
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
        Scheduler.setScheduller(Scheduler.setTimeForExecute(), WeatherSchedullerTask.getInstance);
//        Thread watching = new Thread(new StartWatching());
//        watching.start();

/*        while (true) {
            Document parsePage = null;
            Thread sinoptikThread = new Thread(parserSinoptik,"sinoptikThread");
            sinoptikThread.start();
            Thread gismeteoThread = new Thread(parserGismeteo,"gismeteoThread");
            gismeteoThread.start();
//                //Sinoptik
//                parsePage = WeatherParser.parseWebPage(ParserSinoptik.WEBSITE);
//                parserSinoptik.setCurrentTemp(WeatherParser.parseCurrentTemperature(parsePage, parserSinoptik));
//                parserSinoptik.setMinTemp(WeatherParser.parseMinTemperature(parsePage, parserSinoptik));
//                parserSinoptik.setMaxTemp(WeatherParser.parseMaxTemperature(parsePage, parserSinoptik));
//
//                //Gismeteo
//                parsePage = WeatherParser.parseWebPage(ParserGismeteo.WEBSITE);
//                parserGismeteo.setMinTemp(WeatherParser.parseMinTemperature(parsePage, parserGismeteo));
//                parserGismeteo.setMaxTemp(WeatherParser.parseMaxTemperature(parsePage, parserGismeteo));
//                parserGismeteo.setCloudy(WeatherParser.parseCloudy(parsePage,parserGismeteo).split(" "));

            //Send message
            try {

                SkypeUtils.addContactToContactList(skype, SkypeConstants.SKYPEUSER);
                SkypeUtils.sendMessageToUser(skype, SkypeConstants.SKYPEUSER, parserSinoptik.getF().toString()+ "\n\n" + parserGismeteo.getF().toString());
            } catch (ConnectionException e) {
                e.printStackTrace();
            } catch (NotParticipatingException e) {
                e.printStackTrace();
            } catch (InvalidCredentialsException e) {
                e.printStackTrace();
            } catch (ChatNotFoundException e) {
                e.printStackTrace();
            } catch (NoSuchContactException e) {
                e.printStackTrace();
            }
            long finish = System.currentTimeMillis();
            finish = finish - start;
        }*/
    }
}
