package org.beuwi.simulator.platform.application.views.actions;

import javafx.scene.layout.AnchorPane;
import org.beuwi.simulator.platform.application.views.tabs.SettingsTab;
import org.beuwi.simulator.platform.ui.components.ITabType;

public class OpenSettingsTabAction
{
	private static AnchorPane pane;

	public static void initialize()
	{
		pane = SettingsTab.getRoot();
	}

	public static void update()
	{
		AddEditorTabAction.update("Settings", pane, ITabType.LOG);
	}
}