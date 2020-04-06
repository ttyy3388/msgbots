package org.beuwi.simulator.platform.application.actions;

import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import org.beuwi.simulator.platform.application.views.tabs.DebugRoomTab;
import org.beuwi.simulator.platform.ui.components.ILogItem;
import org.beuwi.simulator.platform.ui.components.ILogType;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AddDebugLogAction
{
	// private static ListView<AnchorPane> lsvGlobalLog;
	private static ListView<AnchorPane> lsvDebugRoom;

	public static void initialize()
	{
		// lsvGlobalLog = GlobalLogTab.getComponent();
		lsvDebugRoom = DebugRoomTab.getLogListView();
	}

	public static void update(String logMsg, int logType)
	{
		String logDate = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss").format(new Date());

		ILogItem item = new ILogItem(logMsg, logDate, logType == ILogType.ERROR ? "error" :
				logType == ILogType.WARNING ? "warning" :
				logType == ILogType.EVENT ? "event" : null);

		// lsvGlobalLog.getItems().add(item);
		// lsvGlobalLog.scrollTo(item);

		lsvDebugRoom.getItems().add(item);
		lsvDebugRoom.scrollTo(item);
	}
}