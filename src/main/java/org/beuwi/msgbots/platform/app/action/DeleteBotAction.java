package org.beuwi.msgbots.platform.app.action;

import org.beuwi.msgbots.manager.FileManager;
import org.beuwi.msgbots.platform.app.impl.Action;
import org.beuwi.msgbots.platform.app.utils.FileUtils;

public class DeleteBotAction implements Action
{
    @Override
    public void init()
    {

    }

    public static void execute(String name)
    {
        FileUtils.remove(FileManager.getBotFolder(name));
    }

    @Override
    public String getName()
    {
        return "delete.bot.action";
    }
}
