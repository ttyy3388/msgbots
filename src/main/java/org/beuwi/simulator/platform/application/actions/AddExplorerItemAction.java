package org.beuwi.simulator.platform.application.actions;

import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import org.beuwi.simulator.platform.application.parts.ActiveAreaPart;

public class AddExplorerItemAction
{
	private static ListView<AnchorPane> listView = null;

	public static void initialize()
	{
		listView = (ListView) ActiveAreaPart.getNameSpace().get("lsvExplorerTab");
	}

	// Script Name (No Extension)
	public static void action(String name)
	{

	}
}