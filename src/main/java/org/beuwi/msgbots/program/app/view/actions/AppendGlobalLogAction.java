package org.beuwi.msgbots.program.app.view.actions;

import org.beuwi.msgbots.program.app.impl.Executor;
import org.beuwi.msgbots.program.app.view.tabs.GlobalLogTab;
import org.beuwi.msgbots.program.gui.control.LogItem;
import org.beuwi.msgbots.program.gui.control.LogView;

public class AppendGlobalLogAction implements Executor {
    private static AppendGlobalLogAction instance = null;

    private final LogView control = GlobalLogTab.getInstance().getLogView();

    public void execute(LogItem item) {
        control.getItems().add(item);
    }

    public static AppendGlobalLogAction getInstance() {
        if (instance == null) {
            instance = new AppendGlobalLogAction();
        }
        return instance;
    }
}
