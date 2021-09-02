package org.beuwi.msgbots.platform.app.view.actions;

import org.beuwi.msgbots.base.impl.Executor;

public class ReloadAllBotsAction implements Executor {
    private static ReloadAllBotsAction instance = null;
    public void execute() {
        // ScriptManager.initAll(true);
    }
    public static ReloadAllBotsAction getInstance() {
        if (instance == null) {
            instance = new ReloadAllBotsAction();
        }
        return instance;
    }
}
