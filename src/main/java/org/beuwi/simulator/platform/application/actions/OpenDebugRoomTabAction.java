package org.beuwi.simulator.platform.application.actions;

import javafx.scene.layout.AnchorPane;
import org.beuwi.simulator.platform.application.views.tabs.DebugRoomTab;
import org.beuwi.simulator.platform.ui.components.ITabType;
import org.beuwi.simulator.settings.Settings;

public class OpenDebugRoomTabAction
{
	private static AnchorPane pane;

	public static void initialize()
	{
		pane = DebugRoomTab.getRoot();
	}

	public static void update()
	{
		AddEditorTabAction.update(Settings.getPublicSetting("chat").getString("room"), pane, ITabType.CHAT);
	}
}