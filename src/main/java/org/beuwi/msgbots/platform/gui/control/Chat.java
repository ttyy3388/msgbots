package org.beuwi.msgbots.platform.gui.control;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class Chat extends HBox
{
	final ContextMenu menu = new ContextMenu
	(
		new MenuItem("Copy"),
		new MenuItem("Delete")
	);

	public Chat(String message)
	{
		this(message, false);
	}

	public Chat(String message, boolean isbot)
	{
		String name = isbot ? "sender" : "bot";

		Label chat = new Label();

		chat.setText(message);
		chat.setMaxWidth(220);
		chat.setWrapText(true);

		if (!isbot)
		{
			setAlignment(Pos.CENTER_RIGHT);
		}
		else
		{

		}

		menu.setNode(this);

		getChildren().add(chat);
		getStyleClass().add("chat");
	}
}
