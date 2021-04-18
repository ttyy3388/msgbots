package org.beuwi.msgbots.platform.app.view.tabs;

import javafx.collections.ObservableMap;
import javafx.scene.control.TextFormatter;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import org.beuwi.msgbots.openapi.FormLoader;
import org.beuwi.msgbots.platform.app.impl.View;
import org.beuwi.msgbots.platform.app.view.actions.SendChatMessageAction;
import org.beuwi.msgbots.platform.gui.control.ChatView;
import org.beuwi.msgbots.platform.gui.control.TabItem;
import org.beuwi.msgbots.platform.gui.control.TextArea;
import org.beuwi.msgbots.platform.gui.layout.VBox;

public class DebugRoomTab implements View {
	private static final int MAX_TEXT_LENGTH = 5000;

	private static ObservableMap<String, Object> namespace;
	private static FormLoader loader;
	private static TabItem root;
	private static VBox component;

	@Override
	public void init() {
		loader = new FormLoader("tab", "debug-room-tab");
		namespace = loader.getNamespace();
		root = loader.getRoot();
		component = loader.getComponent();

		ChatView listView = (ChatView) namespace.get("lsvChatView");
		TextArea textArea = (TextArea) namespace.get("txaChatInput");

		textArea.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
			KeyCode keyCode = event.getCode();
			if (keyCode.equals(KeyCode.TAB)) {
				event.consume();
				return ;
			}
			if (keyCode.equals(KeyCode.ENTER)) {
				if (event.isShiftDown()) {
					// 현재 커서의 위치를 가져옴
					int position = textArea.getCaretPosition();
					textArea.insertText(position, System.lineSeparator());
					event.consume();
					return ;
				}

				String text = textArea.getText();

				if (text.trim().isEmpty()) {
					if (text.isEmpty()) {
						textArea.clear();
					}
					event.consume();
					return ;
				}

				SendChatMessageAction.execute(text);

				textArea.clear();
				event.consume();
			}
			/* else {
				// 리미트 지정
				if (text.length() > MAX_TEXT_LENGTH) {
					textArea.setText(textArea.getText().substring(0, MAX_TEXT_LENGTH));
				}
			} */
		});

		textArea.requestFocus();
	}

	public static TabItem getRoot() {
		return root;
	}

	public static VBox getComponent() {
		return component;
	}

	public static <T> T getComponent(String key) {
		return (T) namespace.get(key);
	}

	public static ObservableMap<String, Object> getNamespace() {
		return namespace;
	}
}