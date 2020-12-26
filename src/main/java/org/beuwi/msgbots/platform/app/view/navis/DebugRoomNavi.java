package org.beuwi.msgbots.platform.app.view.navis;

import javafx.collections.ObservableMap;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import org.beuwi.msgbots.openapi.FormLoader;
import org.beuwi.msgbots.platform.app.impl.View;
import org.beuwi.msgbots.platform.app.view.actions.SendChatMessageAction;
import org.beuwi.msgbots.platform.gui.control.ChatView;
import org.beuwi.msgbots.platform.gui.control.Tab;
import org.beuwi.msgbots.platform.gui.control.TextArea;
import org.beuwi.msgbots.platform.gui.control.VBox;

public class DebugRoomNavi implements View {
	private static ObservableMap<String, Object> namespace;
	private static FormLoader loader;
	private static Tab root;
	private static VBox component;

	@Override
	public void init() {
		loader = new FormLoader("navi", "debug-room-navi");
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
					textArea.appendText(System.lineSeparator());
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
		});

		textArea.requestFocus();
	}

	public static Tab getRoot() {
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