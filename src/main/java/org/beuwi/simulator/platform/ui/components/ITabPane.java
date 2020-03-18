package org.beuwi.simulator.platform.ui.components;

import javafx.scene.Node;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

public class ITabPane extends TabPane
{
	public ITabPane()
	{
		// setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
		// getStyleClass().add("i-tab-pane");
	}

	public void addTab(ITab tab)
	{
		tab.setOnMousePressed(event ->
		{
			if (event.isMiddleButtonDown())
			{
				closeTab(tab);
			}
		});

		getTabs().add(tab);
	}

	public void addTab(String title, Node node)
	{
		addTab(new ITab(null, title, node));
	}

	public void closeTab(Tab tab)
	{
		getTabs().remove(tab);
	}
}