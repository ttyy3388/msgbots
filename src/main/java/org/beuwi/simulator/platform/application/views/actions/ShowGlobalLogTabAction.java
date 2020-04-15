package org.beuwi.simulator.platform.application.views.actions;

import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import org.beuwi.simulator.platform.application.views.tabs.GlobalLogTab;

public class ShowGlobalLogTabAction
{
	private static ListView<AnchorPane> listView;

	public static void initialize()
	{
		listView = GlobalLogTab.getComponent();
	}

	public static void update()
	{
		AddEditorTabAction.update("@tab::log", "Global Log", listView);
	}
}