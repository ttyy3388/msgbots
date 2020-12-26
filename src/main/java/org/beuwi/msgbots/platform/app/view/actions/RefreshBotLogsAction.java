package org.beuwi.msgbots.platform.app.view.actions;

import org.beuwi.msgbots.manager.FileManager;
import org.beuwi.msgbots.manager.LogManager;
import org.beuwi.msgbots.platform.app.impl.Action;
import org.beuwi.msgbots.platform.app.view.tabs.GlobalLogTab;
import org.beuwi.msgbots.platform.gui.control.Bot;
import org.beuwi.msgbots.platform.gui.control.BotView;
import org.beuwi.msgbots.platform.gui.control.LogView;

import java.io.File;

public class RefreshBotLogsAction implements Action {
    @Override
    public void init() {

    }

    public static void execute() {

    }

    @Override
    public String getName()
    {
        return "refresh.bot.logs.action";
    }
}
