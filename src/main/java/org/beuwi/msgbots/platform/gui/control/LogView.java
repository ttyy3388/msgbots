package org.beuwi.msgbots.platform.gui.control;

import javafx.collections.ObservableList;
import org.beuwi.msgbots.manager.LogManager;

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
		if (name != null)
		{
			setItems(LogManager.load(name));
		}
		else
		{
			setItems(LogManager.load());
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