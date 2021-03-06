package org.beuwi.msgbots.platform.gui.control;

import javafx.collections.ListChangeListener;

import org.beuwi.msgbots.manager.LogManager;

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
			getItems().setAll(LogManager.load(name));
		}
		else {
			getItems().setAll(LogManager.loadGlobal());
		}

		setAutoScroll(true);
		// setSelectionMode(SelectionMode.MULTIPLE);
		getStyleClass().add(DEFAULT_STYLE_CLASS);
	}
}