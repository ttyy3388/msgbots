package org.beuwi.msgbots.platform.gui.control;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

public class ChatView extends ListView<Chat>
{
	final ContextMenu menu = new ContextMenu
	(
		new MenuItem("Clear", event -> clear()),
		new MenuItem("Select All", event -> select())
	);

	public ChatView()
	{
		// menu.setNode(this);

		this.addEventFilter(MouseEvent.MOUSE_CLICKED, event ->
		{
			Node target = (Node) event.getTarget();

			System.out.println(target);

			if (target instanceof VBox || target instanceof Text || target instanceof Label)
			{
				return ;
			}
			else
			{
				menu.show(this, event);
			}
		});

		this.addEventFilter(MouseEvent.MOUSE_PRESSED, event ->
		{
			if (menu.isShowing())
			{
				menu.hide();
			}
		});

		getItems().addListener((ListChangeListener<Chat>) change ->
		{
			while (change.next())
			{
				for (Chat chat : change.getRemoved())
				{
					chat.setView(null);
				}

				for (Chat chat : change.getAddedSubList())
				{
					chat.setView(this);
				}
			}
		});

		getStyleClass().add("chat-view");
	}

	public void addChat(Chat chat)
	{
		addItem(chat);
	}

	public ObservableList<Chat> getChats()
	{
		return getItems();
	}
}