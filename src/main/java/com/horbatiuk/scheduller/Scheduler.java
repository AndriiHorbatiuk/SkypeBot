package com.horbatiuk.scheduller;

import java.util.Calendar;
import java.util.TimeZone;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;


/**
 * Created by Andrey on 14.09.2016.
 */
public class Scheduler {

    public static <T extends TimerTask, Schedulable> void setScheduller(Calendar today, T task) {
        Timer timer = new Timer();
        timer.schedule(task, today.getTime(), TimeUnit.MILLISECONDS.convert(1, TimeUnit.DAYS)); // 60*60*24*100 = 8640000ms
    }
//@TODO Вынести время в отдельную сущность
    public static Calendar setTimeForExecute() {
        TimeZone timeZone = TimeZone.getTimeZone("Europe/Kiev");
        Calendar today = Calendar.getInstance();
        today.setTimeZone(timeZone);
        today.set(Calendar.HOUR_OF_DAY, 9);
        today.set(Calendar.MINUTE, 0);
        today.set(Calendar.SECOND, 0);
        return today;
    }
}
