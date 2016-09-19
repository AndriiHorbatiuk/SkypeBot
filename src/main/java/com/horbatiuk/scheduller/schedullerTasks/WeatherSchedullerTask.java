package com.horbatiuk.scheduller.schedullerTasks;

import com.horbatiuk.commands.jobs.ParseWeatherJob;
import com.horbatiuk.skype.SkypeConstants;
import com.horbatiuk.skype.SkypeUtils;
import com.samczsun.skype4j.exceptions.ChatNotFoundException;
import com.samczsun.skype4j.exceptions.ConnectionException;
import com.samczsun.skype4j.exceptions.InvalidCredentialsException;
import com.samczsun.skype4j.exceptions.NoSuchContactException;

import java.io.IOException;
import java.util.TimerTask;

/**
 * Created by Andrey on 14.09.2016.
 */
public final class WeatherSchedullerTask extends TimerTask {

    public static WeatherSchedullerTask getInstance = new WeatherSchedullerTask();

    private WeatherSchedullerTask() {
    }

    @Override
    public void run() {

        try {
            ParseWeatherJob.getInstance.doJob(SkypeUtils.getChatFromUserName(SkypeConstants.USER_TO_SEND_NOTIFICATIONS));
        } catch (ConnectionException e) {
            e.printStackTrace();
        } catch (NoSuchContactException e) {
            e.printStackTrace();
        } catch (ChatNotFoundException e) {
            e.printStackTrace();
        } catch (InvalidCredentialsException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
