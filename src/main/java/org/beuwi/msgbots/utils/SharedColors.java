package org.beuwi.msgbots.utils;

import javafx.scene.paint.Color;

import org.beuwi.msgbots.base.Dfile;
import org.beuwi.msgbots.base.JObject;
import org.beuwi.msgbots.setting.GlobalSettings;

public class SharedColors {
    private static final Dfile dfile = SharedValues.getDfile("dfile.colorMap");
    private static final String theme = GlobalSettings.getString("program.colorTheme");
    private static final JObject json = new JObject(dfile.toResource());

    public static Color getColor(String key) {
        return Color.valueOf(String.valueOf(json.getMap(theme).get(key)));
    }

    /* public static Color getColor(String theme, String key) {
    } */
}
