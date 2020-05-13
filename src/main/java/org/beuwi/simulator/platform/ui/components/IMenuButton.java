package org.beuwi.simulator.platform.ui.components;

import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;

import java.util.List;

public class IMenuButton extends Button
{
	{
		getStyleClass().add("ifx-menu-button");
	}

	public void setMenu(IContextMenu menu)
	{
		menu.setNode(this);
	}

	public void setMenus(MenuItem... items)
	{
		setMenu(new IContextMenu(items));
	}

	public void setMenus(List<MenuItem> items)
	{
		setMenu(new IContextMenu(items));
	}
}
