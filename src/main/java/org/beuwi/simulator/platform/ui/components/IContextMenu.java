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
	public IContextMenu(List<MenuItem> list)
	{
		this.getItems().setAll(list);
	}

	public IContextMenu(MenuItem... items)
	{
		this.getItems().setAll(items);
	}

	/* public void setNode(Node node)
	{
		setNode(node, MouseButton.SECONDARY);
	} */

	public void setNode(Node node)
	{
		if (node instanceof Button)
		{
			node.addEventFilter(MouseEvent.MOUSE_CLICKED, event ->
			{
				if (!isShowing())
				{
					this.show(node, Side.BOTTOM, event);
				}
				else
				{
					this.hide();
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
						this.show(node, event);
					}
					else
					{
						this.hide();
					}
				});
			}
			else
			{
				node.addEventFilter(MouseEvent.MOUSE_CLICKED, event ->
				{
					this.show(node, event);
				});
			}

			node.addEventFilter(MouseEvent.MOUSE_PRESSED, event ->
			{
				if (this.isShowing())
				{
					this.hide();
				}
			});
		}
	}

	public void show(Node node, MouseEvent event)
	{
		if (MouseButton.SECONDARY.equals(event.getButton()))
		{
			this.show(node, event.getScreenX(), event.getScreenY());
		}
		else
		{
			this.hide();
		}
	}

	public void show(Node node, Side side, MouseEvent event)
	{
		if (MouseButton.PRIMARY.equals(event.getButton()))
		{
			this.show(node, side, 0, 0);
		}
		else
		{
			this.hide();
		}
	}
}