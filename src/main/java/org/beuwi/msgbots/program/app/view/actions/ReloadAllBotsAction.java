package org.beuwi.msgbots.program.app.view.actions;

import org.beuwi.msgbots.manager.ScriptManager;
import org.beuwi.msgbots.program.app.impl.Executor;

public class ReloadAllBotsAction implements Executor {
    private static ReloadAllBotsAction instance = null;
    public void execute() {
        ScriptManager.initAll(true);
    }
    public static ReloadAllBotsAction getInstance() {
        if (instance == null) {
            instance = new ReloadAllBotsAction();
        }
        return instance;
    }
}
