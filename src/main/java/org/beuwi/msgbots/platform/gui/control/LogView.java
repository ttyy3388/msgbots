package org.beuwi.msgbots.platform.gui.control;

import javafx.scene.control.SelectionMode;

public class LogView extends ListView
{
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

		getStyleClass().add("log-view");
		getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
	}
}