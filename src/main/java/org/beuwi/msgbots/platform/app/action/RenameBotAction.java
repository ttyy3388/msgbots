package org.beuwi.msgbots.platform.app.action;

import org.beuwi.msgbots.manager.FileManager;
import org.beuwi.msgbots.platform.app.impl.Action;

import java.io.File;

public class RenameBotAction implements Action
{
    @Override
    public void init()
    {

    }

    public static void execute(String before, String after)
    {
        execute(FileManager.getBotFolder(before), FileManager.getBotFolder(after));
    }

    public static void execute(File before, File after)
    {
        if (before.exists())
        {
            System.out.println("file exists");
        }
        else
        {
            before.renameTo(after);
        }
    }

    @Override
    public String getName()
    {
        return "rename.bot.action";
    }
}
