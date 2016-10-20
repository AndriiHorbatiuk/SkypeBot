package com.horbatiuk;


import com.horbatiuk.commands.SkypeCommandsData;
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
    }
}
