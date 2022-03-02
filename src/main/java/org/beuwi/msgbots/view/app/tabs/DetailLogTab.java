package org.beuwi.msgbots.view.app.tabs;

import javafx.collections.ObservableMap;
import javafx.fxml.FXML;
import javafx.scene.Node;

import org.beuwi.msgbots.openapi.FormLoader;
import org.beuwi.msgbots.base.impl.View;
import org.beuwi.msgbots.view.gui.control.LogView;
import org.beuwi.msgbots.view.gui.control.TabItem;

public class DetailLogTab extends TabItem implements View {
	private static DetailLogTab instance = null;

	private final ObservableMap<String, Node> namespace;
	private final FormLoader loader;

	@FXML
	private LogView control;

	private DetailLogTab() {
		loader = new FormLoader();
		loader.setName("detail-log-tab");
		loader.setController(this);
		loader.setRoot(this);
		loader.load();

		namespace = loader.getNamespace();
		// root = loader.getRoot();
	}

	public LogView getLogView() {
		return control;
	}

	@Override
	public Node findById(String id) {
		return namespace.get(id);
	}

	public static DetailLogTab getInstance() {
		if (instance == null) {
			instance = new DetailLogTab();
		}
		return instance;
	}
}