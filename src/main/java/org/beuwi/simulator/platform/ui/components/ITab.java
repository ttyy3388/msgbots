package org.beuwi.simulator.platform.ui.components;

import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.Node;
import javafx.scene.control.Tab;
import javafx.scene.layout.HBox;

public class ITab extends Tab
{
	{
		getStyleClass().add("ifx-tab");
	}

	public void setHeader(HBox hbox)
	{
		setGraphic(hbox);
	}

	public Node getHeader()
	{
		return getGraphic();
	}

	public void setMenu(IContextMenu menu)
	{
		menu.setNode(getHeader());
	}

	public ITabPane getITabPane()
	{
		return (ITabPane) getTabPane();
	}

	public void addEventHandler(EventType type, EventHandler handler)
	{
		getHeader().addEventHandler(type, handler);
	}

	public void addEventFilter(EventType type, EventHandler handler)
	{
		getHeader().addEventFilter(type, handler);
	}
}
