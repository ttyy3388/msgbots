package org.beuwi.msgbots.platform.app.view.actions;

import org.beuwi.msgbots.platform.app.impl.Action;
import org.beuwi.msgbots.platform.util.SharedValues;
import org.beuwi.msgbots.setting.GlobalSettings;

import java.io.File;

public class UpdateBotsPathAction implements Action {
    @Override
    public void init() {

    }
    public static void execute(){
        SharedValues.BOTS_FOLDER_PATH = GlobalSettings.getString("program:bots_path");
        SharedValues.BOTS_FOLDER_FILE = new File(SharedValues.BOTS_FOLDER_PATH);
        RefreshBotListAction.execute();
    }

    @Override
    public String getName() {
        return null;
    }
}
