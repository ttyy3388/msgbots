package org.beuwi.simulator.platform.ui.components;

import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.control.Tab;
import javafx.scene.layout.HBox;

public class ITab extends Tab
{
	{
		this.getStyleClass().add("ifx-tab");
	}

	public void setHeader(HBox hbox)
	{
		this.setGraphic(hbox);
	}

	public HBox getHeader()
	{
		return (HBox) this.getGraphic();
	}

	public void setMenu(IContextMenu menu)
	{
		menu.setNode(this.getHeader());
	}

	public ITabPane getITabPane()
	{
		return (ITabPane) this.getTabPane();
	}

	public void addEventHandler(EventType type, EventHandler handler)
	{
		this.getHeader().addEventHandler(type, handler);
	}

	public void addEventFilter(EventType type, EventHandler handler)
	{
		this.getHeader().addEventFilter(type, handler);
	}
}
