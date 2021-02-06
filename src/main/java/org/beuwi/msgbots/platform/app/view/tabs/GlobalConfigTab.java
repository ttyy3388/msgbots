package org.beuwi.msgbots.platform.app.view.tabs;

import javafx.collections.ObservableMap;

import org.beuwi.msgbots.openapi.FormLoader;
import org.beuwi.msgbots.platform.app.impl.View;
import org.beuwi.msgbots.platform.gui.control.NaviView;
import org.beuwi.msgbots.platform.gui.control.TabItem;

public class GlobalConfigTab implements View {
	private static ObservableMap<String, Object> namespace;
	private static FormLoader loader;
	private static TabItem root;
	private static NaviView component;

	@Override
	public void init() {
		loader = new FormLoader("tab", "global-config-tab");
		namespace = loader.getNamespace();
		root = loader.getRoot();
		component = loader.getComponent();
	}

	public static TabItem getRoot() {
		return root;
	}

	public static NaviView getComponent() {
		return component;
	}

	public static <T> T getComponent(String key) {
		return (T) namespace.get(key);
	}

	public static ObservableMap<String, Object> getNamespace() {
		return namespace;
	}
}