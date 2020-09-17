package org.beuwi.msgbots.platform.gui.control;

public class ChatView extends ListView
{
	final ContextMenu menu = new ContextMenu
	(
		new MenuItem("Clear", event -> clear()),
		new MenuItem("Select All", event -> select())
	);

	public ChatView()
	{
		menu.setNode(this);

		getStyleClass().add("chat-view");
	}

	public void addChat(Chat chat)
	{
		addItem(chat);
	}
}