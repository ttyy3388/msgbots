package org.beuwi.simulator.platform.application.actions;

import javafx.scene.layout.AnchorPane;
import org.beuwi.simulator.platform.application.views.tabs.DebugRoomTab;
import org.beuwi.simulator.platform.ui.components.ITabType;

public class OpenDebugRoomAction
{
	private static AnchorPane pane;

	public static void initialize()
	{
		pane = (AnchorPane) DebugRoomTab.getNameSpace().get("anpDebugRoom");
	}

	public static void update()
	{
		AddEditorTabAction.update("Debug Room", pane, ITabType.DEBUG);
	}
}