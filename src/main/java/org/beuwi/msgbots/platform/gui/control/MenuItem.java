package org.beuwi.msgbots.platform.gui.control;

import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.HBox;

public class MenuItem extends javafx.scene.control.MenuItem
{
	public MenuItem(String text)
	{
		this(text, (String) null);
	}

	public MenuItem(String text, String command)
	{
		this(text, command, null);
	}

	public MenuItem(String text, EventHandler handler)
	{
		this(text, null, handler);
	}

	public MenuItem(String text, String command, EventHandler handler)
	{
		this(text, command, null, handler);
	}

	public MenuItem(String text, String command, ReadOnlyBooleanProperty property, EventHandler handler)
	{
		Label name = new Label(text);
		name.setMinWidth(150);

		if (command != null)
		{
			setAccelerator(KeyCombination.keyCombination(command));
		}

		if (property != null)
		{
			property.addListener((observable, oldValue, newValue) ->
			{
				setDisable(!newValue);
			});
		}

		setGraphic(new HBox(name));
		setOnAction(handler);
	}

	public static SeparatorMenuItem getSeparator()
	{
		return new SeparatorMenuItem();
	}
}
