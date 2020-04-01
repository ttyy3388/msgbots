package org.beuwi.simulator.platform.application.actions;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import org.beuwi.simulator.platform.application.views.tabs.ChatRoomTab;

public class SendChatMessageAction
{
	private static ListView listView;

	public static void initialize()
	{
		listView = (ListView) ChatRoomTab.getNameSpace().get("listView");
	}

	public static void update(String message, boolean isBot)
	{
		HBox   itemCell  = new HBox();
		Label  itemLabel = new Label(message);

		// itemCell.setPadding(new Insets(0));
		itemCell.setId(message);
		itemLabel.setMaxWidth(300);
		itemLabel.setWrapText(true);

		if (!isBot)
		{
			itemLabel.getStyleClass().add("sender-label");
			itemCell.setAlignment(Pos.TOP_RIGHT);
			itemCell.getChildren().add(itemLabel);
		}
		else
		{
			/* ImageView profile = new ImageView(new Image(FileManager.getDataFile("profile_bot.png").toURI().toString()));
			itemLabel.getStyleClass().add("bot-label");
			profile.setFitHeight(40);
			profile.setFitWidth(40);

			itemCell.setSpacing(10);
			itemCell.setAlignment(Pos.TOP_LEFT);
			itemCell.getChildren().addAll(profile, itemLabel); */
		}

		listView.getItems().add(itemCell);
		listView.scrollTo(itemCell);

		if (!isBot)
		{
			// ScriptManager.run(message);
		}
	}
}