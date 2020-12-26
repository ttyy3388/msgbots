package org.beuwi.msgbots.platform.app.view.parts;

import javafx.collections.ObservableMap;
import javafx.scene.layout.StackPane;
import org.beuwi.msgbots.openapi.FormLoader;
import org.beuwi.msgbots.platform.app.impl.View;
import org.beuwi.msgbots.platform.gui.control.TabView;
import org.beuwi.msgbots.platform.gui.layout.StackPanel;
import org.beuwi.msgbots.platform.util.SharedValues;

// Editor Area
public class MainAreaPart implements View {
	private static final int DEFAULT_MAX_WIDTH = 500;

	private static ObservableMap<String, Object> namespace;
	private static FormLoader loader;
	private static StackPanel root;
	private static TabView component;

	@Override
	public void init() {
		loader = new FormLoader("part", "main-area-part");
		namespace = loader.getNamespace();
		root = loader.getRoot();
		component = loader.getComponent();

		root.setMinWidth(DEFAULT_MAX_WIDTH);
	}

	public static StackPanel getRoot() {
		return root;
	}

	public static TabView getComponent() {
		return component;
	}

	public static <T> T getComponent(String key) {
		return (T) namespace.get(key);
	}

	public static ObservableMap<String, Object> getNamespace() {
		return namespace;
	}
}