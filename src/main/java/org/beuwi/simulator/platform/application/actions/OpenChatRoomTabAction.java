package org.beuwi.simulator.platform.application.actions;

import javafx.scene.layout.AnchorPane;
import org.beuwi.simulator.platform.application.views.tabs.ChatRoomTab;
import org.beuwi.simulator.platform.ui.components.ITabType;

public class OpenChatRoomTabAction
{
	private static AnchorPane pane;

	public static void initialize()
	{
		pane = ChatRoomTab.getRoot();
	}

	public static void update()
	{
		AddEditorTabAction.update("Chat Room", pane, ITabType.DEBUG);
	}
}