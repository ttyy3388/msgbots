package org.beuwi.msgbots.platform.gui.control;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;

public class MenuButton extends Button
{
    private static final Insets DEFAULT_PADDING = new Insets(0, 5, 0, 5);

	private static final int DEFAULT_WIDTH = 40;
	private static final int DEFAULT_HEIGHT = 30;

	public MenuButton()
	{
		setPadding(DEFAULT_PADDING);

		setMinWidth(DEFAULT_WIDTH);
		// setPrefWidth(DEFAULT_WIDTH);
		setPrefHeight(DEFAULT_HEIGHT);

		getStyleClass().add("menu-button");
	}

	public void setMenu(ContextMenu menu)
	{
		menu.setNode(this);
	}

	public void setMenus(MenuItem... items)
	{
		setMenu(new ContextMenu(items));
	}
}