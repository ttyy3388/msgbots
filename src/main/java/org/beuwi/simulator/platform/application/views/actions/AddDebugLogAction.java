package org.beuwi.simulator.platform.application.views.actions;

import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import org.beuwi.simulator.platform.application.views.tabs.GlobalLogTab;
import org.beuwi.simulator.platform.ui.components.ILogItem;

public class AddDebugLogAction
{
	private static ListView<AnchorPane> lsvGlobalLog;
	private static ListView<AnchorPane> lsvDebugRoom;

	public static void initialize()
	{
		lsvGlobalLog = GlobalLogTab.getComponent();
		// lsvDebugRoom = DebugRoomTab.getLogListView();
	}

	public static void update(ILogItem item)
	{
		// lsvDebugRoom.getItems().add(item);
		// lsvDebugRoom.scrollTo(item);
	}
}