package org.beuwi.simulator.platform.application.views.actions;

import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

import java.util.List;

public class CloseTabAction
{
	public static void update(TabPane pane)
	{
		update(pane.getSelectionModel().getSelectedItem());
	}

	public static void update(Tab tab)
	{
		tab.getTabPane().getTabs().remove(tab);
	}

	public static void update(List<Tab> tabs)
	{
		tabs.get(0).getTabPane().getTabs().removeAll(tabs);
	}
}