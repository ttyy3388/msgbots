package org.beuwi.msgbots.platform.gui.control;

import javafx.collections.ObservableList;

public class LogView extends ListView<Log>
{
	private static final String DEFAULT_STYLE_CLASS = "log-view";
	private static final int DEFAULT_GAP_VALUE = 10;

	public LogView()
	{
		this(null);
	}

	// Name : Script Name
	public LogView(String name)
	{
		// Global Log
		if (name != null)
		{
			// setItems();
		}
		else
		{
			// setItems();
		}

		// setSpacing(DEFAULT_GAP_VALUE);
		getStyleClass().add(DEFAULT_STYLE_CLASS);
		// getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
	}

	public ObservableList<Log> getLogs()
	{
		return getItems();
	}
}