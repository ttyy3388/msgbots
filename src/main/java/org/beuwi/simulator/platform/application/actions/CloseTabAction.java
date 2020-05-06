package org.beuwi.simulator.platform.application.actions;

import javafx.scene.control.Tab;

import java.util.Collection;

public class CloseTabAction
{
	public static void update(Tab tab)
	{
		tab.getTabPane().getTabs().remove(tab);
	}

	public static void update(Collection tabs)
	{
		tabs.removeAll(tabs);
	}
}