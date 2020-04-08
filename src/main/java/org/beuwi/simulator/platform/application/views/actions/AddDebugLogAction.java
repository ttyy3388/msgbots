package org.beuwi.simulator.platform.application.views.actions;

import javafx.scene.control.*;
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

		lsvDebugRoom.getItems().add(item);
		lsvDebugRoom.scrollTo(item);

		lsvDebugRoom.setCellFactory(param -> new ListCell<AnchorPane>()
		{
			{
				prefWidthProperty().bind(lsvDebugRoom.widthProperty().subtract(2));
				setMaxWidth(Control.USE_PREF_SIZE);
			}

			@Override
			protected void updateItem(AnchorPane item, boolean empty)
			{
				super.updateItem(item, empty);

				if (item != null && !empty)
				{
					setGraphic(item);
				}
				else
				{
					setGraphic(null);
				}
			}
		});
	}
}