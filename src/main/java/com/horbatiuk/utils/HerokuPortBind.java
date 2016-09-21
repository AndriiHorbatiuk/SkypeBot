package com.horbatiuk.utils;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Andrey on 16.09.2016.
 */
public final class HerokuPortBind{

    public static HerokuPortBind herokuPortBind = new HerokuPortBind();

    private HerokuPortBind(){}

    public static void bindPort() {
        String host = "0.0.0.0";
        String port = System.getenv("PORT");
        ServerSocket ss = null; // создаем сокет сервера и привязываем его к вышеуказанному порту
        try {
            ss = new ServerSocket(Integer.parseInt(port));
            Socket socket = ss.accept();
            System.out.println("Port binded.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
