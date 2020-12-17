package org.beuwi.msgbots.platform.app.view.actions;

import org.beuwi.msgbots.platform.app.impl.Action;
import org.beuwi.msgbots.platform.app.view.tabs.GlobalLogTab;
import org.beuwi.msgbots.platform.gui.control.LogBox;
import org.beuwi.msgbots.platform.gui.control.LogView;

public class AddBotLogBoxAction implements Action
{
    private static LogView control;

    @Override
    public void init()
    {
        control = GlobalLogTab.getComponent();
    }

    public static void execute(LogBox log)
    {
        control.addItem(log);
    }

    public static void execute(String name, LogBox log)
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
