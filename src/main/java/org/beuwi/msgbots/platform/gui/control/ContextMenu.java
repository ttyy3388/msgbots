package org.beuwi.msgbots.platform.gui.control;

import javafx.css.PseudoClass;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public class ContextMenu extends javafx.scene.control.ContextMenu
{
	public ContextMenu()
	{
		this(null);
	}

	public ContextMenu(javafx.scene.control.MenuItem... items)
	{
		getItems().addAll(items);
		getStyleClass().add("context-menu");
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
			node.addEventFilter(MouseEvent.MOUSE_CLICKED, event ->
			{
				show(node, event);
			});

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
