package org.beuwi.simulator.platform.application.views.actions;

import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import org.beuwi.simulator.platform.application.views.tabs.GlobalLogTab;

public class OpenGlobalLogTabAction
{
	private static ListView<AnchorPane> listView;

	public static void initialize()
	{
		listView = GlobalLogTab.getComponent();
	}

	public static void update()
	{
		AddEditorTabAction.update("@global::log", "Global Log", listView);
	}
}