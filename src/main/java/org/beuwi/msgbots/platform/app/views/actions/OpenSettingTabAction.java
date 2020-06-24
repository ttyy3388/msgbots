package org.beuwi.msgbots.platform.app.views.actions;

import javafx.scene.layout.StackPane;
import org.beuwi.msgbots.platform.app.views.tabs.PreferencesTab;

public class OpenSettingTabAction
{
	private static StackPane pane;

	public static void initialize()
	{
		pane = PreferencesTab.getRoot();
	}

	public static void update()
	{
		// AddEditorTabAction.update("@global::settings", "Settings", pane);
	}
}