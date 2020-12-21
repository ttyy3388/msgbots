package org.beuwi.msgbots.platform.gui.control;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.SelectionMode;
import javafx.scene.text.Text;

public class ChatView extends ListView<Chat> {
	private static final String DEFAULT_STYLE_CLASS = "chat-view";

	final ContextMenu menu = new ContextMenu(
		new Menu("Delete All", event -> getItems().clear())
		// new Menu("Select All", event -> getSelectionModel().selectAll())
	);

	public ChatView() {
		setOnMouseClicked(event -> {
			Node target = (Node) event.getTarget();

			if (target instanceof VBox || target instanceof Text || target instanceof Label) {
				return ;
			}
			else {
				menu.show(this, event);
			}
		});

		setOnMousePressed(event -> {
			if (menu.isShowing()) {
				menu.hide();
			}
		});

		getItems().addListener((ListChangeListener<Chat>) change -> {
			while (change.next()) {
				for (Chat chat : change.getRemoved()) {
					chat.setView(null);
				}
				for (Chat chat : change.getAddedSubList()) {
					chat.setView(this);
				}
			}
		});

		// setSelectionMode(SelectionMode.MULTIPLE);
		getStyleClass().add(DEFAULT_STYLE_CLASS);
	}

	/* public Chat getChat(int index)
	{
		return getItem(index);
	}

	public Chat getChat(String id)
	{
		return getItem(id);
	}

	public ObservableList<Chat> getChats()
	{
		return getItems();
	} */
}