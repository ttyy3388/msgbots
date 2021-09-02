package org.beuwi.msgbots.platform.app.view.parts;

import javafx.collections.ObservableMap;
import javafx.fxml.FXML;

import org.beuwi.msgbots.openapi.FormLoader;
import org.beuwi.msgbots.base.impl.View;
import org.beuwi.msgbots.platform.gui.control.TabView;
import org.beuwi.msgbots.platform.gui.layout.StackPane;

public class MainAreaPart extends StackPane implements View {
	private static MainAreaPart instance = null;

	private final ObservableMap<String, Object> namespace;
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
	public Object findById(String id) {
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