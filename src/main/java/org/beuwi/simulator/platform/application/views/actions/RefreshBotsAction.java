package org.beuwi.simulator.platform.application.views.actions;

import org.beuwi.simulator.managers.BotManager;
import org.beuwi.simulator.managers.FileManager;
import org.beuwi.simulator.managers.LogManager;
import org.beuwi.simulator.platform.application.views.parts.ActiveAreaPart;
import org.beuwi.simulator.platform.ui.components.IListView;
import org.beuwi.simulator.platform.ui.components.ILogView;

import java.io.File;

public class RefreshBotsAction
{
    private static IListView listView;

    public static void initialize()
    {
        listView = ActiveAreaPart.BotsTab.getComponent();
    }

    public static void update()
    {
        listView.getItems().clear();

        BotManager.data.clear();
        LogManager.data.clear();

        File[] folders = FileManager.BOTS_FOLDER.listFiles(File::isDirectory);

        for (File folder : folders)
        {
            String name = folder.getName();

            AddExplorerBotAction.update(name);

            LogManager.data.put(name, new ILogView(name));
        }
    }
}