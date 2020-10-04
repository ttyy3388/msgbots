package org.beuwi.msgbots.platform.gui.control;

import javafx.geometry.Pos;
import javafx.scene.layout.Priority;

public class Chat extends HBox
{
	private static final String DEFAULT_STYLE_CLASS = "chat-item";
	// private static final String HUMAN_STYLE_CLASS = "chat-human";
	// private static final String BOT_STYLE_CLASS = "chat-bot";

	// private static final int DEFAULT_MIN_HEIGHT = 50;

	private final Label chat = new Label();
	private final Label name = new Label();

	private final VBox item = new VBox(name, chat);

	private final ContextMenu menu;

	private ChatView parent;

	{
		VBox.setVgrow(chat, Priority.ALWAYS);
	}

	public Chat(String message)
	{
		this(message, false);
	}

	public Chat(String message, boolean isbot)
	{
		menu = new ContextMenu
		(
			new MenuItem("Copy"),
			new MenuItem("Delete")
		);

		menu.setNode(chat);

		name.setText("TEST NAME");

		item.setSpacing(10);
		item.setFillWidth(false);
		// item.setFitContent(false);

		chat.setText(message);
		chat.setMaxWidth(220);
		chat.setWrapText(true);
		chat.getStyleClass().add("chat-label");

		if (!isbot)
		{
			chat.setAlignment(Pos.CENTER_RIGHT);
			item.setAlignment(Pos.CENTER_RIGHT);
			this.setAlignment(Pos.CENTER_RIGHT);
			chat.getStyleClass().add("chat-human");
		}
		else
		{
			// Profile
			chat.setAlignment(Pos.CENTER_LEFT);
			item.setAlignment(Pos.CENTER_LEFT);
			this.setAlignment(Pos.CENTER_LEFT);
			chat.getStyleClass().add("chat-bot");
		}

		getChildren().add(item);
		getStyleClass().add(DEFAULT_STYLE_CLASS);
	}

	public ChatView getView()
	{
		return parent;
	}

	public void setView(ChatView parent)
	{
		this.parent = parent;
	}
}