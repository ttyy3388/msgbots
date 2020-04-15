package org.beuwi.simulator.platform.ui.components;

import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;

import java.util.List;

public class IContextMenu extends ContextMenu
{
	public IContextMenu(List<MenuItem> list)
	{
		getItems().addAll(list);
	}

	public IContextMenu(MenuItem... items)
	{
		getItems().addAll(items);
	}

	public void show(Node node, Side side)
	{
		show(node, side, 0, 0);
	}

	public void show(Node node, MouseEvent event)
	{
		show(node, event.getScreenX(), event.getScreenY());
	}
}