package org.beuwi.msgbots.view.app.tabs;

import javafx.collections.ObservableMap;
import javafx.fxml.FXML;
import org.beuwi.msgbots.base.impl.View;
import org.beuwi.msgbots.openapi.FormLoader;
import org.beuwi.msgbots.view.gui.control.LogView;
import org.beuwi.msgbots.view.gui.control.TabItem;

public class ViewKeymapTab extends TabItem implements View {
	private static ViewKeymapTab instance = null;

	private final ObservableMap<String, Object> namespace;
	private final FormLoader loader;

	@FXML private LogView control;

	private ViewKeymapTab() {
		loader = new FormLoader();
		loader.setName("view-keymap-tab");
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
	public Object findById(String id) {
		return namespace.get(id);
	}

	public static ViewKeymapTab getInstance() {
		if (instance == null) {
			instance = new ViewKeymapTab();
		}
		return instance;
	}
}