package org.beuwi.simulator.platform.ui.components;

import javafx.css.PseudoClass;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

import java.util.List;

public class IContextMenu extends ContextMenu
{
	{
		getStyleClass().add("ifx-context-menu");
	}

	public IContextMenu(List<MenuItem> list)
	{
		getItems().setAll(list);
	}

	public IContextMenu(MenuItem... items)
	{
		getItems().setAll(items);
	}

	public void setNode(Node node)
	{
		if (node instanceof Button)
		{
			node.addEventFilter(MouseEvent.MOUSE_CLICKED, event ->
			{
				if (!isShowing())
				{
					show(node, Side.BOTTOM, event);
				}
				else
				{
					hide();
				}
			});

			this.showingProperty().addListener((observable, oldValue, newValue) ->
			{
				node.pseudoClassStateChanged(PseudoClass.getPseudoClass("showing"), newValue);
			});
		}
		else
		{
			if (node instanceof ListView)
			{
				node.addEventFilter(MouseEvent.MOUSE_CLICKED, event ->
				{
					String target = event.getTarget().toString();

					if
					(
						target.contains("ListView") ||
						target.contains("IListView") ||
						target.contains("ILogView")  ||
						target.contains("HBox") || // Chat Item
						target.contains("Group")
					)
					{
						if (!((ListView) node).getItems().isEmpty())
						{
							show(node, event);
						}
					}
					else
					{
						hide();
					}
				});
			}
			else
			{
				node.addEventFilter(MouseEvent.MOUSE_CLICKED, event ->
				{
					show(node, event);
				});
			}

			node.addEventFilter(MouseEvent.MOUSE_PRESSED, event ->
			{
				if (isShowing())
				{
					hide();
				}
			});
		}
	}

	public void show(Node node, MouseEvent event)
	{
		if (MouseButton.SECONDARY.equals(event.getButton()))
		{
			show(node, event.getScreenX(), event.getScreenY());
		}
		else
		{
			hide();
		}
	}

	public void show(Node node, Side side, MouseEvent event)
	{
		if (MouseButton.PRIMARY.equals(event.getButton()))
		{
			show(node, side, 0, 0);
		}
		else
		{
			hide();
		}
	}
}