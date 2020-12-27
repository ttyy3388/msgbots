package org.beuwi.msgbots.platform.gui.control;

import javafx.collections.ListChangeListener;

import org.beuwi.msgbots.manager.LogManager;

public class LogView extends ListView<LogItem> {
	private static final String DEFAULT_STYLE_CLASS = "log-view";

	public LogView() {
		this(null);
	}

	// Name : Script Name
	public LogView(String name) {
		getItems().addListener((ListChangeListener<LogItem>) change -> {
			while (change.next()) {
				for (LogItem logbox : change.getRemoved()) {
					logbox.setView(null);
				}
				for (LogItem logbox : change.getAddedSubList()) {
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