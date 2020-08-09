package org.beuwi.msgbots.platform.app.view.actions;

import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import org.beuwi.msgbots.platform.app.impl.Action;
import org.beuwi.msgbots.platform.app.view.tabs.DebugRoomTab;

public class AddChatMessageAction implements Action
{
	private static ListView listView;

	@Override
	public void init()
	{
		listView = DebugRoomTab.getComponent();
	}

	public static void execute(String message)
	{
		Label chat = new Label();
		chat.setText(message);
		chat.setMaxWidth(220);
		chat.setWrapText(true);

		chat.getStyleClass().add("chat");

		listView.getItems().add(chat);
	}

	@Override
	public String getName()
	{
		return "add.chat.message.action";
	}
}