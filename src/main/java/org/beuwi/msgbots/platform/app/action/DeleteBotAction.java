package org.beuwi.msgbots.platform.app.action;

import org.beuwi.msgbots.manager.FileManager;
import org.beuwi.msgbots.platform.app.impl.Action;

public class DeleteBotAction implements Action
{
    @Override
    public void init()
    {

    }

    public static void execute(String name)
    {
        FileManager.remove(FileManager.getBotFolder(name));
    }

    @Override
    public String getName()
    {
        return "delete.bot.action";
    }
}
