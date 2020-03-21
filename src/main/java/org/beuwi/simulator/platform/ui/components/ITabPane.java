package org.beuwi.simulator.platform.ui.components;

import javafx.scene.control.TabPane;

public class ITabPane extends TabPane
{
	public ITabPane()
	{
		// super();
	}

	public void addTab(ITab itab)
	{
		getTabs().add(itab);
	}
}