package com.horbatiuk.utils;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Timer;
import java.util.TimerTask;

public final class Heroku5MinutesPing implements Runnable {

//    public static Heroku5MinutesPing heroku5MinutesPing = new Heroku5MinutesPing();

    public Heroku5MinutesPing() {
    }

    public void run() {
        String urlString = "http://fierce-everglades-71952.herokuapp.com";
        URL url = null;
        while (true) {
            try {
                Thread.sleep(60000);
                url = new URL(urlString);
                URLConnection conn = url.openConnection();
                InputStream is = conn.getInputStream();
                is.close();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                System.out.println("Service was pinged by SkypeBot.");;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

