package org.beuwi.simulator.platform.application.views.actions;

import javafx.scene.control.ListView;
import org.beuwi.simulator.managers.FileManager;
import org.beuwi.simulator.platform.application.views.parts.ActiveAreaPart;

import java.io.File;

public class RefreshExplorerAction
{	private static ListView listView;

    public static void initialize()
    {
        listView = (ListView) ActiveAreaPart.getNameSpace().get("lsvExplorerArea");
    }

    public static void update()
    {
        listView.getItems().clear();

        File[] folders = FileManager.BOTS_FOLDER.listFiles(File::isDirectory);

        for (File folder : folders)
        {
            AddExplorerItemAction.update(folder.getName());
        }
    }
}
