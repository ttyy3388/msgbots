package org.beuwi.msgbots.platform.app.action;

import org.beuwi.msgbots.openapi.JSONObject;
import org.beuwi.msgbots.platform.app.impl.Action;
import org.beuwi.msgbots.platform.app.view.actions.DisplayErrorDialogAction;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/* public class GetWebTextAction  implements Action {
    @Override
    public void init() {
    }

    public static String execute(String url) {
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
            DisplayErrorDialogAction.execute(e);
        }

        return null;
    }

    @Override
    public String getName() {
        return "get.web.text.action";
    }
} */