package org.beuwi.msgbots.platform.app.view.actions;

import javafx.scene.layout.VBox;
import org.beuwi.msgbots.platform.app.impl.Action;
import org.beuwi.msgbots.platform.app.view.tabs.DebugRoomTab;
import org.beuwi.msgbots.platform.gui.control.Tab;

public class OpenDebugRoomAction implements Action
{
	private static VBox pane;

	@Override
	public void init()
	{
		pane = DebugRoomTab.getRoot();
	}

	public static void execute()
	{
		AddEditorTabAction.execute(new Tab("DEBUG ROOM", pane));
	}

	@Override
	public String getName()
	{
		return "open.debug.room.action";
	}
}