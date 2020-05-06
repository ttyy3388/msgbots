package org.beuwi.simulator.platform.application.views.actions;

import javafx.scene.layout.AnchorPane;
import org.beuwi.simulator.platform.application.views.tabs.SettingsTab;

public class OpenSettingsTabAction
{
	private static AnchorPane pane;

	public static void initialize()
	{
		pane = SettingsTab.getRoot();
	}

	public static void update()
	{
		AddEditorTabAction.update("@global::settings", "Settings", pane);
	}
}