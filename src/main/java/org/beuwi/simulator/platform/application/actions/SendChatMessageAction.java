package org.beuwi.simulator.platform.application.actions;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import org.beuwi.simulator.platform.application.views.tabs.DebugRoomTab;

public class SendChatMessageAction
{
	private static ListView listView;

	public static void initialize()
	{
		listView = (ListView) DebugRoomTab.getNameSpace().get("lsvChatList");
	}

	public static void update(String message, int type)
	{
		HBox   itemCell  = new HBox();
		Label  itemLabel = new Label(message);

		// itemCell.setPadding(new Insets(0));
		itemCell.setId(message);
		itemLabel.setMaxWidth(300);
		itemLabel.setWrapText(true);

		switch (type)
		{
			// Bot
			case 0 :
				itemLabel.getStyleClass().add("bot-label");
				itemCell.setAlignment(Pos.TOP_LEFT);
				itemCell.getChildren().add(itemLabel);
				break;

			// Sender
			case 1 :
				itemLabel.getStyleClass().add("sender-label");
				itemCell.setAlignment(Pos.TOP_RIGHT);
				itemCell.getChildren().add(itemLabel);
				break;
		}

		listView.getItems().add(itemCell);
		listView.scrollTo(itemCell);
	}
}