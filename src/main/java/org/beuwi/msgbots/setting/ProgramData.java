package org.beuwi.msgbots.setting;

import org.beuwi.msgbots.manager.FileManager;
import org.beuwi.msgbots.openapi.JsonObject;
import org.beuwi.msgbots.platform.app.view.actions.AddToastMessageAction;
import org.beuwi.msgbots.platform.util.SharedValues;

import java.io.File;

public class ProgramData {
    private static final File file = SharedValues.PROGRAM_DATA_FILE;
    private static JsonObject json = new JsonObject(file);

    static {
        FileManager.link(file, () -> json = new JsonObject(file));
    }

    public static <T> T getData(String address) {
        String[] data = address.split(":");

        String name = data[0];
        String option = data[1];

        // throw new NullPointerException("this address does not exists");

        return (T) json.getMap(name).get(option);
    }

    public static int getInt(String address) {
        return Integer.valueOf("" + getData(address));
    }

    public static String getString(String address) {
        return String.valueOf("" + getData(address));
    }

    public static boolean getBoolean(String address) {
        return Boolean.valueOf("" + getData(address));
    }

    public static <T> void setData(String address, T value) {
        String[] data = address.split(":");

        String name = data[0];
        String option = data[1];

        json.getMap(name).put(option, value);

        try {
            FileManager.save(file, json.toString());
        }
        catch (Exception e) {
            AddToastMessageAction.execute(e);
        }
    }
}
