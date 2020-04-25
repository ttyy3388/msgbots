package org.beuwi.simulator.platform.ui.components;

import javafx.beans.property.BooleanProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.HBox;

public class IMenuItem extends MenuItem
{
	public IMenuItem(String text)
	{
		this(text, (String) null);
	}

	public IMenuItem(String text, String command)
	{
		this(text, command, null);
	}

	public IMenuItem(String text, EventHandler<ActionEvent> handler)
	{
		this(text, null, handler);
	}

	public IMenuItem(String text, String command, EventHandler<ActionEvent> handler)
	{
		this(text, command, null, handler);
	}

	public IMenuItem(String text, String command, BooleanProperty property, EventHandler<ActionEvent> handler)
	{
		Label name = new Label(text);
		name.setMinWidth(150);

		// Label cmd = new Label(command);
		// cmd.setAlignment(Pos.CENTER_RIGHT);

		// HBox.setHgrow(name, Priority.ALWAYS);

		if (command != null)
		{
			setAccelerator(KeyCombination.keyCombination(command));
		}

		if (property != null)
		{
			property.addListener((observable, oldValue, newValue) ->
			{
				this.setDisable(newValue);
			});
		}

		setGraphic(new HBox(name));
		setOnAction(handler);
	}
}