package org.beuwi.msgbots.platform.app.views.actions;

import javafx.scene.Node;
import org.beuwi.msgbots.platform.app.views.tabs.GlobalSettingTab;

public class OpenGlobalSettingAction
{
	private static Node pane;

	public static void initialize()
	{
		pane = GlobalSettingTab.getRoot();
	}

	public static void update()
	{
		AddEditorTabAction.update("Settings", pane);
	}
}