package org.beuwi.msgbots.setting;

import org.beuwi.msgbots.manager.FileManager;
import org.beuwi.msgbots.openapi.JsonObject;

import java.io.File;

public class ScriptSettings {

    /* public static class Settings extends org.beuwi.msgbots.openapi.Settings
    {
        private final File file;

        public Settings(String name)
        {
            this.file = FileManager.getBotSetting(name);

            try
            {
                this.putAll(new JsonObject(file));
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }

        @Override
        public void apply()
        {
            FileManager.save(file, this.toBeautifyString());
        }
    } */
}
