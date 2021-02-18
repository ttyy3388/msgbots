package org.beuwi.msgbots.platform.gui.control;

import javafx.collections.ListChangeListener;
import javafx.scene.control.SelectionMode;
import javafx.scene.input.KeyEvent;

import org.beuwi.msgbots.platform.app.action.CopyListAction;
import org.beuwi.msgbots.platform.app.action.CopyStringAction;

import java.util.ArrayList;
import java.util.List;

public class ChatView extends ListView<ChatItem> {
	private static final String DEFAULT_STYLE_CLASS = "chat-view";

	public ChatView() {
		getItems().addListener((ListChangeListener<ChatItem>) change -> {
			while (change.next()) {
				for (ChatItem item : change.getRemoved()) {
					item.setView(null);
				}
				for (ChatItem item : change.getAddedSubList()) {
					item.setView(this);
				}
			}
		});

		addEventHandler(KeyEvent.KEY_PRESSED, event -> {
			if (event.isControlDown()) {
				switch (event.getCode()) {
					// Copy
					case C :
						final List<String> chats = new ArrayList<>();
						getSelectedItems().forEach(item -> {
							chats.add(item.getMessage());
						});
						CopyListAction.execute(chats, "\n");
						break;
				}
			}
		});

		/* new ContextMenu(
			new MenuItem("Select All", event -> {
				getSelectionModel().selectAll();
			})
		).setNode(this); */

		setAutoScroll(true);
		setSelectionMode(SelectionMode.MULTIPLE);
		getStyleClass().add(DEFAULT_STYLE_CLASS);
	}
}