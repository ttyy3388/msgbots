package org.beuwi.simulator.platform.application.actions;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import org.beuwi.simulator.platform.application.views.tabs.DebugRoomTab;

public class ResizeChatAreaAction
{
	private static AnchorPane anpActiveArea;
	private static AnchorPane anpChatArea;

	public static void initialize()
	{
		// anpActiveArea = ActiveAreaPart.getRoot();
		anpChatArea   = (AnchorPane) DebugRoomTab.getNameSpace().get("anpChatArea");
	}

	public static void update(MouseEvent event)
	{
		anpChatArea.setPrefWidth(event.getSceneX() - anpActiveArea.getWidth());
	}
}