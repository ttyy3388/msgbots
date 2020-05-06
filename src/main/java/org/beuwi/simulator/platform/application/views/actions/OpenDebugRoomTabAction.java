package org.beuwi.simulator.platform.application.views.actions;

import javafx.scene.Node;
import org.beuwi.simulator.platform.application.views.tabs.DebugRoomTab;
import org.beuwi.simulator.settings.Settings;
import org.beuwi.simulator.utils.ResourceUtils;

public class OpenDebugRoomTabAction
{
	private static Node pane;

	public static void initialize()
	{
		pane = DebugRoomTab.getRoot();
	}

	public static void update()
	{
		AddEditorTabAction.update
		(
			ResourceUtils.getImage("tab_debug"),
			"@global::debug",
			Settings.getPublicSetting("chat").getString("room"),
			pane
		);
	}
}