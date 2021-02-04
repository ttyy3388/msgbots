package org.beuwi.msgbots.platform.gui.control;

import javafx.collections.ListChangeListener;

public class LogView extends ListView<LogItem> {
	private static final String DEFAULT_STYLE_CLASS = "log-view";

	public LogView() {
		this(null);
	}

	// Name : Script name
	public LogView(String name) {
		getItems().addListener((ListChangeListener<LogItem>) change -> {
			while (change.next()) {
				for (LogItem item : change.getRemoved()) {
					item.setView(null);
				}
				for (LogItem item : change.getAddedSubList()) {
					item.setView(this);
				}
			}
		});

		if (name != null) {
		}
		else {
		}

		// setSelectionMode(SelectionMode.MULTIPLE);
		getStyleClass().add(DEFAULT_STYLE_CLASS);
	}
}