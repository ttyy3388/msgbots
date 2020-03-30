package org.beuwi.simulator.platform.application.actions;

import javafx.scene.control.ListView;
import org.beuwi.simulator.platform.application.views.tabs.DebugRoomTab;

public class AddDebugRoomLogAction
{
	private static ListView listView;

	public static void initialize()
	{
		listView = (ListView) DebugRoomTab.getNameSpace().get("lsvLogPart");
	}

	/* public static void update(String logMsg, int type)
	{
		String logType = switch (type)
		{
			case 0 -> "error";
			case 1 -> "event";
			case 2 -> "info";
			default -> null;
		};

		AnchorPane itemCell = new AnchorPane();
		ImageView  itemIcon = new ImageView(ResourceUtils.getImage( "log_" + logType + ".png"));
		Label 	   itemMsg  = new Label(logMsg + ": " + logName);
		Label 	   itemDate = new Label(logDate);

		itemIcon.setFitWidth(20);
		itemIcon.setFitHeight(20);

		AnchorPane.setTopAnchor(itemIcon, 15.0);
		AnchorPane.setLeftAnchor(itemIcon, 15.0);

		AnchorPane.setTopAnchor(itemMsg, 15.0);
		AnchorPane.setLeftAnchor(itemMsg, 50.0);
		AnchorPane.setRightAnchor(itemMsg, 15.0);

		AnchorPane.setLeftAnchor(itemDate, 15.0);
		AnchorPane.setBottomAnchor(itemDate, 10.0);

		itemMsg.setPrefWidth(listView.getWidth() - 50);
		itemMsg.setWrapText(true);
		itemMsg.getStyleClass().add(logType);
		itemDate.getStyleClass().add(logType);

		itemCell.setPrefHeight(90);
		itemCell.getStyleClass().add("list-item");
		itemCell.getChildren().addAll(itemIcon, itemMsg, itemDate);

		listView.getItems().add(itemCell);
		listView.scrollTo(itemCell);
	} */
}