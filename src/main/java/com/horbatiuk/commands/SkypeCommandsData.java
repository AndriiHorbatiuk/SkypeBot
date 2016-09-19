package com.horbatiuk.commands;

import com.horbatiuk.commands.jobs.Jobable;
import com.horbatiuk.commands.jobs.ParseFuelJob;
import com.horbatiuk.commands.jobs.ParseWeatherJob;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Andrey on 09.09.2016.
 */
public final class SkypeCommandsData<T extends Jobable>{

    private Map<String, T> listOfCommands = new HashMap<>();

    public static SkypeCommandsData getInstance = new SkypeCommandsData();

    private SkypeCommandsData() {
    }

    public Map<String, T> getListOfCommands() {
        return listOfCommands;
    }

    public static void initBotCommands(){
        getInstance.getListOfCommands().put("!погода", new ParseWeatherJob());
        getInstance.getListOfCommands().put("!бенз", new ParseFuelJob());
    }

}
