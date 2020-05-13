package org.beuwi.simulator.platform.application.views.actions;

import org.beuwi.simulator.managers.FileManager;

public class DeleteBotAction
{
    public static void update(String name)
    {
        FileManager.remove(FileManager.getBotFolder(name));
    }
}
