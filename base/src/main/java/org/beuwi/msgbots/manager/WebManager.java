package org.beuwi.msgbots.manager;

import org.beuwi.msgbots.base.Manager;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class WebManager implements Manager {
    public static String getText(String url) {
        try {
            URLConnection connection = new URL(url).openConnection();

            connection.setRequestProperty("User-Agent", "Mozilla/5.0");
            connection.setConnectTimeout(5000);
            connection.setUseCaches(false);

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String text = reader.readLine();
            String line = "";

            while ((line = reader.readLine()) != null) {
                text += "\n" + line;
            }

            reader.close();
            // connection.time;

            return text;
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
