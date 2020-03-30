package org.beuwi.simulator.platform.application.actions;

import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import org.beuwi.simulator.platform.application.views.parts.SideBarPart;

public class ClearExplorerItemsAction
{
	private static ListView<HBox> listView;

	public static void initialize()
	{
		listView = (ListView) SideBarPart.getNameSpace().get("lsvExplorerPart");
	}

	public static void update()
	{
		listView.getItems().clear();
	}
}