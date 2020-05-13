package org.beuwi.simulator.platform.application.views.actions;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import org.beuwi.simulator.compiler.engine.ScriptManager;
import org.beuwi.simulator.managers.FileManager;
import org.beuwi.simulator.platform.application.views.tabs.DebugRoomTab;
import org.beuwi.simulator.platform.ui.components.IContextMenu;
import org.beuwi.simulator.platform.ui.components.IListView;
import org.beuwi.simulator.platform.ui.components.IMenuItem;
import org.beuwi.simulator.settings.Settings;

public class AddChatMessageAction
{
	private static IListView<HBox> listView;

	public static void initialize()
	{
		listView = DebugRoomTab.getComponent();
	}

	public static void update(String message, boolean isBot)
	{
		AnchorPane pane = new AnchorPane();

		HBox cell = new HBox(pane);
		HBox item = new HBox();

		Label chat = new Label(message);
		chat.setText(message);
		chat.setMaxWidth(220);
		chat.setWrapText(true);

		Settings.Public data = Settings.getPublicSetting("debug");

		String name = data.getString(!isBot ? "sender" : "bot");

		if (!isBot)
		{
			chat.getStyleClass().add("sender-label");
			item.getChildren().add(chat);
			cell.setAlignment(Pos.TOP_RIGHT);
		}
		else
		{
			// Name Label
			Label label = new Label(name);

			AnchorPane.setTopAnchor(label, .0);
			AnchorPane.setLeftAnchor(label, .0);

			AnchorPane.setTopAnchor(chat, 22.0);
			AnchorPane.setLeftAnchor(chat, 0.0);

			Circle profile = new Circle(35, 35, 20);
			profile.setFill(new ImagePattern(new Image(FileManager.getDataFile("profile_bot.png").toURI().toString())));

			chat.getStyleClass().add("bot-label");

			item.setSpacing(12);
			item.getChildren().addAll(profile, new AnchorPane(label, chat));

			cell.setAlignment(Pos.TOP_LEFT);
		}

		IContextMenu menu = new IContextMenu
		(
			new IMenuItem("Cut", "Ctrl + X", event -> listView.cut()),
			new IMenuItem("Copy", "Ctrl + C", event -> listView.copy()),
			new IMenuItem("Delete", "Delete", event -> listView.delete()),
			new SeparatorMenuItem(),
			new IMenuItem("Select All", "Ctrl + A", event -> listView.selectAll())
		);

		menu.setNode(pane);
		pane.getChildren().add(item);
		item.setMouseTransparent(true);
		cell.setId(message);

		listView.addItem(cell);
		listView.scrollTo(cell);

		if (!isBot)
		{
			ScriptManager.run(message);
		}
	}

	/* if (message.length() >= 200)
	{
		// 전체보기
	}

	if (message.length() >= 20000)
	{
		return ;
	} */
}