package org.beuwi.msgbots.view.app.parts;

import javafx.collections.ObservableMap;
import javafx.fxml.FXML;
import javafx.scene.Node;

import org.beuwi.msgbots.openapi.FormLoader;
import org.beuwi.msgbots.base.impl.View;
import org.beuwi.msgbots.view.gui.control.TabView;
import org.beuwi.msgbots.view.gui.layout.StackPane;

public class MainAreaPart extends StackPane implements View {
	private static MainAreaPart instance = null;

	private final ObservableMap<String, Node> namespace;
	private final FormLoader loader;

	@FXML private TabView tabView;

	private MainAreaPart() {
		loader = new FormLoader();
		loader.setName("main-area-part");
		loader.setController(this);
		loader.setRoot(this);
		loader.load();

		namespace = loader.getNamespace();
		// root = loader.getRoot();
	}

	@Override
	public Node findById(String id) {
		return namespace.get(id);
	}

	public TabView getTabView() {
		return tabView;
	}

	public static MainAreaPart getInstance() {
		if (instance == null) {
			instance = new MainAreaPart();
		}
		return instance;
	}
}