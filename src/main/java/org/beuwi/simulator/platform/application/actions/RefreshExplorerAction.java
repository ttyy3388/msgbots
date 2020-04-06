package org.beuwi.simulator.platform.application.actions;

import org.beuwi.simulator.managers.FileManager;

import java.io.File;

public class RefreshExplorerAction
{
    public static void update()
    {
        // data.clear();

        // ClearExplorerItemsAction.update();

        File[] folders = FileManager.BOTS_FOLDER.listFiles(File::isDirectory);

        for (File folder : folders)
        {
            AddExplorerItemAction.update(folder.getName());
        }
    }
}
