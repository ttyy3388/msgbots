package org.beuwi.msgbots.view.gui.control;

import javafx.collections.ListChangeListener;
import javafx.scene.control.SelectionMode;
import javafx.scene.input.KeyEvent;

import java.util.ArrayList;
import java.util.List;

public class ChatView extends ListView<ChatItem> {
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
						// CopyListAction.execute(chats, "\n");
						break;
				}
			}
		});

		setAutoScroll(true);
		// setSelectionMode(SelectionMode.SINGLE);
		addStyleClass("chat-view");
	}
}