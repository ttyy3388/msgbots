package org.beuwi.msgbots.platform.app.action;

import org.beuwi.msgbots.manager.FileManager;
import org.beuwi.msgbots.platform.app.impl.Action;
import org.beuwi.msgbots.platform.app.view.actions.AddToastMessageAction;
import org.beuwi.msgbots.platform.gui.enums.ToastType;

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
        if (after.exists())
        {
            AddToastMessageAction.execute(ToastType.ERROR, "Rename bot error", "Bot " + after.getName() + " : already exists");
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
