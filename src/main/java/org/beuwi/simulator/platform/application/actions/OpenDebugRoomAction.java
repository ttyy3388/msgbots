package org.beuwi.simulator.platform.application.actions;

import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import org.beuwi.simulator.platform.application.views.tabs.DebugRoomTab;
import org.beuwi.simulator.platform.ui.components.ITabType;

public class OpenDebugRoomAction
{
	private static AnchorPane pane;

	private static Image icon;

	public static void initialize()
	{
		pane = (AnchorPane) DebugRoomTab.getNameSpace().get("anpDebugRoom");
		icon = new Image(OpenDebugRoomAction.class.getResource("/images/debug_blue.png").toExternalForm());
	}

	public static void update()
	{
		AddEditorTabAction.update(icon, "Debug Room", pane, ITabType.DEBUG);
	}
}