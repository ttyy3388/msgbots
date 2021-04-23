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
import org.beuwi.msgbots.platform.gui.layout.DebugRoom;
import org.beuwi.msgbots.platform.gui.layout.VBox;

public class DebugRoomTab implements View {
	private static final int MAX_TEXT_LENGTH = 5000;

	private static ObservableMap<String, Object> namespace;
	private static FormLoader loader;
	private static TabItem root;
	private static DebugRoom component;

	@Override
	public void init() {
		loader = new FormLoader("tab", "debug-room-tab");
		namespace = loader.getNamespace();
		root = loader.getRoot();
		component = loader.getComponent();
	}

	public static TabItem getRoot() {
		return root;
	}

	public static DebugRoom getComponent() {
		return component;
	}

	public static <T> T getComponent(String key) {
		return (T) namespace.get(key);
	}

	public static ObservableMap<String, Object> getNamespace() {
		return namespace;
	}
}