package org.beuwi.msgbots.platform.app.view.actions;

import org.beuwi.msgbots.platform.app.impl.Action;
import org.beuwi.msgbots.platform.util.SharedValues;

import java.io.File;

public class UpdateBotsPathAction implements Action {
    @Override
    public void init() {

    }
    public static void execute(String path){
        if (path == null) {
            throw new NullPointerException("Bots path must not be null");
        }
        SharedValues.setValue("BOT_FOLDER_PATH", path);
        SharedValues.setValue("BOT_FOLDER_FILE", new File(path));

        RefreshBotListAction.execute();
    }

    @Override
    public String getName() {
        return null;
    }
}