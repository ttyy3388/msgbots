package org.beuwi.simulator.platform.application.actions;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import org.beuwi.simulator.platform.application.views.tabs.DebugRoomTab;

public class OpenDebugRoomAction
{
	private static AnchorPane pane;

	private static ImageView icon;

	public static void initialize()
	{
		pane = (AnchorPane) DebugRoomTab.getNameSpace().get("anpDebugRoom");
		icon = new ImageView(new Image(OpenDebugRoomAction.class.getResource("").toExternalForm()));
	}

	public static void update()
	{
		AddEditorTabAction.update(pane);
	}
}