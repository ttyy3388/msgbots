package org.beuwi.simulator.platform.ui.components;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCombination;

public class IMenuItem extends MenuItem
{
	public IMenuItem(String text)
	{
		this(text, null);
	}

	public IMenuItem(String text, String command)
	{
		this(text, command, null);
	}

	public IMenuItem(String text, String command, EventHandler<ActionEvent> handler)
	{
		Label name = new Label(text);

		name.setPrefWidth(150);

		if (command != null)
		{
			setAccelerator(KeyCombination.keyCombination(command));
		}

		setGraphic(name);
		setOnAction(handler);
	}
}