package org.beuwi.msgbots.platform.gui.control;

import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;

public class MenuButton extends Button
{
	private static final String DEFAULT_STYLE_CLASS = "menu-button";

	private static final int DEFAULT_WIDTH = 40;
	private static final int DEFAULT_HEIGHT = 30;

	public MenuButton()
	{
		this(null);
	}

	public MenuButton(String name)
	{
		setMinWidth(DEFAULT_WIDTH);
		// setPrefWidth(DEFAULT_WIDTH);
		setPrefHeight(DEFAULT_HEIGHT);

		getStyleClass().add(DEFAULT_STYLE_CLASS);

		if (name != null)
		{
			setText(name);
		}
	}

	public void setMenu(ContextMenu menu)
	{
		menu.setNode(this);
	}

	public void setMenus(MenuItem... items)
	{
		setMenu(new ContextMenu(items));
	}

	public ReadOnlyBooleanProperty getFocusedProperty()
	{
		return focusedProperty();
	}
}