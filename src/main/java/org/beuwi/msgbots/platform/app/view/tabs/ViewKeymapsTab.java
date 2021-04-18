package org.beuwi.msgbots.platform.app.view.tabs;

import javafx.collections.ObservableMap;

import org.beuwi.msgbots.openapi.FormLoader;
import org.beuwi.msgbots.platform.app.impl.View;
import org.beuwi.msgbots.openapi.KeyMap;
import org.beuwi.msgbots.platform.gui.control.KeyMapBox;
import org.beuwi.msgbots.platform.gui.control.ListView;
import org.beuwi.msgbots.platform.gui.control.TabItem;
import org.beuwi.msgbots.platform.gui.layout.StackPane;
import org.beuwi.msgbots.platform.gui.layout.VBox;
import org.beuwi.msgbots.platform.util.GlobalKeyMaps;

public class ViewKeymapsTab implements View {
	private static ObservableMap<String, Object> namespace;
	private static FormLoader loader;
	private static TabItem root;
	private static VBox component;

	@Override
	public void init() {
		loader = new FormLoader("tab", "view-keymaps-tab");
		namespace = loader.getNamespace();
		root = loader.getRoot();
		component = loader.getComponent();

		VBox<KeyMapBox> vbox = (VBox) namespace.get("vbxKeyMaps");
		for (KeyMap keyMap : GlobalKeyMaps.getList()) {
			vbox.getChildren().add(new KeyMapBox(keyMap));
		}
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