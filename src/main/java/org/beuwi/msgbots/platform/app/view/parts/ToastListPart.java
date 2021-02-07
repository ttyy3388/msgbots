package org.beuwi.msgbots.platform.app.view.parts;

import javafx.collections.ObservableMap;

import org.beuwi.msgbots.openapi.FormLoader;
import org.beuwi.msgbots.platform.app.impl.View;
import org.beuwi.msgbots.platform.gui.control.ToastView;
import org.beuwi.msgbots.platform.gui.layout.AnchorPane;

public class ToastListPart implements View {
	private static ObservableMap<String, Object> namespace;
	private static FormLoader loader;
	private static AnchorPane root;
	private static ToastView component;

	@Override
	public void init() {
		loader = new FormLoader("part", "toast-list-part");
		namespace = loader.getNamespace();
		root = loader.getRoot();
		component = loader.getComponent();

		root.setBackground(null);
		// root.setMouseTransparent(true);
		root.setPickOnBounds(false);

		component.setBackground(null);
		// component.setMouseTransparent(true);
		component.setPickOnBounds(false);
	}

	public static AnchorPane getRoot() {
		return root;
	}

	public static ToastView getComponent() {
		return component;
	}

	public static <T> T getComponent(String key) {
		return (T) namespace.get(key);
	}

	public static ObservableMap<String, Object> getNamespace() {
		return namespace;
	}
}
