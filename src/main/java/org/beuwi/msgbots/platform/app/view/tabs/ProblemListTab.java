package org.beuwi.msgbots.platform.app.view.tabs;

import javafx.collections.ObservableMap;
import org.beuwi.msgbots.openapi.FormLoader;
import org.beuwi.msgbots.platform.app.impl.View;
import org.beuwi.msgbots.platform.gui.control.Button;
import org.beuwi.msgbots.platform.gui.control.TabItem;
import org.beuwi.msgbots.platform.gui.layout.HBox;

public class ProblemListTab implements View {
	private static ObservableMap<String, Object> namespace;
	private static FormLoader loader;
	private static TabItem root;

	@Override
	public void init() {
		loader = new FormLoader("tab", "problem-list-tab");
		namespace = loader.getNamespace();
		root = loader.getRoot();
		// component = loader.getComponent();

		HBox header = (HBox) root.getHeader();
		Button button = (Button) root.getButton();

		header.getChildren().remove(button);
	}

	public static TabItem getRoot() {
		return root;
	}

	/* public static TextArea getComponent() {
		return component;
	} */

	public static Object getComponent(String key) {
		return namespace.get(key);
	}

	public static ObservableMap<String, Object> getNamespace() {
		return namespace;
	}
}