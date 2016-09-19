package com.horbatiuk.watchers.watchingData;

import java.io.*;

/**
 * Created by Andrey on 16.09.2016.
 */
public final class ChiccoWatcherData implements iWatching {
    public final String WEBSITE = "http://chicco.com.ua";
    public static final String DATA_PATH = "#flashid > a:nth-child(1) > img";
    public static final ChiccoWatcherData getInstance = new ChiccoWatcherData();
    private String data = "";

    private final String filePath = "src/main/resources/banner_url_chicco.txt";

    private String msg = null;

    private ChiccoWatcherData() {
    }


    public String getData() {
        if (data.equals("")) {
            try (BufferedReader br = new BufferedReader(new FileReader(filePath));) {
                String line;
                if ((line = br.readLine()) != null) {
                    data = line;
                    return data;
                }
                else {
                    return "";
                }

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return data;
    }

    public void setData(String data) {
        this.data = data;
        try (FileWriter fw = new FileWriter(filePath);) {
            fw.write(data);
            fw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getMsg() {
        return "Ссылка на баннер на сайте Chicco изменилась на: http://www.chicco.com.ua" + data;
    }
}
