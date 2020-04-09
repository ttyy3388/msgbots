package org.beuwi.simulator.platform.application.views.actions;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import org.beuwi.simulator.compiler.engine.ScriptEngine;
import org.beuwi.simulator.managers.FileManager;
import org.beuwi.simulator.platform.application.views.tabs.DebugRoomTab;
import org.beuwi.simulator.settings.Settings;

public class SendChatMessageAction
{
	private static ListView listView;

	public static void initialize()
	{
		listView = DebugRoomTab.getChatListView();
	}

	public static void update(String message, boolean isBot)
	{
		HBox   itemCell  = new HBox();
		Label  itemLabel = new Label(message);

		itemCell.setId(message);
		itemLabel.setMaxWidth(180);
		itemLabel.setWrapText(true);

		if (!isBot)
		{
			itemCell.setAlignment(Pos.TOP_RIGHT);
			itemCell.getChildren().add(itemLabel);

			itemLabel.getStyleClass().add("sender-label");
		}
		else
		{
			AnchorPane itemPane = new AnchorPane();

			Label nameLabel = new Label(Settings.getPublicSetting("chat").getString("bot"));

			AnchorPane.setTopAnchor(nameLabel, .0);
			AnchorPane.setLeftAnchor(nameLabel, .0);

			AnchorPane.setTopAnchor(itemLabel, 22.0);
			AnchorPane.setLeftAnchor(itemLabel, 0.0);
			AnchorPane.setRightAnchor(itemLabel, .0);

			Circle profile = new Circle(35, 35, 20);
			profile.setFill(new ImagePattern(new Image(FileManager.getDataFile("profile_bot.png").toURI().toString())));

			itemPane.getChildren().addAll(nameLabel, itemLabel);

			itemCell.setSpacing(10);
			itemCell.setAlignment(Pos.TOP_LEFT);
			itemCell.getChildren().addAll(profile, itemPane);

			itemLabel.getStyleClass().add("bot-label");
		}

		listView.getItems().add(itemCell);
		listView.scrollTo(itemCell);

		if (!isBot)
		{
			ScriptEngine.run(message);
		}
	}
}