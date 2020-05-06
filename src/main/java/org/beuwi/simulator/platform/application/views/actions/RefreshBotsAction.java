package org.beuwi.simulator.platform.application.views.actions;

import org.beuwi.simulator.managers.BotManager;
import org.beuwi.simulator.managers.FileManager;
import org.beuwi.simulator.managers.LogManager;
import org.beuwi.simulator.platform.application.views.parts.ActiveAreaPart;
import org.beuwi.simulator.platform.ui.components.IListView;

import java.io.File;

public class RefreshBotsAction
{
    private static IListView listView;

    public static void initialize()
    {
        listView = ActiveAreaPart.ExplorerTabPart.getComponent();
    }

    public static void update()
    {
        listView.getItems().clear();

        BotManager.data.clear();
        LogManager.data.clear();

        File[] folders = FileManager.BOTS_FOLDER.listFiles(File::isDirectory);

        for (File folder : folders)
        {
            AddExplorerBotAction.update(folder.getName());
            AddBotLogViewAction.update(folder.getName());
        }
    }
}