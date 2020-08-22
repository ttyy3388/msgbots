package org.beuwi.msgbots.platform.gui.dock;

import javafx.event.EventHandler;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public class DockPane extends StackPane implements EventHandler<DockEvent>
{

	public DockPane()
	{
		this(null);
	}

	public DockPane(Pane pane)
	{

	}

	// public void split()

	@Override
	public void handle(DockEvent event) {

	}
}