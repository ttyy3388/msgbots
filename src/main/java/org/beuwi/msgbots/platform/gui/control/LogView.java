package org.beuwi.msgbots.platform.gui.control;

import javafx.collections.ListChangeListener;

import javafx.scene.control.ListCell;
import javafx.scene.control.SelectionMode;
import org.beuwi.msgbots.manager.LogManager;

public class LogView extends ListView<LogBox> {
	private static final String DEFAULT_STYLE_CLASS = "log-view";

	public LogView() {
		this(null);
	}

	// Name : Script Name
	public LogView(String name) {
		getItems().addListener((ListChangeListener<LogBox>) change -> {
			while (change.next()) {
				for (LogBox logbox : change.getRemoved()) {
					logbox.setView(null);
				}
				for (LogBox logbox : change.getAddedSubList()) {
					logbox.setView(this);
				}
			}
		});

		if (name != null) {
			getItems().setAll(LogManager.load(name));
		}
		else {
			getItems().setAll(LogManager.load());
		}

		// setSelectionMode(SelectionMode.MULTIPLE);
		getStyleClass().add(DEFAULT_STYLE_CLASS);
	}
}