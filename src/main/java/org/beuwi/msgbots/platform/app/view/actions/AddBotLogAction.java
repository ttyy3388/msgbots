package org.beuwi.msgbots.platform.app.view.actions;

import org.beuwi.msgbots.manager.LogManager;
import org.beuwi.msgbots.platform.app.impl.Action;
import org.beuwi.msgbots.platform.app.view.tabs.GlobalLogTab;
import org.beuwi.msgbots.platform.gui.control.Log;
import org.beuwi.msgbots.platform.gui.control.LogView;

public class AddBotLogAction implements Action
{
    private static LogView control;

    @Override
    public void init()
    {
        control = GlobalLogTab.getComponent();
    }

    public static void execute(Log log)
    {
        control.addItem(log);
    }

    public static void execute(String name, Log log)
    {
        // LogView logView = LogManager.getView(name);

       //  if (logView != null)
        {
            // logView.addItem(log);
        }

        control.addItem(log);
    }

    @Override
    public String getName()
    {
        return "add.bot.log.action";
    }
}
