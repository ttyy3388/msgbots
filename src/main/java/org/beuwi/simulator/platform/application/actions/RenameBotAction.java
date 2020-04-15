package org.beuwi.simulator.platform.application.actions;

import org.beuwi.simulator.managers.FileManager;
import org.beuwi.simulator.platform.application.views.dialogs.ExistsBotIDialog;

import java.io.File;

public class RenameBotAction
{
    public static void update(String name, String newName)
    {
        File before = FileManager.getBotFolder(name);
        File after  = FileManager.getBotFolder(newName);

        if (after.exists())
        {
            new ExistsBotIDialog(newName).display();
        }
        else
        {
            before.renameTo(after);
        }
    }
}
